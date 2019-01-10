package com.drury.twittermoodanalyzer.view.component


import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import android.view.View
import com.drury.twittermoodanalyzer.R

class CustomAlertDialog {

    fun withCustomStyle(context: Context,
                        alertTitle: String,
                        alertMessage: String) {

        val onButtonClick = { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
        }

        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom))

        with(builder)
        {
            setTitle(alertTitle)
            setMessage(alertMessage)
            setPositiveButton("OK", DialogInterface.OnClickListener(function = onButtonClick))
            show()
        }
    }
}