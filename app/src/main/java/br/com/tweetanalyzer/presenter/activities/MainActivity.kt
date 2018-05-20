package br.com.tweetanalyzer.presenter.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.controller.PreferenceController
import br.com.tweetanalyzer.events.TokenRetrieveEvent
import br.com.tweetanalyzer.models.JobType
import br.com.tweetanalyzer.services.SearchService
import br.com.tweetanalyzer.services.util.ServiceConstants
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val ACTIVITY_RESULT_CODE_ERROR = 0

        const val ERROR_MESSAGE = "error_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_search_view.setOnClickListener(this)
        item_input_et.setOnEditorActionListener({ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                startSearch()
                true
            } else
                false
        })
        search_button.setOnClickListener(this)

        if (savedInstanceState != null)
            handleSavedInstance(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

        if (PreferenceController.getToken(this).isEmpty()) {
            startService(Intent(this, SearchService::class.java).apply {
                putExtra(ServiceConstants.JOB_TYPE, JobType.GET_AUTH)
            })
        }
    }

    override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBoolean("started_search", item_input_et.isShown)
    }

    private fun handleSavedInstance(savedInstance: Bundle) = showSearchView(savedInstance.getBoolean("started_search"))

    private fun showSearchView(isToShow: Boolean) {
        btn_show_search_view.visibility = if (isToShow) View.GONE else View.VISIBLE
        search_view.visibility = if (isToShow) View.VISIBLE else View.GONE
    }

    private fun showSearchView() {
        val set = TransitionSet().addTransition(Fade()).setInterpolator(FastOutLinearInInterpolator())
        TransitionManager.beginDelayedTransition(transitions_container, set)

        showSearchView(true)
    }

    private fun showSnackbar(msg: String) {
        Snackbar
                .make(main_view, msg, Snackbar.LENGTH_LONG)
                .show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    private fun startSearch() {
        if (item_input_et.text.toString().isEmpty()) {
            hideKeyboard()
            showSnackbar(getString(R.string.empty_search))
            return
        }

        startActivityForResult(Intent(this, TwitterList::class.java).apply {
            putExtra(ServiceConstants.SEARCH_INPUT, item_input_et.text.toString())
        }, ACTIVITY_RESULT_CODE_ERROR)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            ACTIVITY_RESULT_CODE_ERROR ->
                if (data != null) showSnackbar(data.getStringExtra(MainActivity.ERROR_MESSAGE))

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_search_view -> showSearchView()
            R.id.search_button -> startSearch()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: TokenRetrieveEvent) {
        if (!event.success) {
            showSnackbar(getString(R.string.error_get_token))
        }
    }
}
