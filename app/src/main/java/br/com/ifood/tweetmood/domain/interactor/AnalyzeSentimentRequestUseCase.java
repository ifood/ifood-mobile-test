package br.com.ifood.tweetmood.domain.interactor;

import android.arch.lifecycle.LiveData;

import br.com.ifood.tweetmood.data.repositories.source.GoogleRepository;
import br.com.ifood.tweetmood.domain.model.Request.AnalyzeSentimentRequest;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 14/06/18.
 */

public class AnalyzeSentimentRequestUseCase {

    private GoogleRepository googleRepository;

    public LiveData<WrapperResponse<AnalyzeSentimentResponse>> execute(AnalyzeSentimentRequest request){
        googleRepository = new GoogleRepository();

        return googleRepository.getAnalyzeSentiment(request);
    }
}
