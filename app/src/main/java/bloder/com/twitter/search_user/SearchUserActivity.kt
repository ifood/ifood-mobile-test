package bloder.com.twitter.search_user

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import bloder.com.domain.models.search.Status
import bloder.com.presentation.AppViewModel
import bloder.com.presentation.twitter.search.SearchTweetsState
import bloder.com.presentation.twitter.search.SearchViewModel
import bloder.com.twitter.R
import bloder.com.twitter.StateActivity
import bloder.com.twitter.preferences.AuthTokenPreferences
import kotlinx.android.synthetic.main.activity_search_user.*
import org.koin.android.ext.android.inject

class SearchUserActivity : StateActivity<SearchTweetsState>() {

    private val viewModel: SearchViewModel by inject()
    private val authPreferences: AuthTokenPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        changeSystemBarColor()
    }

    override fun handleState(state: SearchTweetsState) = when(state) {
        is SearchTweetsState.Setup -> setup()
        is SearchTweetsState.TweetsFetched -> onTweetsFetched(state.tweets)
        is SearchTweetsState.TweetsFetchFailed -> onTweetsFetchFailed(state.errorMessage)
    }

    override fun provideViewModel(): AppViewModel<SearchTweetsState> = viewModel

    private fun setup() {
        search.setOnEditorActionListener { text, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.fetchTweetsFrom(text.text.toString(), authPreferences.getAuthToken(this@SearchUserActivity))
            }
            true
        }
    }

    private fun onTweetsFetched(tweets: List<Status>) {
        
    }

    private fun onTweetsFetchFailed(errorMessage: String) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.ops_error))
                .setMessage(errorMessage)
                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
    }

    private fun changeSystemBarColor() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    } else {}
}