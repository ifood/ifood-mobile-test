package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.services.language.v1.CloudNaturalLanguage
import dagger.Module
import dagger.Provides

@Module
class GoogleModule {

    @Provides
    fun provideCloudNaturalLanguage(): CloudNaturalLanguage {
        return NaturalLanguageServiceProvider.naturalLanguageService
    }

}