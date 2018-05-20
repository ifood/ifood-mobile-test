package br.com.tweetanalyzer.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class GlideUtil {

    companion object {
        fun glideImage(context: Context, imageUrl: String, imageView: ImageView) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView)
        }
    }
}