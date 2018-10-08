package com.study.vipoliveira.tweetanalyst.ui.tweet

import android.support.v7.widget.RecyclerView
import android.view.View
import com.study.vipoliveira.tweetanalyst.model.TweetResponse

class TweetListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: TweetResponse) = with(itemView) {
        with(item){
//
//            user_name.text = user.login
//            pull_request_title.text = title
//            pull_request_description.text = body
//            creation_date.text = itemView.context.getString(R.string.created_at, DateUtils.toSimpleString(createdAt))
//            Glide.with(itemView).load(user.avatarUrl).apply(RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.DATA)).into(user_image)
        }
    }
}