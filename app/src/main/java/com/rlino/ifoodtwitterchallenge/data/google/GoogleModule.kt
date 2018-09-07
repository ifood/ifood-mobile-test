package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer
import com.rlino.ifoodtwitterchallenge.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GoogleModule {

    @Provides
    @Singleton
    fun provideCloudNaturalLanguage(): CloudNaturalLanguage {
        return CloudNaturalLanguage.Builder(
                AndroidHttp.newCompatibleTransport(),
                AndroidJsonFactory(),
                null
        ).setCloudNaturalLanguageRequestInitializer(
                CloudNaturalLanguageRequestInitializer(BuildConfig.GOOGLE_API_KEY)
        ).build()
    }

}