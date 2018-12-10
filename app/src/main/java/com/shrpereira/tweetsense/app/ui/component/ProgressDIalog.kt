package com.shrpereira.tweetsense.app.ui.component

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.shrpereira.tweetsense.app.R

object ProgressDialog {

	fun getLoadingDialog(context: Context): Dialog {

		val progressDialog = Dialog(context, R.style.ProgressBarDialog)
		if (progressDialog.window != null) {
			progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		}
		progressDialog.setContentView(R.layout.dialog_progress_layout)
		progressDialog.setCancelable(false)
		progressDialog.setCanceledOnTouchOutside(false)
		return progressDialog
	}
}
