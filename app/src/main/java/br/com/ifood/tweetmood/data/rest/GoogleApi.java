package br.com.ifood.tweetmood.data.rest;

import br.com.ifood.tweetmood.BuildConfig;
import br.com.ifood.tweetmood.domain.model.Request.AnalyzeSentimentRequest;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by uchoa on 10/06/18.
 */

public interface GoogleApi {

    @POST(BuildConfig.API_VERSION + "documents:analyzeSentiment")
    Call<AnalyzeSentimentResponse> postAnalyzeSentiment(@Body AnalyzeSentimentRequest request);


}
