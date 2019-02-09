package movile.marcus.com.br.moviletest.model.data

import com.squareup.moshi.Json

data class UserResult(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "screen_name") val screenName: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "profile_image_url") val profileImageUrl: String?,
    @field:Json(name = "profile_banner_url") val profileBannerUrl: String?
)

class User(private val tweetList: List<TweetData>?) {
    fun getUser(): UserResult? = tweetList?.get(0)?.user
}