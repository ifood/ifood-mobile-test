package com.shrpereira.tweetsense.domain.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSource
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.model.TwitterTokenResponse
import com.shrpereira.tweetsense.data.remote.authentication.GoogleAuthDataSource
import com.shrpereira.tweetsense.data.remote.authentication.TwitterAuthDataSource

interface AuthenticationUseCase {

	fun authenticateTwitter(): LiveData<DataResource<Unit>>
	fun authenticateGoogle(): LiveData<DataResource<Unit>>
}

class AuthenticationUseCaseImpl(
	private val accessTokenDataSource: AccessTokenDataSource,
	private val twitterAuthDataSource: TwitterAuthDataSource,
	private val googleAuthDataSource: GoogleAuthDataSource
) : AuthenticationUseCase {

	override fun authenticateTwitter(): LiveData<DataResource<Unit>> {
		accessTokenDataSource.getTwitterAccessToken()?.let {
			return MutableLiveData<DataResource<Unit>>().apply {
				value = DataResource.newInstance(null, null)
			}
		}

		return Transformations.map(twitterAuthDataSource.authenticate()) {
			it.responseData?.authorization?.let { token ->
				accessTokenDataSource.saveTwitterAccessToken(token)
			}
			mapTokenResponse(it)
		}
	}

	override fun authenticateGoogle(): LiveData<DataResource<Unit>> {
		return googleAuthDataSource.authenticate()
	}

	private fun mapTokenResponse(response: DataResource<TwitterTokenResponse>): DataResource<Unit> =
		response.switchTo(null, response.error)
}