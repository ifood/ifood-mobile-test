package bloder.com.twitter.search_user

import android.annotation.SuppressLint
import android.view.View
import bloder.com.domain.models.search.Status
import bloder.com.twitter.FullScreenBottomSheet
import bloder.com.twitter.R


class SearchedTweetsDialog @SuppressLint("ValidFragment") constructor
(private val tweets: List<Status>) : FullScreenBottomSheet() {

    constructor() : this(listOf())

    override fun viewToInflate(): Int = R.layout.searched_user_dialog

    override fun onViewInflated(view: View) {
        tweets
    }
}