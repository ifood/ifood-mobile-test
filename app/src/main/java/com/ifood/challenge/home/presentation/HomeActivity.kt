package com.ifood.challenge.home.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ifood.challenge.R
import com.ifood.challenge.base.common.exception.AppException
import com.ifood.challenge.base.data.ViewState
import com.ifood.challenge.base.data.getResponse
import com.ifood.challenge.base.extensions.failure
import com.ifood.challenge.base.extensions.observe
import com.ifood.challenge.base.extensions.provideViewModel
import com.ifood.challenge.base.extensions.toast
import com.ifood.challenge.base.extensions.view.isVisible
import com.ifood.challenge.base.extensions.view.setLinearLayout
import com.ifood.challenge.base.presentation.BaseActivity
import com.ifood.challenge.base.presentation.views.EmptyView
import com.ifood.challenge.base.presentation.views.ErrorView
import com.ifood.challenge.base.presentation.views.SkeletonView
import com.ifood.challenge.home.model.Tweet
import com.ifood.challenge.home.model.TwitterUser
import com.ifood.challenge.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.search_users_bottom_sheet.*

class HomeActivity : BaseActivity() {

    override val layoutResId: Int = R.layout.home_activity
    override val baseEmptyView: EmptyView? get() = emptyView.apply { setActionButtonClick { viewModel.tryFetchUserTimelineAgain() } }
    override val baseErrorView: ErrorView? get() = errorView.apply { setActionButtonClick { viewModel.tryFetchUserTimelineAgain() } }
    override val baseSkeletonView: SkeletonView? get() = skeletonView

    private lateinit var viewModel: HomeViewModel
    private val bottomSheet by lazy { BottomSheetBehavior.from(homeNestedScroll) }
    private val twitterUserAdapter by lazy { TwitterUserAdapter(::onTwitterUserClick) }
    private val tweetAdapter by lazy { TweetAdapter(::onTweetClick) }
    private var isShowingInitialState = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory) {
            observe(twitterUsersResponseState, ::onTwitterUsersResponse)
            observe(tweetsResponseState, ::onTweetsResponse)
            observe(tweetAnalyzingSentiment, ::onTweetChange)
            observe(searchText, ::onSearchTextChange)
            failure(appException, ::onResponseError)
        }

        setupViews()
    }

    private fun setupViews() {
        observeTextChange()
        searchUserEmptyView.apply {
            setActionButtonClick {
                viewModel.trySearchUsersAgain()
                isVisible = false
            }
        }
        searchUserErrorView.apply {
            setActionButtonClick {
                viewModel.trySearchUsersAgain()
                isVisible = false
            }
        }
    }

    private fun onSearchTextChange(newSearchText: String?) {
        if (newSearchText == null) return
        viewModel.searchUsers(newSearchText)
    }

    private fun onTwitterUsersResponse(viewState: ViewState?) {
        when (viewState) {
            ViewState.Loading -> loadingUsers(true)
            is ViewState.Complete<*> -> showUsers(viewState.getResponse())
            is ViewState.Failed -> showUsersError(viewState.error)
        }
    }

    private fun onTweetsResponse(viewState: ViewState?) {
        when (viewState) {
            ViewState.Loading -> loading(true)
            is ViewState.Complete<*> -> showTweets(viewState.getResponse())
        }
    }

    private fun onResponseError(appException: AppException?) {
        checkResponseException(appException)
    }

    private fun showUsers(twitterUsers: List<TwitterUser>) {
        loadingUsers(false)
        searchUserErrorView.isVisible = false
        searchUserEmptyView.isVisible = twitterUsers.isEmpty()
        expandBottomSheet()
        searchUsersRv.apply {
            if (adapter == null) {
                setLinearLayout()
                adapter = twitterUserAdapter
            }
        }
        twitterUserAdapter.setItems(twitterUsers)
    }

    private fun showUsersError(error: Throwable?) {
        loadingUsers(false)
        searchUserErrorView.showError(error)
        expandBottomSheet()
    }

    private fun showTweets(tweets: List<Tweet>) {
        loading(false)
        homeTweetsRv.apply {
            if (adapter == null) {
                setLinearLayout(hasFixedSize = true)
                adapter = tweetAdapter
            }
        }
        tweetAdapter.setItems(tweets)
        toggleEmptyView(tweets)
    }

    private fun onTweetChange(viewState: ViewState?) {
        when (viewState) {
            is ViewState.Complete<*> -> tweetAdapter.notifyChange(viewState.getResponse())
            is ViewState.Failed -> {
                toast(R.string.analyze_sentiment_error, Toast.LENGTH_LONG)
                tweetAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onTwitterUserClick(twitterUser: TwitterUser) {
        if (isShowingInitialState) {
            isShowingInitialState = false
            initialState.isVisible = false
        }
        viewModel.fetchUserTimeline(twitterUser)
        collapseBottomSheet()
        searchUsersEditText.clearFocus()
    }

    private fun onTweetClick(tweet: Tweet) {
        viewModel.analyzeSentiment(tweet)
    }

    private fun loadingUsers(isLoading: Boolean) {
        searchUsersProgress.isVisible = isLoading
    }

    private fun expandBottomSheet() {
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun collapseBottomSheet() {
        bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun observeTextChange() {
        viewModel.observeSearchTextChange()
        searchUsersEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                val query = text?.toString() ?: ""
                viewModel.onTextChange(query)
            }
        })
    }

    override fun onBackPressed() {
        if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) collapseBottomSheet()
        else super.onBackPressed()
    }
}
