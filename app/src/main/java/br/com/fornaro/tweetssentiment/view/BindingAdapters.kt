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
}