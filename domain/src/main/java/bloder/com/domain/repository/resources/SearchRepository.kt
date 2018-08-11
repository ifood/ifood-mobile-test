package bloder.com.domain.repository.resources

import bloder.com.domain.models.search.Status
import io.reactivex.Single

interface SearchRepository {

    fun searchTweets(auth: String, name: String) : Single<List<Status>>
}