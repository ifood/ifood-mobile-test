package bloder.com.domain.payloads.search

import bloder.com.domain.models.search.User
import com.google.gson.annotations.SerializedName


data class UserPayload(
        @SerializedName("name") private val name: String?,
        @SerializedName("profile_background_image_url_https") private val profileImage: String?
) {
    fun toModel() : User = User(
            name ?: "",
            profileImage ?: ""
    )
}