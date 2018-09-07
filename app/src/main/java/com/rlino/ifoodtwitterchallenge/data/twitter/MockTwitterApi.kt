package com.rlino.ifoodtwitterchallenge.data.twitter

import com.google.common.net.MediaType
import com.rlino.ifoodtwitterchallenge.model.Tweet
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class MockTwitterApi : TwitterApi {

    override fun getTweetsFromUser(from: String, excludeReplies: Boolean, includeRts: Boolean): Single<List<Tweet>> {
//        return Single.error<List<Tweet>>(HttpException(Response.error<List<Tweet>>(403, ResponseBody.create(okhttp3.MediaType.get(MediaType.ANY_APPLICATION_TYPE.toString()), ""))))
        return Single.just(listOf(
                Tweet(Date(), 6126, "i am very happy"),
                Tweet(Date(), 61213, "I wanna die, shit day"),
                Tweet(Date(), 6125123, "The stock price went up 10pts"),
                Tweet(Date(), 61267162, "Neutral tweet"),
                Tweet(Date(), 61123643, "OMG such a great day, i am exploding")
        )).delay(1, TimeUnit.SECONDS)
    }

}