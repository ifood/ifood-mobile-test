package com.spider.twitteranalyzer.tests.feature.list

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.spider.twitteranalyzer.base.domain.ExecutorThread
import com.spider.twitteranalyzer.base.domain.PostExecutionThread
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import com.spider.twitteranalyzer.base.view.ErrorMessageFactory
import com.spider.twitteranalyzer.feature.list.domain.FetchTweetsUseCase
import com.spider.twitteranalyzer.feature.list.domain.Mapper
import com.spider.twitteranalyzer.feature.list.repository.TweetsRepository
import com.spider.twitteranalyzer.feature.list.viewmodel.ListViewModel
import com.spider.twitteranalyzer.feature.list.viewslice.list.ListViewSlice
import com.spider.twitteranalyzer.feature.list.viewslice.search.SearchViewSlice
import io.reactivex.Single

object Mocks {

    private val repository: TweetsRepository = object : TweetsRepository {
        override fun fetchTweets(screenName: String): Single<List<TweetResponse>> {

            return Single.just(
                list
            )
        }
    }
    private val mapper: Mapper = Mapper()
    private val executorThread = ExecutorThread.Impl()
    private val postExecutionThread = PostExecutionThread.Impl()
    val list = listOf(
        TweetResponse(
            text = "@AlecLongstreth this is so fun to follow along during your day!",
            user = TweetResponse.User("x"),
            createdAt = "Fri Feb 01 22:02:18 +0000 2019"
        ),
        TweetResponse(
            text = "@zombieyeti Dude this is looking amazing!",
            user = TweetResponse.User("x"),
            createdAt = "Fri Feb 01 08:18:58 +0000 2019"
        ),
        TweetResponse(
            text = "U.S. Senate: National Pinball Day - Sign the Petition!",
            user = TweetResponse.User("x"),
            createdAt = "Wed Jan 30 21:34:21 +0000 2019"
        )
    )
    val liveDataListViewSlice = MutableLiveData<ListViewSlice.Action>()
    val liveDataSearchViewSlice = MutableLiveData<SearchViewSlice.Action>()
    val state: MediatorLiveData<ListViewModel.State> = MediatorLiveData()
    val useCase: FetchTweetsUseCase = FetchTweetsUseCase.Impl(repository, mapper, executorThread, postExecutionThread)
    val errorMessageFactory = object : ErrorMessageFactory {
        override fun create(exception: Throwable): ErrorMessageFactory.ErrorMessage {
            return ErrorMessageFactory.ErrorMessage(exception.message!!)
        }
    }
}