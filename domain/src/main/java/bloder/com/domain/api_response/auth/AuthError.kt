package bloder.com.domain.api_response.auth

class AuthError(val reason: AUTH_ERROR, val errorMessage: String) : Throwable()

enum class AUTH_ERROR {
    UNKNOWN
}