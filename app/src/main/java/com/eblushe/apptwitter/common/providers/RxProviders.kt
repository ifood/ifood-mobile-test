package com.eblushe.apptwitter.common.providers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface  RxProvider {
    fun io() : Scheduler
    fun computation() :  Scheduler
    fun newThread() : Scheduler
    fun trampoline() : Scheduler
    fun mainThread() : Scheduler
    fun addDisposable(disposable: Disposable) : Boolean
    fun removeDisposable(disposable: Disposable) : Boolean
    fun clearAllDisposable()
}

class SchedulerProvider(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    ) : RxProvider {
    override fun io() = Schedulers.io()
    override fun computation() = Schedulers.computation()
    override fun newThread() = Schedulers.newThread()
    override fun trampoline() = Schedulers.trampoline()
    override fun mainThread() = AndroidSchedulers.mainThread()
    override fun addDisposable(disposable: Disposable) : Boolean =  compositeDisposable.add(disposable)
    override fun removeDisposable(disposable: Disposable) : Boolean = compositeDisposable.remove(disposable)
    override fun clearAllDisposable() = compositeDisposable.clear()
}

class SchedulerProviderMock(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    ) : RxProvider {
    override fun io() = Schedulers.trampoline()
    override fun computation() = Schedulers.trampoline()
    override fun newThread() = Schedulers.trampoline()
    override fun trampoline() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
    override fun addDisposable(disposable: Disposable) : Boolean = compositeDisposable.add(disposable)
    override fun removeDisposable(disposable: Disposable) : Boolean = compositeDisposable.remove(disposable)
    override fun clearAllDisposable() = compositeDisposable.clear()
}