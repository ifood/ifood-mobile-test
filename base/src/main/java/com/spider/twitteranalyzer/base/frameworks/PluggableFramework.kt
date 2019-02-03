package com.spider.twitteranalyzer.base.frameworks

import android.app.Application

interface PluggableFramework {

    fun plug(app: Application)

}