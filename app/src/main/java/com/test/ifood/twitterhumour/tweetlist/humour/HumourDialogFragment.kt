package com.test.ifood.twitterhumour.tweetlist.humour

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.databinding.DialogFragmentHumourBinding
import com.test.ifood.twitterhumour.tweetlist.humour.viewmodel.HumourViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HumourDialogFragment: DialogFragment() {

    companion object {
        val TAG = HumourDialogFragment::class.java.simpleName
        private const val TWEET_SENTIMENT = "tweet.sentiment"

        fun newInstance(sentiment: String): HumourDialogFragment {
            val fragment = HumourDialogFragment()
            val args = Bundle()

            args.putString(TWEET_SENTIMENT, sentiment)
            fragment.arguments = args

            return fragment
        }
    }

    @Inject
    lateinit var viewModel: HumourViewModel

    lateinit var binding: DialogFragmentHumourBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_humour, container, false)

        if (!arguments?.containsKey(TWEET_SENTIMENT)!!) {
            throw IllegalStateException("Missing sentiment.")
        }

        val sentiment = arguments!!.getString(TWEET_SENTIMENT)
        viewModel.sentiment = sentiment
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }
}