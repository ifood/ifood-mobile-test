package com.eblushe.apptwitter.common.models

class DataHolder<T>(var value: T? = null, var state: State = State.EMPTY, val error: Exception? = null) {
    enum class State{
        EMPTY,
        LOADING,
        LOADED,
        RELOADING,
        FINISHED,
        ERROR
    }
}