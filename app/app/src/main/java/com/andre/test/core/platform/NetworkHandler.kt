package com.andre.test.core.platform

import android.content.Context
import com.andre.test.core.extension.networkInfo
import javax.inject.Inject

class NetworkHandler
@Inject constructor(private val context: Context) {

    val isConnected
        get() =
            context.networkInfo?.isConnectedOrConnecting ?: false
}