package br.com.tweetanalyzer.presenter.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.events.TwitterListResult
import br.com.tweetanalyzer.events.UserInfoEvent
import br.com.tweetanalyzer.models.JobType
import br.com.tweetanalyzer.models.TwitterUserInfo
import br.com.tweetanalyzer.presenter.adapter.TwitterListAdapter
import br.com.tweetanalyzer.services.SearchService
import br.com.tweetanalyzer.services.util.ServiceConstants
import br.com.tweetanalyzer.util.GlideUtil
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.ViewSkeletonScreen
import com.google.gson.Gson
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.twitter_list.*
import kotlinx.android.synthetic.main.twitter_list_content_view.*
import kotlinx.android.synthetic.main.twitter_list_header_view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by gabrielsamorim
 * on 15/05/18.
 */
class TwitterList : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, Animation.AnimationListener {
    private val percentShowToolbarTitle: Float = 0.8f
    private val percentHideToolbarDetails: Float = 0.3f
    private val animationDuration: Long = 200

    private var isTitleVisible = false
    private var isTitleContainerVisible = true

    private var adapter: TwitterListAdapter = TwitterListAdapter(this, listOf())

    private lateinit var skeletonScreen: RecyclerViewSkeletonScreen
    private var userSkeletonView: ViewSkeletonScreen? = null

    private lateinit var searchString: String

    private lateinit var user: TwitterUserInfo
    private lateinit var tweets: TwitterListResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_list)

        getExtras()
        initLayout()

        if (savedInstanceState == null)
            searchUser()
        else
            handleSavedInstance(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("user_info", Gson().toJson(user))
        outState?.putString("tweets_list", Gson().toJson(tweets))
    }

    private fun getExtras() {
        searchString = intent.getStringExtra(ServiceConstants.SEARCH_INPUT)
    }

    private fun initLayout() {
        main_appbar.addOnOffsetChangedListener(this)

        twitter_recycler_view.layoutManager = LinearLayoutManager(this)
        twitter_recycler_view.itemAnimator = DefaultItemAnimator()
        twitter_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        twitter_recycler_view.adapter = adapter

        skeletonScreen = Skeleton.bind(twitter_recycler_view)
                .adapter(adapter)
                .load(R.layout.twitter_list_skeleton_row)
                .show()


        startAlphaAnimation(toolbar_title, 0, View.INVISIBLE)
    }

    private fun handleSavedInstance(savedInstanceState: Bundle) {
        user = Gson().fromJson(savedInstanceState.getString("user_info"), TwitterUserInfo::class.java)
        tweets = Gson().fromJson(savedInstanceState.getString("tweets_list"), TwitterListResult::class.java)
        updateUserInfo(user)
        twitter_recycler_view.adapter = TwitterListAdapter(this, tweets.tweetList!!)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= percentShowToolbarTitle) {
            if (!isTitleVisible) {
                startAlphaAnimation(toolbar_title, animationDuration, View.VISIBLE)
                isTitleVisible = true
            }
        } else {
            if (isTitleVisible) {
                startAlphaAnimation(toolbar_title, animationDuration, View.INVISIBLE)
                isTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= percentHideToolbarDetails) {
            if (isTitleContainerVisible) {
                startAlphaAnimation(profile_info_view, animationDuration, View.INVISIBLE)
                isTitleContainerVisible = false
            }
        } else {
            if (!isTitleContainerVisible) {
                startAlphaAnimation(profile_info_view, animationDuration, View.VISIBLE)
                isTitleContainerVisible = true
            }
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation = if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f) else AlphaAnimation(1f, 0f)

        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        alphaAnimation.setAnimationListener(this)
        v.startAnimation(alphaAnimation)
    }

    private fun updateUserInfo(user: TwitterUserInfo) {
        if (userSkeletonView != null)
            userSkeletonView?.hide()

        toolbar_title.text = user.userName
        user_name.text = user.userName
        screen_name.text = user.screenName
        profile_description.text = if (user.description.isBlank()) "" else user.description
        profile_following.text = user.followingCount
        profile_followers.text = user.followersCount

        GlideUtil.glideImage(this, user.backgroundImageUrl, profile_background_image)
        GlideUtil.glideImage(this, user.imageUrl, user_profile_image)
    }

    private fun startService(jobType: JobType, searchType: String, search: String) {
        val i = Intent(this, SearchService::class.java)
        i.putExtra(ServiceConstants.JOB_TYPE, jobType.name)
        i.putExtra(searchType, search)
        startService(i)
    }

    private fun searchUser() {
        startUserLoadingAnimation()
        startService(JobType.GET_USER_INFO, ServiceConstants.SEARCH_USER_INPUT, searchString)
    }

    private fun searchTweets() {
        startService(JobType.SEARCH_INPUT, ServiceConstants.SEARCH_INPUT, searchString)
    }

    private fun startUserLoadingAnimation() {
        userSkeletonView = Skeleton.bind(profile_info_view)
                .load(R.layout.twitter_list_header_skeleton_view)
                .show()
    }

    override fun onAnimationRepeat(animation: Animation?) {
        //do nothing
    }

    override fun onAnimationEnd(animation: Animation?) {
        main_toolbar.setBackgroundColor(ContextCompat.getColor(this,
                if (isTitleVisible) R.color.colorPrimary else android.R.color.transparent))
    }

    override fun onAnimationStart(animation: Animation?) {
        //do nothing
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, offset: Int) {
        val maxScroll = appBarLayout?.totalScrollRange
        val percentage = Math.abs(offset).toFloat() / maxScroll!!.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserInfoEvent(userInfo: UserInfoEvent) {
        if (userInfo.user == null) {
            setResult(MainActivity.ACTIVITY_RESULT_CODE_ERROR, Intent().apply {
                putExtra(MainActivity.ERROR_MESSAGE, getString(R.string.user_not_found))
            })
            finish()
            return
        }

        user = userInfo.user

        updateUserInfo(userInfo.user)

        searchTweets()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(tweetsResult: TwitterListResult) {
        if (tweetsResult.tweetList!!.isNotEmpty()) {
            tweets = TwitterListResult(tweetsResult.tweetList)

            tweetsResult.tweetList.forEach({
                it.score = -2.0
            })
            adapter.setTweetList(tweetsResult.tweetList)
        } else {
            //Hide skeleton if the user does not have any tweet to show
            card_recycler_view.visibility = View.GONE
        }
        skeletonScreen.hide()
    }
}