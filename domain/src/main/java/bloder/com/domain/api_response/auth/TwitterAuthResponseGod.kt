package bloder.com.domain.api_response.auth

import bloder.com.domain.api_response.ResponseGod
import bloder.com.domain.models.auth.TwitterAuth
import bloder.com.domain.payloads.auth.TwitterAuthPayload
import io.reactivex.SingleEmitter
import retrofit2.Response

class TwitterAuthResponseGod(
        private val response: Response<TwitterAuthPayload>,
        private val emitter: SingleEmitter<TwitterAuth>
) : ResponseGod {

    override fun on200() {
        response.body()?.let {
            emitter.onSuccess(it.toModel())
        }
    }

    override fun unknown(code: Int) {
        emitter.onError(AuthError(AUTH_ERROR.UNKNOWN, "Unknown problem!"))
    }
}

fun Response<TwitterAuthPayload>.handleResponseGod(emitter: SingleEmitter<TwitterAuth>, code: Int) {
    TwitterAuthResponseGod(this, emitter).handle(code)
}