package com.shrpereira.tweetsense.app.ui.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.shrpereira.tweetsense.app.R
import com.shrpereira.tweetsense.app.common.extension.hideKeyboard
import com.shrpereira.tweetsense.app.common.extension.observe
import com.shrpereira.tweetsense.app.ui.base.BaseActivity
import com.shrpereira.tweetsense.domain.model.TweetModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

	private val viewModel by viewModel<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		configureSwipeRefresh()
		configureRecyclerView()
		configureNicknameForm()
		configureObservers()
	}

	private fun configureSwipeRefresh() {
		swipeRefreshTweets.setOnRefreshListener {
			getTweets()
		}
	}

	private fun configureRecyclerView() {
		recyclerViewTweets.apply {
			hasFixedSize()
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = TweetsListAdapter(mutableListOf()) { model ->
				getTweetSentiment(model.text)
			}
		}
	}

	private fun configureNicknameForm() {
		materialButtonSend.setOnClickListener { getTweets() }
		textInputEditTextNickname.setOnEditorActionListener { _, actionId, _ ->
			return@setOnEditorActionListener when (actionId) {
				EditorInfo.IME_ACTION_SEND -> {
					getTweets()
					true
				}
				else -> false
			}
		}
	}

	private fun configureObservers() {
		observe(viewModel.tweetsLiveData, ::handleTweets)
		observe(viewModel.sentimentLiveData, ::handleSentiment)
	}

	private fun handleTweets(state: MainViewModel.TweetState) {
		hideLoading()

		when (state) {
			is MainViewModel.TweetState.Success -> onTweetsFetchSuccess(state.tweets)
			is MainViewModel.TweetState.Error -> doOnError(state.message)
			is MainViewModel.TweetState.EmptyNickname ->
				toast(getString(R.string.the_nickname_cant_be_empty))
		}
	}

	private fun handleSentiment(state: MainViewModel.SentimentState) {
		hideFullScreenLoading()

		when (state) {
			is MainViewModel.SentimentState.Positive ->
				createSentimentDialog(R.layout.dialog_positive_emoji)
			is MainViewModel.SentimentState.Negative ->
				createSentimentDialog(R.layout.dialog_negative_emoji)
			is MainViewModel.SentimentState.Neutral ->
				createSentimentDialog(R.layout.dialog_neutral_emoji)
			is MainViewModel.SentimentState.Error -> {
				Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_LONG).show()
			}
		}
	}

	private fun createSentimentDialog(@LayoutRes layoutId: Int) {
		Dialog(this).apply {
			window?.requestFeature(Window.FEATURE_NO_TITLE)
			window?.setBackgroundDrawableResource(android.R.color.transparent)
			setContentView(layoutInflater.inflate(layoutId, null))
			findViewById<ConstraintLayout>(R.id.constraintLayoutParent).setOnClickListener {
				dismiss()
			}
			show()
		}
	}

	private fun getTweets() {
		showLoading()
		viewModel.getTweets(textInputEditTextNickname.text.toString())
	}

	private fun getTweetSentiment(tweetText: String) {
		showFullScreenLoading()
		viewModel.getTweetSentiment(tweetText)
	}

	private fun showLoading() {
		swipeRefreshTweets.isRefreshing = true
	}

	private fun hideLoading() {
		swipeRefreshTweets.isRefreshing = false
	}

	private fun doOnError(errorMessage: String) {
		clearList()
		Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
	}

	private fun onTweetsFetchSuccess(tweets: List<TweetModel>) {
		(recyclerViewTweets.adapter as TweetsListAdapter).updateList(tweets)
		hideKeyboard()
		textInputEditTextNickname.clearFocus()
	}

	private fun clearList() {
		(recyclerViewTweets.adapter as TweetsListAdapter).updateList(listOf())
	}
}
