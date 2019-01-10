package com.drury.twittermoodanalyzer.extension

import android.support.v4.view.ViewCompat
import android.view.View
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject
import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd/MM/yyy")
    return format.format(this)
}

fun View.fadeIn(duration: Long): Completable {
    val animationSubject = CompletableSubject.create()
    return animationSubject.doOnSubscribe {
        ViewCompat.animate(this)
                .setDuration(duration)
                .alpha(1f)
                .withEndAction {
                    animationSubject.onComplete()
                }
    }
}