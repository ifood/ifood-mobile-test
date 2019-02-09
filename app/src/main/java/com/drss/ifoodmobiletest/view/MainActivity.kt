package com.drss.ifoodmobiletest.view

import android.os.Bundle
import android.util.Log
import androidx.annotation.ContentView
import com.drss.ifoodmobiletest.R
import com.drss.ifoodmobiletest.repository.TwitterDataSource
import com.drss.ifoodmobiletest.viewmodel.InjectableViewModelFactory
import com.twitter.sdk.android.core.models.Tweet
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ContentView(R.layout.activity_main)
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var injectableViewModelFactory: InjectableViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var call = TwitterDataSource.instance.getUserTimeline("certainnotdave")
        call.enqueue(object: Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.forEach {
                    Log.d("TESTE", it.text)
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Log.e("TESTE", t.toString())
            }
        })
    }
}
