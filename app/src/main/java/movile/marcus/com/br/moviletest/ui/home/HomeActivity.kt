package movile.marcus.com.br.moviletest.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.ui.BaseActivity

class HomeActivity : BaseActivity() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init() {
        initObservers()

        if (homeViewModel.tweetResult.value == null) {
            homeViewModel.getTweetByUser("globoesporte")
        }
    }

    private fun initObservers() {
        homeViewModel.tweetResult.observeResource(this, onSuccess = {

        }, onError = {

        })
    }
}