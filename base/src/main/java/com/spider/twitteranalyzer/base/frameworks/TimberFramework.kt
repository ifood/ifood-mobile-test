package com.spider.twitteranalyzer.base.frameworks

import android.app.Application
import timber.log.Timber

/**
 * Created by ufsoares on 2/15/16.
 */

class TimberFramework : PluggableFramework {

    override fun plug(app: Application) {
        Timber.plant(Timber.DebugTree())
    }
}