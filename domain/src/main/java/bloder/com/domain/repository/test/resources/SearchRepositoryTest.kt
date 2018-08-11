package bloder.com.domain.repository.test.resources

import bloder.com.domain.models.Status
import bloder.com.domain.models.User
import bloder.com.domain.repository.resources.SearchRepository
import io.reactivex.Single


class SearchRepositoryTest : SearchRepository {

    override fun searchTweets(name: String): Single<List<Status>> = Single.create<List<Status>> {
        listOf(
                Status("", "Test!", User("Bloder", ""))
        )
    }
}