package bloder.com.domain.api_response.search

import bloder.com.domain.api_response.ResponseGod
import bloder.com.domain.models.search.Status
import bloder.com.domain.payloads.search.StatusPayload
import io.reactivex.SingleEmitter
import retrofit2.Response

class SearchResponseGod(
        private val response: Response<List<StatusPayload>>,
        private val emmiter: SingleEmitter<List<Status>>
) : ResponseGod {

    override fun on200() {
        response.body()?.let {
            emmiter.onSuccess(it.map { it.toModel() })
        }
    }

    override fun on404() {
        emmiter.onError(SearchError(SEARCH_ERROR.NOT_FOUND, "User not found"))
    }

    override fun unknown(code: Int) {
        emmiter.onError(SearchError(SEARCH_ERROR.UNKNOWN, "Unknown problem!"))
    }
}

fun Response<List<StatusPayload>>.handleSearchResponse(emmiter: SingleEmitter<List<Status>>, code: Int) {
    SearchResponseGod(this, emmiter).handle(code)
}