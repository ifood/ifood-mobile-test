package com.shrpereira.tweetsense.app.ui.base

import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shrpereira.tweetsense.app.ui.component.ProgressDialog

abstract class BaseActivity : AppCompatActivity() {

	private val fullScreenLoadingDialog: Dialog by lazy {
		ProgressDialog.getLoadingDialog(this)
	}

	protected fun showFullScreenLoading() {
		fullScreenLoadingDialog.show()
	}

	protected fun hideFullScreenLoading() {
		fullScreenLoadingDialog.dismiss()
	}

	protected fun toast(message: String) {
		Toast.makeText(this@BaseActivity, message, Toast.LENGTH_LONG).show()
	}
}