package com.andre.test.core.di

import android.app.Application
import android.content.Context
import com.andre.test.core.platform.NetworkHandler
import com.andre.test.features.TwitterRepository
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer
import com.twitter.sdk.android.core.TwitterCore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideNaturalLanguageService(): CloudNaturalLanguage {
        return CloudNaturalLanguage.Builder(
            AndroidHttp.newCompatibleTransport(),
            AndroidJsonFactory(),
            null
        ).setCloudNaturalLanguageRequestInitializer(
            CloudNaturalLanguageRequestInitializer("AIzaSyDqepAUcbxla0TGr-SYlPBNZQj-2gq5gt8")
        ).build()
    }

    @Provides @Singleton fun provideTwitterRepository() : TwitterRepository =
        TwitterRepository(TwitterCore.getInstance().apiClient.searchService, NetworkHandler(application))

}

