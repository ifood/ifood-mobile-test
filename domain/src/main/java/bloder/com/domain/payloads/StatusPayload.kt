package bloder.com.domain.payloads

import bloder.com.domain.models.Status
import bloder.com.domain.models.User
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