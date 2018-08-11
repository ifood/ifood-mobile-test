package bloder.com.domain.payloads

import bloder.com.domain.models.Status
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