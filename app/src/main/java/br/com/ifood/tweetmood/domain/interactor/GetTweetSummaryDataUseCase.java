package br.com.ifood.tweetmood.domain.interactor;

import android.arch.lifecycle.LiveData;

import com.twitter.sdk.android.core.models.Search;

import br.com.ifood.tweetmood.data.repositories.source.TwitterRepository;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 13/06/18.
 */

public class GetTweetSummaryDataUseCase {

    private TwitterRepository twitterRepository;


    public LiveData<WrapperResponse<Search>> execute(String username) {
        twitterRepository = new TwitterRepository();

        return twitterRepository.getTwitterSearchData(username);
    }

}