package bloder.com.domain.repository.production

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.production.resources.AuthRepositoryProduction
import bloder.com.domain.repository.production.resources.SearchRepositoryProduction
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository

class ProductionRepository : RepositoryFactory {

    override fun forSearch(): SearchRepository = SearchRepositoryProduction()
    override fun forAuth(): AuthRepository = AuthRepositoryProduction()
}