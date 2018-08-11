package bloder.com.domain.repository.production.resources

import bloder.com.domain.api.search.SearchApi
import bloder.com.domain.api_response.search.handleSearchResponse
import bloder.com.domain.models.Status
import bloder.com.domain.repository.resources.SearchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchRepositoryProduction : SearchRepository {

    override fun searchTweets(auth: String, name: String): Single<List<Status>> = SearchApi().service()
            .searchTweets(auth, name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .flatMap { response ->
                Single.create<List<Status>> {
                    response.handleSearchResponse(it, response.code())
                }
            }
}