package br.com.fornaro.tweetssentiment.view

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("backgroundColor")
    fun View.backgroundColor(color: Int) {
        background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun View.visibleInvisible(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun View.visibleGone(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder", "centerCrop"], requireAll = false)
    fun ImageView.loadImage(url: String?, placeholder: Drawable?, centerCrop: Boolean = false) {
        if (url != null) {
            val options = RequestOptions().placeholder(placeholder)
            if (centerCrop) options.centerCrop()
            Glide.with(context).load(url).apply(options).into(this)
        }
    }
}