package br.com.tweetanalyzer.presenter.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.models.TwitterModel

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
class TwitterListAdapter(val context: Context,
                         var tweets: MutableList<TwitterModel>) :
        RecyclerView.Adapter<TwitterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.twitter_list_row, parent, false))

    override fun getItemCount(): Int = tweets.count()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO set variables into view
    }


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        //variables
    }
}