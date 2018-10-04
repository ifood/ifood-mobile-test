package com.test.ifood.twitterhumour.welcome

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.databinding.ActivityWelcomeBinding
import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.welcome.view.WelcomeView
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import dagger.android.AndroidInjection
import io.reactivex.FlowableSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class WelcomeActivity : AppCompatActivity(), WelcomeView {

    private lateinit var binding: ActivityWelcomeBinding

    @Inject
    lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.viewModel = viewModel
    }

    override fun findTweets() {
        viewModel.searchForTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showNoResultsDialog() },{ showNoResultsDialog() })
    }

    private fun showNoResultsDialog() {

        val builder = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("No tweets found.")
                .setNeutralButton("OK") {dialog, _ -> dialog.dismiss() }

        builder.show()

    }

}
