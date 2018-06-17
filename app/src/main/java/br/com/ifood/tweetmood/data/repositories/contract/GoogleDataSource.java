package br.com.ifood.tweetmood.data.repositories.contract;

import android.arch.lifecycle.LiveData;

import br.com.ifood.tweetmood.domain.model.Request.AnalyzeSentimentRequest;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 14/06/18.
 */

public interface GoogleDataSource {

    LiveData<WrapperResponse<AnalyzeSentimentResponse>> getAnalyzeSentiment(AnalyzeSentimentRequest request);
}
