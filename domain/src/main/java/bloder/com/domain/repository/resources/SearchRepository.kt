package bloder.com.domain.repository.resources

import bloder.com.domain.models.Status
import io.reactivex.Single


interface SearchRepository {

    fun searchTweets(name: String) : Single<List<Status>>
}