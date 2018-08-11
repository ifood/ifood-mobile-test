package bloder.com.domain.payloads.search

import bloder.com.domain.models.search.Status
import bloder.com.domain.models.search.User
import com.google.gson.annotations.SerializedName


data class StatusPayload(
        @SerializedName("created_at") private val createdAt: String?,
        @SerializedName("text") private val tweet: String?,
        @SerializedName("user") private val user: UserPayload?
) {
    fun toModel() : Status = Status(
            createdAt ?: "",
            tweet ?: "",
            user?.toModel() ?: User("", "")
    )
}