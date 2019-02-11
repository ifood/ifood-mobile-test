package com.drss.ifoodmobiletest.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.drss.ifoodmobiletest.R
import com.drss.ifoodmobiletest.databinding.ActivityMainBinding
import com.drss.ifoodmobiletest.view.adapters.TweetsRecyclerViewAdapter
import com.drss.ifoodmobiletest.view.decoration.TweetsItemDecoration
import com.drss.ifoodmobiletest.viewmodel.UserTweetsViewModel
import com.twitter.sdk.android.core.models.Tweet
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var injectableViewModelFactory: ViewModelProvider.Factory
    lateinit var mainBinding : ActivityMainBinding

    var listAdapter: TweetsRecyclerViewAdapter = TweetsRecyclerViewAdapter()

    private lateinit var userTweetsViewModel: UserTweetsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setupViews()
    }

    private fun setupViewModel() {
        userTweetsViewModel = ViewModelProviders.of(this, injectableViewModelFactory)[UserTweetsViewModel::class.java]
        userTweetsViewModel.searchUserResult.observe(this, Observer { list -> onSearchUserResult(list)})
        mainBinding.viewModel = userTweetsViewModel
    }

    private fun setupViews() {
        tweets_recyclerview.adapter = listAdapter
        tweets_recyclerview.layoutManager = LinearLayoutManager(this)
        tweets_recyclerview.addItemDecoration(TweetsItemDecoration(resources.getDimensionPixelSize(R.dimen.vertical_separation_height)))
    }

    private fun onSearchUserResult(tweets : List<Tweet>){
        listAdapter.setData(tweets)
    }

}
