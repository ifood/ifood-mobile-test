package com.ifood.challenge.base.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addToComposite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/**
 * Extension function to subscribe on the background thread for a Completable
 * */
fun Completable.performOnBack(): Completable {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Single<T>.performOnBack(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to observe on background io thread for a Single
 * */
fun <T> Single<T>.observeOnBackground(): Single<T> {
    return this.observeOn(Schedulers.computation())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Observable<T>.performOnBack(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to observe on computation thread for a Observable
 * */
fun <T> Observable<T>.observeOnBackground(): Observable<T> {
    return this.observeOn(Schedulers.io())
}
