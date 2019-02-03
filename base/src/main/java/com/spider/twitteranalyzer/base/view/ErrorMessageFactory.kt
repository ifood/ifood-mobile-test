package com.spider.twitteranalyzer.base.view

import android.accounts.NetworkErrorException
import android.content.Context
import com.spider.twitteranalyzer.base.R
import com.spider.twitteranalyzer.base.domain.NotFoundAccountError
import com.spider.twitteranalyzer.base.domain.PrivateAccountError
import timber.log.Timber

interface ErrorMessageFactory {
    fun create(exception: Throwable): ErrorMessage

    data class ErrorMessage(val message: String)

    class Impl constructor(private val context: Context) : ErrorMessageFactory {
        override fun create(exception: Throwable): ErrorMessage {
            Timber.e(exception, exception.message)
            return when (exception) {
                is PrivateAccountError -> ErrorMessage(message = context.getString(R.string.private_account_error))
                is NotFoundAccountError -> ErrorMessage(message = context.getString(R.string.not_found_account_error))
                is NetworkErrorException -> ErrorMessage(message = context.getString(R.string.network_error))
                else -> ErrorMessage(message = context.getString(R.string.unknown_error))
            }
        }
    }
}