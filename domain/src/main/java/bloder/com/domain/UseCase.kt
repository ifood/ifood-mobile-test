package bloder.com.domain

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.production.ProductionRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class UseCase {

    private var repository: RepositoryFactory = ProductionRepository()
    private var disposables = CompositeDisposable()
    private var observeScheduler: Scheduler? = AndroidSchedulers.mainThread()

    fun getScheduler() : Scheduler = observeScheduler ?: AndroidSchedulers.mainThread()

    fun dispose() {
        disposables.clear()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun testWith(repository: RepositoryFactory) {
        this.repository = repository
    }
}