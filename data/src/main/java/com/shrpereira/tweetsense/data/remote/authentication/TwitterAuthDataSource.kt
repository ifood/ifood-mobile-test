package com.shrpereira.tweetsense.data.remote.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.leonardoxh.livedatacalladapter.Resource
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.model.TwitterTokenResponse
import com.shrpereira.tweetsense.data.remote.RetrofitBuilder
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val ACCESS_TOKEN = "client_credentials"

interface TwitterAuthDataSource {

	fun authenticate(): LiveData<DataResource<TwitterTokenResponse>>
}

interface TwitterOAuthAPI {

	@FormUrlEncoded
	@POST("oauth2/token")
	fun requestToken(@Field("grant_type") grantType: String): LiveData<Resource<TwitterTokenResponse>>
}

class TwitterAuthDataSourceImpl(
	private val retrofitBuilder: RetrofitBuilder
) : TwitterAuthDataSource {

	override fun authenticate(): LiveData<DataResource<TwitterTokenResponse>> {
		val oAuthDataResource = retrofitBuilder
			.build(TwitterOAuthAPI::class.java)
			.requestToken(ACCESS_TOKEN)

		return Transformations.map(oAuthDataResource) { DataResource.getFromResource(it) }
	}
}