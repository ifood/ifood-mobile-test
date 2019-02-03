package com.spider.twitteranalyzer.base.storage

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CachedProperty<out T>(private val loader: () -> T) : ReadOnlyProperty<Any, T> {
    private var cachedValue: CachedValue<T> = CachedValue.Invalid

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val cachedValue = this.cachedValue
        return when (cachedValue) {
            CachedValue.Invalid -> loader().also { this.cachedValue = CachedValue.Value(it) }
            is CachedValue.Value<T> -> cachedValue.value
        }
    }

    fun invalidate() {
        cachedValue = CachedValue.Invalid
    }

    @Suppress("unused")
    sealed class CachedValue<out T> {
        object Invalid : CachedValue<Nothing>()
        class Value<out T>(val value: T) : CachedValue<T>()
    }
}