package bloder.com.domain.interactor

import bloder.com.domain.SingleUseCase
import bloder.com.domain.binding.SingleUseCaseBinding
import bloder.com.domain.models.auth.TwitterAuth
import bloder.com.domain.models.search.Status


open class TwitterInteractor : SingleUseCase() {

    fun searchTweetsFrom(auth: String, name: String) : SingleUseCaseBinding<List<Status>> {
        return run(repository.forSearch().searchTweets(auth, name))
    }

    fun getTwitterAuthToken(auth: String) : SingleUseCaseBinding<TwitterAuth> {
        return run(repository.forAuth().getTwitterAuthToken(auth))
    }
}