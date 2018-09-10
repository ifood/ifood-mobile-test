package br.com.fornaro.tweetssentiment.view.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.common.AppConstants
import br.com.fornaro.tweetssentiment.databinding.ActivityMainBinding
import br.com.fornaro.tweetssentiment.view.tweets.TweetsActivity
import br.com.fornaro.tweetssentiment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupDoneKeyboardAction()
        setupViewModel()
        assignViewModel(viewModel)
    }

    private fun setupDoneKeyboardAction() {
        usernameText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.seeTweetsButtonClick(v.text.toString())
            }
            false
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.callback = callback
    }

    private fun assignViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

    private val callback = object : MainCallback {
        override fun onEmptyUsername() {
            Toast.makeText(this@MainActivity, R.string.no_username, Toast.LENGTH_SHORT).show()
        }

        override fun onNoInternetConnection() {
            Toast.makeText(this@MainActivity, R.string.no_internet, Toast.LENGTH_SHORT).show()
        }

        override fun showTweetsListScreen(username: String) {
            val intent = Intent(this@MainActivity, TweetsActivity::class.java)
            intent.putExtra(AppConstants.EXTRA_USERNAME, username)
            startActivity(intent)
            finish()
        }
    }
}
