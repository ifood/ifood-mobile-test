package bloder.com.twitter.di

import bloder.com.presentation.twitter.auth.AuthViewModel
import bloder.com.presentation.twitter.search.SearchViewModel
import bloder.com.presentation.twitter.sentiment.SentimentViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val presentationModule = applicationContext {
    viewModel { AuthViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SentimentViewModel(get()) }
}