package test.ifood.uellisson.ifoodandroidtest.presenter;

import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.ifood.uellisson.ifoodandroidtest.BuildConfig;
import test.ifood.uellisson.ifoodandroidtest.data.mapper.TweetMapper;
import test.ifood.uellisson.ifoodandroidtest.data.model.AuthorizationToken;
import test.ifood.uellisson.ifoodandroidtest.data.model.TweetEntity;
import test.ifood.uellisson.ifoodandroidtest.data.network.TwitterAPI;
import test.ifood.uellisson.ifoodandroidtest.data.network.TwitterApiProvide;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetsPresenter extends Presenter<TweetsPresenter.View>{
    private final TwitterAPI twitterAPI;

    public TweetsPresenter() {
       twitterAPI = TwitterApiProvide.provideAndCreate();
    }

    @Override
    public void initialize(String usernama) {
        super.initialize(usernama);
        requestAccessToken(usernama);
    }

    public interface View extends Presenter.BaseView {
        void showTwittes (List<Tweet> tweetList);
    }

    private void requestAccessToken(final String userName) {
        getView().showLoading();
        twitterAPI.getAccessToken(getAuthorizationHeader(), "client_credentials").enqueue(new Callback<AuthorizationToken>() {
            @Override
            public void onResponse(Call<AuthorizationToken> call, Response<AuthorizationToken> response) {
                String token = "Bearer " + response.body().getAccessToken();
                twitterAPI.getTweetsByUsername(token, userName, 10).enqueue(new Callback<List<TweetEntity>>() {
                    @Override
                    public void onResponse(Call<List<TweetEntity>> call, Response<List<TweetEntity>> response) {
                        TweetMapper mapper = new TweetMapper(response.body());
                        getView().showTwittes(mapper.transformTweetEntity());
                        getView().hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<TweetEntity>> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        getView().hideLoading();
                    }
                });
            }

            @Override
            public void onFailure(Call<AuthorizationToken> call, Throwable t) {
                Log.e("Error", t.getMessage());
                getView().hideLoading();
            }
        });
    }

    private String getAuthorizationHeader() {
        try {
            String consumerKeyAndSecret = BuildConfig.API_KEY_TWITTER + ":" + BuildConfig.API_SECRET_KEY_TWITTER;
            byte[] data = consumerKeyAndSecret.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.NO_WRAP);

            return "Basic " + base64;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
