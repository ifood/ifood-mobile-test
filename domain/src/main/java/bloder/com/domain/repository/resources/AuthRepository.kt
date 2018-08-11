package bloder.com.domain.repository.resources

import bloder.com.domain.models.auth.TwitterAuth
import io.reactivex.Single

interface AuthRepository {

    fun getTwitterAuthToken(auth: String) : Single<TwitterAuth>
}