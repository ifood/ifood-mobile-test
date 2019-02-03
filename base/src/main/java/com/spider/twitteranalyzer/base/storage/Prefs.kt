package com.spider.twitteranalyzer.base.storage

import com.orhanobut.hawk.Hawk
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Prefs<T>(
        private val namespace: String,
        private val default: T
) : ReadWriteProperty<Any?, T> {

    private val cache: CachedProperty<T> = CachedProperty {
        Hawk.get<T>(namespace, default)
    }

    private val currentValue: T by cache

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
            currentValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (currentValue != value) {
            persistData(value)
            cache.invalidate()
        }
    }

    private fun persistData(value: T) {
        if (value == null)
            Hawk.delete(namespace)
        else
            Hawk.put(namespace, value)
    }
}