package com.eblushe.apptwitter.common.extensions

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.toast(@StringRes stringRes: Int) {
    Toast.makeText(this, getString(stringRes), Toast.LENGTH_SHORT).show()
}


fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}