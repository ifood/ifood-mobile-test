package com.rlino.ifoodtwitterchallenge.logger

import com.crashlytics.android.Crashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if(priority in 5..7) {
            t?.let { Crashlytics.logException(t) } ?: Crashlytics.log(priority, tag, message)
        }
    }

}