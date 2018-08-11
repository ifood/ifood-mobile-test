package bloder.com.domain.repository.test.resources

import bloder.com.domain.models.search.Status
import bloder.com.domain.models.search.User
import bloder.com.domain.repository.resources.SearchRepository
import io.reactivex.Single


class SearchRepositoryTest : SearchRepository {

    override fun searchTweets(auth:String, name: String): Single<List<Status>> = Single.just(
        listOf(
                Status("", "Test!", User("Bloder", ""))
        )
    )
}