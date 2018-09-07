package com.rlino.ifoodtwitterchallenge.domain

import io.reactivex.Single

abstract class SingleUseCase<in P, R> {

    abstract fun execute(parameters: P): Single<R>

    operator fun invoke(parameters: P): Single<R> {
        return execute(parameters)
    }

}