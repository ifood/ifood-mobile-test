package com.ifood.challenge.base.extensions.view

import android.view.View

const val ANIMATION_DURATION_DEFAULT = 350L
const val ANIMATION_DURATION_LONG = 1000L

fun View?.fadeOut(
    duration: Long = ANIMATION_DURATION_DEFAULT,
    onAnimationFinished: () -> Unit = {}
) {
    this?.apply {
        alpha = 1F
        animate().alpha(0F)
            .setDuration(duration)
            .withEndAction { onAnimationFinished() }
            .start()
    }
}
