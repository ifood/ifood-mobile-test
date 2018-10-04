package com.test.ifood.twitterhumour.base

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.test.ifood.twitterhumour.R

open class BaseActivity: AppCompatActivity() {

    fun showErrorDialog(msgId: Int) {
        val builder = AlertDialog.Builder(this)
                .setTitle(getString(R.string.general_error))
                .setMessage(getString(msgId))
                .setNeutralButton(getString(R.string.general_ok)) { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
}