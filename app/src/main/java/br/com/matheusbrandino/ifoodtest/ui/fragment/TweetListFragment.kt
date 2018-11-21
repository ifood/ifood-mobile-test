package br.com.matheusbrandino.ifoodtest.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.matheusbrandino.ifoodtest.R
import br.com.matheusbrandino.ifoodtest.data.viewmodel.GoogleViewModel
import br.com.matheusbrandino.ifoodtest.data.viewmodel.TweetViewModel
import br.com.matheusbrandino.ifoodtest.model.Tweet
import br.com.matheusbrandino.ifoodtest.ui.adapter.TweetAdapter
import br.com.matheusbrandino.ifoodtest.ui.view.SentimentAlert
import kotlinx.android.synthetic.main.fragment_tweet_list.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TweetListFragment : Fragment() {

    private val tweetViewModel: TweetViewModel by sharedViewModel()
    private val goooleViewModel: GoogleViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)

        view.listTweets.adapter = TweetAdapter(tweets(), listener)

        goooleViewModel.sentiment.observe(this, Observer { sentiment ->
            sentiment?.let {
                SentimentAlert(it.score).show(context!!) { goooleViewModel.clean() }
            }
        })

        return view
    }

    private val listener: (Tweet) -> Unit = { goooleViewModel.analyse(it) }

    private fun tweets() = tweetViewModel.tweets().value.orEmpty()


}