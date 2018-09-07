package com.rlino.ifoodtwitterchallenge.domain

import com.rlino.ifoodtwitterchallenge.defaultSchedulers
import com.rlino.ifoodtwitterchallenge.logErrors
import io.reactivex.Single

abstract class SingleUseCase<in P, R> {

    abstract fun execute(parameters: P): Single<R>

    operator fun invoke(parameters: P): Single<R> {
        return execute(parameters)
                .defaultSchedulers()
                .logErrors()
    }

}

operator fun <R> SingleUseCase<Unit, R>.invoke(): Single<R> = this(Unit)