package com.test.ifood.twitterhumour.base

import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.test.ifood.twitterhumour.R
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable


open class BaseActivity: DaggerAppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()

    protected fun showErrorDialog(msgId: Int) {
        val dialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.general_error))
                .setMessage(getString(msgId))
                .setNeutralButton(getString(R.string.general_ok)) { dialog, _ -> dialog.dismiss() }
                .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(this, R.color.blue_dark_twitter))
        }

        dialog.show()
    }

    protected fun setupToolbar(toolbar: Toolbar) {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.tweet_list_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.dispose()
    }
}