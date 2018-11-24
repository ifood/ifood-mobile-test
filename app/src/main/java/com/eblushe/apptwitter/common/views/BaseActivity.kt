package com.eblushe.apptwitter.common.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

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
}