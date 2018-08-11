package bloder.com.domain.repository.test.resources

import bloder.com.domain.models.auth.TwitterAuth
import bloder.com.domain.repository.resources.AuthRepository
import io.reactivex.Single


class AuthRepositoryTest : AuthRepository {

    override fun getTwitterAuthToken(auth: String): Single<TwitterAuth> = Single.just(
            TwitterAuth("test")
    )
}