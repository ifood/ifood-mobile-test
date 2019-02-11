package com.drss.ifoodmobiletest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drss.ifoodmobiletest.data.Document
import com.drss.ifoodmobiletest.data.GCloudSentimentBody
import com.drss.ifoodmobiletest.data.Sentiment
import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class GCloudSentimentApi @Inject constructor(val gcloudApiInterface: GCloudApiInterface) {

    var compositeDisposable = CompositeDisposable()

    val mNetworkThreadPoolExecutor: Executor = Executors.newFixedThreadPool(10)

    fun analyseSentiment(text: String): LiveData<Sentiment> {
        compositeDisposable.clear()

        val data = MutableLiveData<Sentiment>()
        val document = Document(text)
        val requestBody = GCloudSentimentBody(document)
        val disposable = gcloudApiInterface.analyzeDocumentSentiment(requestBody)
            .subscribeOn(Schedulers.from(mNetworkThreadPoolExecutor))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> data.value = result?.documentSentiment },
                { error -> Log.e("GCLOUD", error.message) }

            )

        compositeDisposable.add(disposable)

        return data
    }

}