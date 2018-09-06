package com.rlino.ifoodtwitterchallenge.ui

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import com.rlino.ifoodtwitterchallenge.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun ViewGroup.showLoadingOverlay() {
    val loadingView = LayoutInflater.from(context).inflate(R.layout.loading_overlay, this, false)
    loadingView.tag = context.getText(R.string.loading_view_tag)
    addView(loadingView)
    loadingView.setOnClickListener { }
    loadingView.bringToFront()
    invalidate()
}

fun ViewGroup.hideLoadingOverlay() {
    removeView(findViewWithTag(context.getText(R.string.loading_view_tag)))
}

inline fun ViewGroup.fadeIn(duration: Long = 150) {
    visibility = View.VISIBLE
    startAnimation(AlphaAnimation(0.0f, 1.0f).apply {
        this.duration = duration
        this.fillAfter = true
    })
}

inline fun ViewGroup.fadeOut(duration: Long = 150) {
    startAnimation(AlphaAnimation(1.0f, 0.0f).apply {
        this.duration = duration
        this.fillAfter = true
    })
    visibility = View.GONE
}

inline fun ViewGroup.blink(delay: Long = 700) {
    fadeIn()
    Handler().postDelayed({
        fadeOut()
    }, delay)
}

inline fun Int.toEmojiString(): String = String(Character.toChars(this))


inline fun <T> Single<T>.defaultSchedulers(): Single<T> = subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread())