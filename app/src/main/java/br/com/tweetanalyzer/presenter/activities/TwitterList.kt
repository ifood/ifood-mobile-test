package br.com.tweetanalyzer.presenter.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.TwitterService
import br.com.tweetanalyzer.eventbus.TwetterListResult
import br.com.tweetanalyzer.models.TwitterUserInfo
import br.com.tweetanalyzer.presenter.adapter.TwitterListAdapter
import br.com.tweetanalyzer.util.Constant
import com.bumptech.glide.Glide
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    private val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR: Float = 0.9f
    private val PERCENTAGE_TO_HIDE_TITLE_DETAILS: Float = 0.3f
    private val ALPHA_ANIMATIONS_DURATION: Long = 200

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    private lateinit var tweetListAdapter: TwitterListAdapter
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
        twitter_recycler_view.visibility = View.GONE

        twitter_progress.visibility = View.VISIBLE

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

    private fun searchUser() {
        val i = Intent(this, TwitterService::class.java)
        i.putExtra(Constant.JOB_TYPE, Constant.JOB_GET_USER_INFO)
        i.putExtra(Constant.SEARCH_USER_INPUT, searchString)
        startService(i)
    }

    private fun searchTweets() {
        val i = Intent(this, TwitterService::class.java)
        i.putExtra(Constant.JOB_TYPE, Constant.JOB_TYPE_SEARCH_INPUT)
        i.putExtra(Constant.SEARCH_INPUT, searchString)
        startService(i)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserInfoEvent(user: TwitterUserInfo) {
        toolbar_title.text = user.userName
        user_name.text = user.userName
        screen_name.text = user.screenName
        profile_description.text = if (user.description.isBlank()) "" else user.description
        profile_following.text = user.followingCount
        profile_followers.text = user.followersCount

        Glide.with(this)
                .load(user.backgroundImageUrl)
                .into(profile_background_image)

        Glide.with(this)
                .load(user.imageUrl)
                .into(user_profile_image)

        searchTweets()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(tweetsResult: TwetterListResult) {
        if (tweetsResult.tweetList!!.isNotEmpty()) {
            twitter_recycler_view.adapter = TwitterListAdapter(this, tweetsResult.tweetList)
            twitter_recycler_view.visibility = View.VISIBLE
            twitter_progress.visibility = View.GONE
        }
        //TODO validate
    }
}