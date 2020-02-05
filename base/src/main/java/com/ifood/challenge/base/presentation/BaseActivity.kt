package com.ifood.challenge.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ifood.challenge.base.R
import com.ifood.challenge.base.common.exception.AppException
import com.ifood.challenge.base.common.exception.EssentialParamMissingException
import com.ifood.challenge.base.common.exception.HttpError
import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.base.di.base
import com.ifood.challenge.base.extensions.toast
import com.ifood.challenge.base.extensions.view.isVisible
import com.ifood.challenge.base.presentation.views.EmptyView
import com.ifood.challenge.base.presentation.views.ErrorView
import com.ifood.challenge.base.presentation.views.SkeletonView
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutResId: Int

    open val baseSkeletonView: SkeletonView? = null
    open val baseErrorView: ErrorView? = null
    open val baseEmptyView: EmptyView? = null
    val viewModelFactory: ViewModelProvider.Factory by lazy {
        base().viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }

    /**
     * Check commons exception between application
     *
     * @param appException The exception returned from backend
     */
    fun checkResponseException(
        appException: AppException?
    ) {
        Timber.e(appException)
        when (appException) {
            NetworkError -> onNetworkWithoutConnection()
            HttpError -> onHttpError()
            is EssentialParamMissingException -> onHttpError()
            else -> onUnknownError()
        }
        loading(false)
    }

    /**
     * Function called when handled a Http generic exception
     */
    open fun onHttpError() {
        baseErrorView?.showError()
    }

    /**
     * Function called when there is no internet connection
     */
    open fun onNetworkWithoutConnection() {
        baseErrorView?.apply { showConnectionError() } ?: showToastConnectionError()
    }

    private fun showToastConnectionError() {
        toast(R.string.error_no_internet_connection_short_message)
    }

    open fun onUnknownError() {
        if (baseErrorView != null) baseErrorView?.showError()
        else toast(R.string.error_occurred_some_problem)
    }

    open fun loading(isLoading: Boolean) {
        baseSkeletonView?.apply {
            if (isLoading) {
                hideEmptyView()
                show()
            } else {
                hide()
            }
        }
    }

    open fun showEmptyView() {
        loading(false)
        baseEmptyView.isVisible = true
    }

    open fun hideEmptyView() {
        baseEmptyView.isVisible = false
    }

    open fun toggleEmptyView(items: List<Any>) {
        if (items.isEmpty()) showEmptyView() else hideEmptyView()
    }
}
