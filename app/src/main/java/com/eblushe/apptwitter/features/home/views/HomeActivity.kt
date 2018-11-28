package com.eblushe.apptwitter.features.home.views

import android.os.Bundle
import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.providers.RouterProvider
import com.eblushe.apptwitter.common.views.BaseActivity
import com.eblushe.apptwitter.features.home.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLoadUI()
        onLoadLiveData(viewModel)
    }

    override fun onLoadUI() {
        searchImageView.setOnClickListener {
            hideKeyBoard(userNameEditText)
            onOpenScreenName(screenName = userNameEditText.text.toString())
        }

        userNameEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                userNameTextInputLayout.error = null
            }
        }
    }

    override fun onLoadLiveData(viewModel: HomeViewModel) {
        // TODO("not implemented")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    private fun onOpenScreenName(screenName: String) {
        if (screenName.isBlank()) {
            userNameTextInputLayout.error = getString(R.string.required_field)
            return
        }
        RouterProvider.openUserDetailsScreen(this, screenName)
    }
}
