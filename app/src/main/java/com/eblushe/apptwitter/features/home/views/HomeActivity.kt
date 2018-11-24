package com.eblushe.apptwitter.features.home.views

import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.views.BaseActivity
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()

    override fun onLoadUI() {
        // TODO("not implemented")
    }

    override fun onLoadLiveData(viewModel: HomeViewModel) {
        // TODO("not implemented")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }
}
