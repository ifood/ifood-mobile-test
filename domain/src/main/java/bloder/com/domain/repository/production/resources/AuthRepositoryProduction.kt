package bloder.com.domain.repository.production.resources

import bloder.com.domain.api.twitter.TwitterApi
import bloder.com.domain.api_response.auth.handleResponseGod
import bloder.com.domain.models.auth.TwitterAuth
import bloder.com.domain.repository.resources.AuthRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepositoryProduction : AuthRepository {

    override fun getTwitterAuthToken(auth: String): Single<TwitterAuth> = TwitterApi().service().getAuthToken(auth)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .flatMap { response ->
                Single.create<TwitterAuth> {
                    response.handleResponseGod(it, response.code())
                }
            }
}