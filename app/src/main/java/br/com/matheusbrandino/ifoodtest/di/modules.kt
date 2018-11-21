package br.com.matheusbrandino.ifoodtest.di

import br.com.matheusbrandino.ifoodtest.api.google.GoogleApi
import br.com.matheusbrandino.ifoodtest.api.google.RetrofitInitializerForGoogle
import br.com.matheusbrandino.ifoodtest.api.twitter.RetrofitInitializerForTwitter
import br.com.matheusbrandino.ifoodtest.api.twitter.TwitterApi
import br.com.matheusbrandino.ifoodtest.data.repository.GoogleRepository
import br.com.matheusbrandino.ifoodtest.data.repository.TweetRepository
import br.com.matheusbrandino.ifoodtest.data.viewmodel.GoogleViewModel
import br.com.matheusbrandino.ifoodtest.data.viewmodel.TweetViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


private val viewmodels: Module = module {

    viewModel { TweetViewModel(get()) }
    viewModel { GoogleViewModel(get()) }
}

private val respositories: Module = module {

    single { TweetRepository(get()) }
    single { GoogleRepository(get()) }
}


private val apis: Module = module {
    single { TwitterApi(get("twitter")) }
    single { GoogleApi(get("google")) }

    single("google") { RetrofitInitializerForGoogle().retrofit }
    single("twitter") { RetrofitInitializerForTwitter().retrofit }

}

val modules = listOf(apis, respositories, viewmodels)