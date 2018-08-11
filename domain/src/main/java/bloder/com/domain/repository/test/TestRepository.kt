package bloder.com.domain.repository.test

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.repository.test.resources.AuthRepositoryTest
import bloder.com.domain.repository.test.resources.SearchRepositoryTest

class TestRepository : RepositoryFactory {

    override fun forSearch(): SearchRepository = SearchRepositoryTest()
    override fun forAuth(): AuthRepository = AuthRepositoryTest()
}