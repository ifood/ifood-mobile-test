package com.andre.test.core.platform

import android.content.Context
import com.andre.test.core.extension.networkInfo

class NetworkHandler
constructor(private val context: Context) {

    val isConnected
        get() =
            context.networkInfo?.isConnectedOrConnecting ?: false
}