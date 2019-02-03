package com.spider.twitteranalyzer.base.injection.qualifiers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class TwitterOkHttpBasicOAuth
