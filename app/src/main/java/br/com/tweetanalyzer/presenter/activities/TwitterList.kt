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
import br.com.tweetanalyzer.services.SearchService
import br.com.tweetanalyzer.events.TwetterListResult
import br.com.tweetanalyzer.models.JobType
import br.com.tweetanalyzer.models.TwitterUserInfo
import br.com.tweetanalyzer.presenter.adapter.TwitterListAdapter
import br.com.tweetanalyzer.util.Constant
import br.com.tweetanalyzer.util.GlideUtil
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
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
    override fun onAnimationRepeat(animation: Animation?) {
        //do nothing
    }

    override fun onAnimationEnd(animation: Animation?) {
        main_toolbar.setBackgroundColor(ContextCompat.getColor(this,
                if (mIsTheTitleVisible) R.color.colorPrimary else android.R.color.transparent))
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

    private val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR: Float = 0.8f
    private val PERCENTAGE_TO_HIDE_TITLE_DETAILS: Float = 0.3f
    private val ALPHA_ANIMATIONS_DURATION: Long = 200

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    private var adapter: TwitterListAdapter = TwitterListAdapter(this, listOf())
    private lateinit var skeletonScreen: RecyclerViewSkeletonScreen
    private lateinit var searchString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_list)

        getExtras()
        initLayout()
        searchUser()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun getExtras() {
        searchString = intent.getStringExtra(Constant.SEARCH_INPUT)
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

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbar_title, ALPHA_ANIMATIONS_DURATION, View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbar_title, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(profile_info_view, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE)
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(profile_info_view, ALPHA_ANIMATIONS_DURATION, View.VISIBLE)
                mIsTheTitleContainerVisible = true
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
        i.putExtra(Constant.JOB_TYPE, jobType.name)
        i.putExtra(searchType, search)
        startService(i)
    }

    private fun searchUser() {
        startService(JobType.GET_USER_INFO, Constant.SEARCH_USER_INPUT, searchString)
    }

    private fun searchTweets() {
        startService(JobType.SEARCH_INPUT, Constant.SEARCH_INPUT, searchString)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserInfoEvent(user: TwitterUserInfo) {
        updateUserInfo(user)

        searchTweets()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(tweetsResult: TwetterListResult) {
        if (tweetsResult.tweetList!!.isNotEmpty()) {
            tweetsResult.tweetList.forEach({
                it.score = -2.0
            })
            adapter.setTweetList(tweetsResult.tweetList)
            skeletonScreen.hide()
        }
    }
}