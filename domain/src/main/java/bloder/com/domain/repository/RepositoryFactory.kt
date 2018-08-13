package bloder.com.domain.repository

import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.repository.resources.SentimentRepository

interface RepositoryFactory {

    fun forSearch() : SearchRepository
    fun forAuth() : AuthRepository
    fun forSentiment() : SentimentRepository
}