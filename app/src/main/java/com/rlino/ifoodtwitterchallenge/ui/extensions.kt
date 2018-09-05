package com.rlino.ifoodtwitterchallenge.ui

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import com.rlino.ifoodtwitterchallenge.R
import kotlinx.android.synthetic.main.activity_timeline_search.*


fun ViewGroup.showLoadingOverlay() {
    val loadingView = LayoutInflater.from(context).inflate(R.layout.loading_overlay, this, false)
    loadingView.tag = "LOADING_VIEW"
    addView(loadingView)
    loadingView.setOnClickListener { }
    loadingView.bringToFront()
    invalidate()
}

fun ViewGroup.hideLoadingOverlay() {
    removeView(findViewWithTag("LOADING_VIEW"))
}

inline fun ViewGroup.blink(delay: Long = 700) {
    fadeIn()
    Handler().postDelayed({
        fadeOut()
    }, delay)
}

inline fun ViewGroup.fadeIn(duration: Long = 150) {
    visibility = View.VISIBLE
    val alpha = AlphaAnimation(0.0f, 1.0f)
    alpha.duration = duration
    alpha.fillAfter = true
    startAnimation(alpha)
}

inline fun ViewGroup.fadeOut(duration: Long = 150) {
    val alpha = AlphaAnimation(1.0f, 0.0f)
    alpha.duration = duration
    alpha.fillAfter = true
    startAnimation(alpha)
    visibility = View.GONE
}

inline fun Int.toEmojiString(): String = String(Character.toChars(this))