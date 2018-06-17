package br.com.ifood.tweetmood.data.repositories.source;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import br.com.ifood.tweetmood.TMApplication;
import br.com.ifood.tweetmood.data.rest.GoogleApi;
import br.com.ifood.tweetmood.data.repositories.contract.GoogleDataSource;
import br.com.ifood.tweetmood.domain.model.Response.ErrorResponse;
import br.com.ifood.tweetmood.domain.model.Request.AnalyzeSentimentRequest;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uchoa on 14/06/18.
 */

public class GoogleRepository implements GoogleDataSource{

    @Override
    public LiveData<WrapperResponse<AnalyzeSentimentResponse>> getAnalyzeSentiment(AnalyzeSentimentRequest request) {

        final MutableLiveData<WrapperResponse<AnalyzeSentimentResponse>> data = new MutableLiveData<>();

        TMApplication.getInstance().getApiCreator()
                .createService(GoogleApi.class)
                .postAnalyzeSentiment(request)
                .enqueue(new Callback<AnalyzeSentimentResponse>() {
                    @Override
                    public void onResponse(Call<AnalyzeSentimentResponse> call, Response<AnalyzeSentimentResponse> response) {
                        if(response.isSuccessful()){
                            data.postValue(new WrapperResponse<>(response.body(),null));
                        } else{
                            data.postValue(new WrapperResponse<>(null, new ErrorResponse(response.errorBody().toString())) );
                        }
                    }

                    @Override
                    public void onFailure(Call<AnalyzeSentimentResponse> call, Throwable t) {
                        data.postValue(new WrapperResponse<>(null, new ErrorResponse(t.getMessage())) );
                    }
                });


        return data;
    }
}
