package com.shrpereira.tweetsense.data.remote.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.language.v1.CloudNaturalLanguageScopes
import com.shrpereira.tweetsense.data.R
import com.shrpereira.tweetsense.data.cache.authentication.AccessTokenDataSource
import com.shrpereira.tweetsense.data.model.DataResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

interface GoogleAuthDataSource {
	fun authenticate(): LiveData<DataResource<Unit>>
}

class GoogleAuthDataSourceImpl(
	private val context: Context,
	private val accessTokenDataSource: AccessTokenDataSource
) : GoogleAuthDataSource {

	override fun authenticate(): MutableLiveData<DataResource<Unit>> {
		val stream = context.resources.openRawResource(R.raw.google_credentials)

		val credential = GoogleCredential
			.fromStream(stream)
			.createScoped(CloudNaturalLanguageScopes.all())

		val job = GlobalScope.launch {
			credential.refreshToken()
			accessTokenDataSource.saveGoogleAccessToken(credential.accessToken)
		}

		runBlocking {
			job.start()
		}

		return MutableLiveData<DataResource<Unit>>().also {
			it.value = DataResource.newInstance(null, null)
		}
	}
}