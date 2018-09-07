package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer
import com.rlino.ifoodtwitterchallenge.BuildConfig

object NaturalLanguageServiceProvider {

    val naturalLanguageService: CloudNaturalLanguage by lazy {
        CloudNaturalLanguage.Builder(
                AndroidHttp.newCompatibleTransport(),
                AndroidJsonFactory(),
                null
        ).setCloudNaturalLanguageRequestInitializer(
                CloudNaturalLanguageRequestInitializer(BuildConfig.GOOGLE_API_KEY)
        ).build()
    }

}
