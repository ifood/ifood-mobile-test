package com.eblushe.apptwitter.common.views

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {
    protected abstract val viewModel: VM
    protected abstract fun onLoadUI()
    protected abstract fun onLoadLiveData(viewModel: VM)
    protected abstract fun getLayoutId(): Int
}