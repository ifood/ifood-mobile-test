package bloder.com.domain.interactor

import bloder.com.domain.SingleUseCase
import bloder.com.domain.binding.SingleUseCaseBinding
import bloder.com.domain.models.Status


open class SearchTweetsInteractor : SingleUseCase() {

    fun searchTweetsFrom(auth: String, name: String) : SingleUseCaseBinding<List<Status>> {
        return run(repository.forSearch().searchTweets(auth, name))
    }
}