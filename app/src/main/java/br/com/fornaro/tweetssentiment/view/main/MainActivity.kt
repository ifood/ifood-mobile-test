package br.com.fornaro.tweetssentiment.view.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fornaro.tweetssentiment.R
import br.com.fornaro.tweetssentiment.databinding.ActivityMainBinding
import br.com.fornaro.tweetssentiment.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        assignViewModel(viewModel)
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
        }
    }
}
