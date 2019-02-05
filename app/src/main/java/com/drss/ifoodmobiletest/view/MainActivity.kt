package com.drss.ifoodmobiletest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.ContentView
import com.drss.ifoodmobiletest.R
import com.drss.ifoodmobiletest.repository.TwitterDataSource
import com.twitter.sdk.android.core.models.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ContentView(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
