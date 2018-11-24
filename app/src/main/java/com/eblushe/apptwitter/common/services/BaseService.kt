package com.eblushe.apptwitter.common.services

abstract class BaseService<T> {
    protected abstract val endpoint : T
}