package com.eblushe.apptwitter.common.views

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    protected abstract val viewModel: VM
    protected abstract fun onLoadUI()
    protected abstract fun onLoadLiveData(viewModel: VM)
    protected abstract fun getLayoutId(): Int

    protected fun showLoading(view: View?) {
        view?.visibility = View.VISIBLE
    }

    protected fun hideLoading(view: View?) {
        view?.visibility = View.GONE
    }

    fun showSnackBar(view: View, @StringRes message: Int, @StringRes actionText: Int, action: (view: View) -> Unit) {
        val snackBar = Snackbar.make(view, getString(message), Snackbar.LENGTH_LONG)
        snackBar.setAction(actionText, action)
        snackBar.show()
    }

    fun hideKeyBoard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    protected fun <T: Any> getParam(key: String, callback: (bundle: Bundle, key: String) -> T) : T? {
        var value: T? = null
        val containsValue = intent.extras?.containsKey(key) ?: false

        if (containsValue) {
            value = intent.extras?.let { callback(it, key) }
        }

        return value
    }
}