package com.ifood.challenge.home.presentation

import com.ifood.challenge.R
import com.ifood.challenge.base.extensions.view.loadImage
import com.ifood.challenge.base.presentation.BaseRecyclerViewAdapter
import com.ifood.challenge.home.model.TwitterUser
import kotlinx.android.synthetic.main.twitter_user_item.view.*

class TwitterUserAdapter(onTwitterUserClick: (TwitterUser) -> Unit) :
    BaseRecyclerViewAdapter<TwitterUser>(
        layoutResId = R.layout.twitter_user_item, bindView = { view, item ->
            view.twitterUserImage.loadImage(item.profileImageUrlHttps)
            view.twitterUserName.text = item.screenName
            view.twitterUserLocation.text = item.location
            view.setOnClickListener { onTwitterUserClick(item) }
        }
    )
