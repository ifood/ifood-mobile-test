package bloder.com.domain.payloads.search

import bloder.com.domain.models.search.User
import com.google.gson.annotations.SerializedName


data class UserPayload(
        @SerializedName("name") private val name: String?,
        @SerializedName("screen_name") private val screenName: String?,
        @SerializedName("profile_image_url_https") private val profileImage: String?
) {
    fun toModel() : User = User(
            name ?: "",
            screenName ?: "",
            profileImage ?: ""
    )
}