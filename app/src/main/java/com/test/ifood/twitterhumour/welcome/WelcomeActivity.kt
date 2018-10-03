package com.test.ifood.twitterhumour.welcome

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.databinding.ActivityWelcomeBinding
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    @Inject lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.viewModel = viewModel
    }
}
