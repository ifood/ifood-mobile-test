package br.com.fornaro.tweetssentiment.view

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.view.View

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
}