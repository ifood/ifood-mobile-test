package com.drss.ifoodmobiletest.repository

import com.drss.ifoodmobiletest.data.Document
import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GCloudSentimentApi(val gcloudApiInterface: GCloudApiInterface) {

    val mNetworkThreadPoolExecutor: Executor = Executors.newFixedThreadPool(10)

    fun analyseSentiment(text: String): Disposable {
        val document = Document(text)
        return gcloudApiInterface.analyzeDocumentSentiment(document)
            .subscribeOn(Schedulers.from(mNetworkThreadPoolExecutor))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> },
                {error -> }
            )
    }

}