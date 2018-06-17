package br.com.ifood.tweetmood.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.twitter.sdk.android.core.models.Search;

import br.com.ifood.tweetmood.domain.interactor.GetTweetSummaryDataUseCase;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 13/06/18.
 */

public class TwitterViewModel extends ViewModel {

    private GetTweetSummaryDataUseCase getTweetSummaryDataUseCase;


    public LiveData<WrapperResponse<Search>> getTweetSummaryData(String username){

        getTweetSummaryDataUseCase = new GetTweetSummaryDataUseCase();

        return getTweetSummaryDataUseCase.execute(username);

    }

    public LiveData<TweetSearchSummaryModel> getTweetSearchSummaryNextPage(String name, String id){


        return null;
    }
}
