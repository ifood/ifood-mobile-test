package com.shrpereira.tweetsense.app.ui.main

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shrpereira.tweetsense.app.R
import com.shrpereira.tweetsense.app.common.extension.clearAndAddAll
import com.shrpereira.tweetsense.app.common.extension.inflate
import com.shrpereira.tweetsense.app.common.glide.GlideApp
import com.shrpereira.tweetsense.domain.model.TweetModel
import kotlinx.android.synthetic.main.list_item_tweet.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class TweetsListAdapter(
	private val list: MutableList<TweetModel>,
	private val listItemClickAction: (TweetModel) -> Unit
) : RecyclerView.Adapter<TweetsListAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ViewHolder(parent.inflate(R.layout.list_item_tweet))

	override fun getItemCount(): Int = list.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(list[position], listItemClickAction)
	}

	fun updateList(newList: List<TweetModel>) {
		list.clearAndAddAll(newList)
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		@SuppressLint("SimpleDateFormat")
		fun bind(
			item: TweetModel,
			listener: (TweetModel) -> Unit
		) = with(itemView) {

			setOnClickListener { listener(item) }
			textViewTitle.text = item.text
			val parsedDate = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(item.createdAt)
			textViewCreatedAt.text = DateFormat.getDateInstance().format(parsedDate)

			item.user.photoUrl?.let {
				GlideApp.with(context)
					.load(it)
					.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
					.placeholder(R.drawable.ic_user)
					.centerCrop()
					.into(itemView.imageViewPhoto)
			}
		}
	}
}