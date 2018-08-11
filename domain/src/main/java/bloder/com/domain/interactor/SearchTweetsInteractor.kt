package bloder.com.domain.interactor

import bloder.com.domain.SingleUseCase
import bloder.com.domain.binding.SingleUseCaseBinding
import bloder.com.domain.models.Status


class SearchTweetsInteractor : SingleUseCase() {

    fun searchTweetsFrom(name: String) : SingleUseCaseBinding<List<Status>> {
        return run(repository.forSearch().searchTweets(name))
    }
}