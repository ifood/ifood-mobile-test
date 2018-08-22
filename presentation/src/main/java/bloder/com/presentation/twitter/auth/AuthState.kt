package bloder.com.presentation.twitter.auth

sealed class AuthState {

    class TwitterAuthTokenFetched(val authToken: String) : AuthState()
    class TwitterAuthTokenFetchFailed(val errorMessage: String) : AuthState()
    object Setup : AuthState()
}