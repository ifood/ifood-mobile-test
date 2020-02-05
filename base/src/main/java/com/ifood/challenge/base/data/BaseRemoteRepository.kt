package com.ifood.challenge.base.data

import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.base.common.network.NetworkHandler
import com.ifood.challenge.base.extensions.performOnBack
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function

/**
 * Base class for all Repositories
 */
abstract class BaseRemoteRepository(
    private val networkHandler: NetworkHandler
) {

    /**
     * Function to check if there is internet connection
     *
     * @param body Another function to execute when there is internet connection
     * @return An [Completable] with [NetworkError] when there is no internet
     * connection or with the body function result
     */
    fun request(body: () -> Completable): Completable =
        when (networkHandler.isConnected) {
            false -> Completable.error(NetworkError)
            true -> body().performOnBack()
        }

    /**
     * Function to check if there is internet connection
     *
     * @param body Another function to execute when there is internet connection
     * @return An [Completable] with [NetworkError] when there is no internet
     * connection or with the body function result
     */
    fun <T, R> request(mapper: Function<T, R>, body: () -> Single<T>): Single<R> =
        when (networkHandler.isConnected) {
            false -> Single.error(NetworkError)
            true -> body().performOnBack()
                .map(mapper)
        }
}
