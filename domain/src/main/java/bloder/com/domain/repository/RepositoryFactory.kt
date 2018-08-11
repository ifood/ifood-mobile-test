package bloder.com.domain.repository

import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository

interface RepositoryFactory {

    fun forSearch() : SearchRepository
    fun forAuth() : AuthRepository
}