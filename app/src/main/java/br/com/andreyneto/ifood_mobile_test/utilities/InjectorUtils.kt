package br.com.andreyneto.ifood_mobile_test.utilities

import android.content.Context
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository
import br.com.andreyneto.ifood_mobile_test.data.database.TweetDatabase
import br.com.andreyneto.ifood_mobile_test.data.network.SentimentNetworkDataSource
import br.com.andreyneto.ifood_mobile_test.data.network.TweetNetworkDataSource
import br.com.andreyneto.ifood_mobile_test.ui.detail.SentimentViewModelFactory
import br.com.andreyneto.ifood_mobile_test.ui.main.MainViewModelFactory

class InjectorUtils {

    private fun provideRepository(context: Context): TweetRepository? {
        val database: TweetDatabase? = TweetDatabase.getInstance(context.applicationContext)
        val executors: AppExecutors? = AppExecutors.instance
        val netWorkDataSource: TweetNetworkDataSource =
                TweetNetworkDataSource.getInstance(context.applicationContext, executors!!)!!
        val sentimentDataSource: SentimentNetworkDataSource =
                SentimentNetworkDataSource.getInstance(context.applicationContext, executors)!!
        return TweetRepository.getInstance(database!!.tweetDao(), netWorkDataSource, sentimentDataSource, executors)
    }

    fun provideMainActivityViewModelFactory(context: Context): MainViewModelFactory {
        val repository = provideRepository(context.applicationContext)
        return MainViewModelFactory(repository!!)
    }

    fun provideSentimentViewModelFactory(context: Context, tweetID: Long): SentimentViewModelFactory {
        val repository = provideRepository(context.applicationContext)
        return SentimentViewModelFactory(repository!!, tweetID)
    }
}