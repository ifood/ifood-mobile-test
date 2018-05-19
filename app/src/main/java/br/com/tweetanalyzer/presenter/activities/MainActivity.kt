package br.com.tweetanalyzer.presenter.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import br.com.tweetanalyzer.PreferenceController
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.TwitterService
import br.com.tweetanalyzer.eventbus.TokenRetrieveEvent
import br.com.tweetanalyzer.util.Constant
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_search -> searchPerson()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener(this)
        item_input_et.setOnEditorActionListener({ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                startSearch()
                true
            } else
                false
        })
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

        if (PreferenceController.getToken(this).isEmpty()) {
            startService(Intent(this, TwitterService::class.java).apply {
                putExtra(Constant.JOB_TYPE, Constant.JOB_TYPE_GET_AUTH)
            })
        }
    }

    override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)
    }

    private fun searchPerson() {
        val set = TransitionSet().addTransition(Fade()).setInterpolator(FastOutLinearInInterpolator())
        TransitionManager.beginDelayedTransition(transitions_container, set)

        btn_search.visibility = View.GONE
        item_input_et.visibility = View.VISIBLE
    }

    private fun startSearch() {
        startActivity(Intent(this, TwitterList::class.java).apply {
            putExtra(Constant.SEARCH_INPUT, item_input_et.text.toString())
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: TokenRetrieveEvent) {
        if (!event.success) {
            //TODO build a better error message
            Toast.makeText(this, "Error Initializing", Toast.LENGTH_LONG).show()
        }
    }
}
