package testCommon.utils

import io.reactivex.observers.TestObserver

fun <T> TestObserver<T>.assertCompleted(value: T) {
    awaitTerminalEvent()
    assertValue(value)
    assertComplete()
    assertTerminated()
    assertNoErrors()
}

fun <T> TestObserver<T>.assertWithError(error: Throwable) {
    awaitTerminalEvent()
    assertError(error)
    assertTerminated()
    assertNotComplete()
}

fun <T> TestObserver<T>.assertWithError(error: Class<out Throwable>) {
    awaitTerminalEvent()
    assertError(error)
    assertTerminated()
    assertNotComplete()
}
