package com.spider.twitteranalyzer.base.frameworks

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.orhanobut.hawk.Hawk
import timber.log.Timber

class HawkFramework : PluggableFramework {

    override fun plug(app: Application) {
        Hawk.init(app)
                .setStorage(Storage(app, "com.spider.twitteranalyzer.data.v1"))
                .setLogInterceptor { Timber.tag("Storage").d(it) }
                .build()
    }

    private class Storage(context: Context, tag: String) : com.orhanobut.hawk.Storage {

        var preferences: SharedPreferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE)

        override fun <T> put(key: String, value: T): Boolean {
            return getEditor().putString(key, value.toString()).commit()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T> get(key: String): T {
            return preferences.getString(key, null) as T
        }

        override fun delete(key: String): Boolean {
            return getEditor().remove(key).commit()
        }

        override fun contains(key: String): Boolean {
            return preferences.contains(key)
        }

        override fun deleteAll(): Boolean {
            return getEditor().clear().commit()
        }

        override fun count(): Long {
            return preferences.all.size.toLong()
        }

        private fun getEditor(): SharedPreferences.Editor {
            return preferences.edit()
        }

    }
}