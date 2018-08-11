package bloder.com.domain.api_response.search

import bloder.com.domain.api_response.ResponseGod
import bloder.com.domain.models.Status
import bloder.com.domain.payloads.SearchResponsePayload
import io.reactivex.SingleEmitter
import retrofit2.Response

class SearchResponseGod(
        private val response: Response<SearchResponsePayload>,
        private val emmiter: SingleEmitter<List<Status>>
) : ResponseGod {

    override fun on200() {
        response.body()?.let {
            emmiter.onSuccess(it.toModel())
        }
    }

    override fun unknown(code: Int) {
        emmiter.onError(SearchError(SEARCH_ERROR.UNKNOWN, "Unknown problem!"))
    }
}

fun Response<SearchResponsePayload>.handleSearchResponse(emmiter: SingleEmitter<List<Status>>, code: Int) {
    SearchResponseGod(this, emmiter).handle(code)
}