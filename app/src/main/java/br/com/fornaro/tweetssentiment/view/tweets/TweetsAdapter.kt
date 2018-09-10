package br.com.fornaro.tweetssentiment.view.tweets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.fornaro.tweetssentiment.databinding.ItemTweetBinding
import br.com.fornaro.tweetssentiment.model.Tweet

class TweetsAdapter(private val listener: TweetsAdapter.OnTweetListener) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {

    interface OnTweetListener {
        fun analyze(tweet: Tweet)
    }

    private val list = mutableListOf<Tweet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTweetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    fun setData(data: List<Tweet>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun analyzedFinished(tweet: Tweet) {
        notifyItemChanged(list.indexOf(tweet))
    }

    class ViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tweet: Tweet, listener: OnTweetListener) {
            binding.tweet = tweet
            binding.isAnalyzing = false
            binding.executePendingBindings()

            binding.analyzeLayout?.analyzeButton?.setOnClickListener {
                binding.isAnalyzing = true
                listener.analyze(tweet)
            }
        }
    }
}