package bloder.com.domain

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.production.ProductionRepository
import bloder.com.domain.repository.test.TestRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class UseCase {

    protected var repository: RepositoryFactory = ProductionRepository()
    private var disposables = CompositeDisposable()

    fun dispose() = disposables.clear()

    fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    fun testWith(repository: RepositoryFactory) {
        this.repository = repository
    }

    fun test() {
        this.repository = TestRepository()
        this.disposables = CompositeDisposable()
    }
}