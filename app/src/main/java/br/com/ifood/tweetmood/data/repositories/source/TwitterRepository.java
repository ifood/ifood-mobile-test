package br.com.ifood.tweetmood.data.repositories.source;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;

import br.com.ifood.tweetmood.data.repositories.contract.TwitterDataSource;
import br.com.ifood.tweetmood.domain.model.Response.ErrorResponse;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;
import retrofit2.Call;

/**
 * Created by uchoa on 14/06/18.
 */

public class TwitterRepository implements TwitterDataSource {

    @Override
    public LiveData<WrapperResponse<Search>> getTwitterSearchData(String username) {

        Call<Search> request = makeRequest(username);

        return getTweets(request);
    }

    @Override
    public LiveData<WrapperResponse<Search>> getTwitterSearchDataNextPage(String username, String nextPage) {
        return null;
    }

    private Call<Search> makeRequest(String username){
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();

        return twitterApiClient.getSearchService().tweets("from:" + username,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private LiveData<WrapperResponse<Search>> getTweets(Call<Search> call) {
        final MutableLiveData<WrapperResponse<Search>> data = new MutableLiveData<>();

        call.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                data.postValue(new WrapperResponse<>(result.data, null));
            }

            @Override
            public void failure(TwitterException exception) {
                data.postValue(new WrapperResponse<>(null, new ErrorResponse(exception.getCause().toString())));
            }
        });

        return data;
    }

}