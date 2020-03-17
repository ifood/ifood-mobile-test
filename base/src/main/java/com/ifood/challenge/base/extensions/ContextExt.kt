package com.ifood.challenge.base.extensions

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Extension property to get a [NetworkInfo]
 *
 * @return The active network info in the Android Framework
 */
val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.getColorRes(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)

fun Activity.toast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, stringResId, duration).show()
