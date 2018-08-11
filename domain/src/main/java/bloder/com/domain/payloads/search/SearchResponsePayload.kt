package bloder.com.domain.payloads.search

import bloder.com.domain.models.search.Status
import com.google.gson.annotations.SerializedName

data class SearchResponsePayload(
        @SerializedName("statuses") private val statuses: List<StatusPayload>?
) {
    fun toModel() : List<Status> = statuses?.let {
        it.map {
            it.toModel()
        }
    } ?: listOf()
}