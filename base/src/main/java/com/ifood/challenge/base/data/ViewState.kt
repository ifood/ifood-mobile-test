package com.ifood.challenge.base.data

sealed class ViewState {
    object Loading : ViewState()
    data class Complete<T>(val response: T) : ViewState()
    data class Failed(val error: Throwable? = null) : ViewState()
}

inline fun <reified T> ViewState.Complete<*>?.getResponse(): T {
    return (this?.response as T)
}
