package br.com.ifood.tweetmood.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import br.com.ifood.tweetmood.domain.interactor.AnalyzeSentimentRequestUseCase;
import br.com.ifood.tweetmood.domain.model.Request.AnalyzeSentimentRequest;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 14/06/18.
 */

public class GoogleViewModel extends ViewModel {

    private AnalyzeSentimentRequestUseCase analyzeSentimentRequestUseCase;

    public LiveData<WrapperResponse<AnalyzeSentimentResponse>> analyzeSentiment(String text){
        analyzeSentimentRequestUseCase = new AnalyzeSentimentRequestUseCase();

        AnalyzeSentimentRequest request = new AnalyzeSentimentRequest(text);

        return analyzeSentimentRequestUseCase.execute(request);

    }
}
