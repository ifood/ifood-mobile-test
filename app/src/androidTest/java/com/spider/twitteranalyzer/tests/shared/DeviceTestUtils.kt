package com.spider.twitteranalyzer.tests.shared

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice

object DeviceTestUtils {
    fun wakeUp() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).apply {
            wakeUp()
            swipe(68, 848, 459, 236, 20)
        }
    }
}