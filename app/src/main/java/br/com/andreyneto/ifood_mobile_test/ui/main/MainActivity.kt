package br.com.andreyneto.ifood_mobile_test.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.ifood_mobile_test.R
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import br.com.andreyneto.ifood_mobile_test.ui.detail.SentimentBottomSheet
import br.com.andreyneto.ifood_mobile_test.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mAdapter: TweetAdapter? = null

    private var mViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val factory: MainViewModelFactory = InjectorUtils().provideMainActivityViewModelFactory(this)
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)


        mViewModel!!.loadingTweets().observe(this, Observer {
            showLoading(it)
        })

        editText.setOnEditorActionListener { editText, _, _ ->
            editText.text.let { charSequence ->
                hideKeyboard()
                if (charSequence.isNotEmpty()) {
                    mViewModel!!.loadingTweets().postValue(true)
                    mViewModel!!.getTweets(editText.text.toString()).removeObservers(this)
                    mViewModel!!.getTweets(editText.text.toString()).observe(this, Observer {
                        manageTweets(it)
                    })
                } else {
                    mViewModel!!.loadingTweets().postValue(false)
                    emptyScreen(false)
                }
            }
            true
        }
        setupRecycler()
    }

    private fun emptyScreen(showMessage: Boolean) {
        cardView.visibility = View.GONE
        val layoutParams: ConstraintLayout.LayoutParams = editText.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.verticalBias = 0.5f
        editText.layoutParams = layoutParams
        lblError.visibility = if (showMessage && progressBar.visibility != View.VISIBLE) View.VISIBLE else View.GONE
    }

    private fun manageTweets(tweets: List<TweetEntry>?) {
        tweets?.let {
            if(it.isNotEmpty()) {
                cardView.visibility = View.VISIBLE
                val layoutParams: ConstraintLayout.LayoutParams = editText.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.verticalBias = 0.0f
                editText.layoutParams = layoutParams
                mAdapter?.swapList(it)
            } else emptyScreen(true)
        }
    }

    private fun setupRecycler() {
        mAdapter = TweetAdapter(this,  mutableListOf()) { tweetID ->  clickHandler(tweetID) }
        recyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this.mAdapter
    }

    private fun showLoading(visible: Boolean) {
        progressBar.visibility = if(visible) View.VISIBLE else View.GONE
    }

    private fun clickHandler(tweetID: Long) {
        val sentimentBottomSheet =  SentimentBottomSheet().newInstance(tweetID)
        sentimentBottomSheet.show(supportFragmentManager, SentimentBottomSheet.TWEET_ID)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
        recyclerView.requestFocus()
    }
}
