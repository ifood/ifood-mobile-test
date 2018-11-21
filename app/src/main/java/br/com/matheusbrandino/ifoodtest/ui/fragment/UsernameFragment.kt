package br.com.matheusbrandino.ifoodtest.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.matheusbrandino.ifoodtest.R
import br.com.matheusbrandino.ifoodtest.data.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.fragment_username.*
import kotlinx.android.synthetic.main.fragment_username.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UsernameFragment : Fragment() {

    val viewModel: TweetViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_username, container, false)

        view.fabSearch.setOnClickListener { searchTweets() }

        viewModel.tweets().observe(this, Observer {
            group.visibility = View.GONE
        })

        viewModel.error().observe(this, Observer {
            group.visibility = View.GONE
        })

        return view
    }

    private fun searchTweets() {

        val username = inputUsername.text.toString()

        if (username.isNotEmpty()) {

            tilUsername.isErrorEnabled = false

            viewModel.searchTweetsFrom(username)

            group.visibility = View.VISIBLE

        } else {

            tilUsername.error = getString(R.string.invalid_username)
        }
    }

}
