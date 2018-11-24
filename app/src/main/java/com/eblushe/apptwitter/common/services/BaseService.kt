package com.eblushe.apptwitter.common.services

abstract class BaseService<T> {
    abstract val endpointType : Class<T>
    abstract fun get() : T
}