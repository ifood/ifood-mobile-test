package br.com.tweetanalyzer

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*


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

    private fun searchPerson() {
        val set = TransitionSet().addTransition(Fade()).setInterpolator(FastOutLinearInInterpolator())
        TransitionManager.beginDelayedTransition(transitions_container, set)

        btn_search.visibility = View.GONE
        item_input_et.visibility = View.VISIBLE
    }

    private fun startSearch() {
        //TODO validate input
        startActivity(Intent(this, TwitterList::class.java).apply {
            putExtra(Constant.SEARCH_INPUT, item_input_et.text.toString())
        })
    }
}
