package bloder.com.domain

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.production.ProductionRepository
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
}