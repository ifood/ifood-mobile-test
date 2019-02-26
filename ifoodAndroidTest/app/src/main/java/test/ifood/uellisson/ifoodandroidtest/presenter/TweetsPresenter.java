package test.ifood.uellisson.ifoodandroidtest.presenter;

import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.ifood.uellisson.ifoodandroidtest.BuildConfig;
import test.ifood.uellisson.ifoodandroidtest.data.model.AuthorizationToken;
import test.ifood.uellisson.ifoodandroidtest.data.network.TwitterAPI;
import test.ifood.uellisson.ifoodandroidtest.data.network.TwitterApiProvide;
import test.ifood.uellisson.ifoodandroidtest.data.model.TweetEntity;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetsPresenter extends Presenter<TweetsPresenter.View>{
    List<Tweet> lt;
    final TwitterAPI twitterAPI;

    public TweetsPresenter() {
       twitterAPI = TwitterApiProvide.provideAndCreate();
    }

    @Override
    public void initialize() {
        super.initialize();
        requestAccessToken();
        //TODO implements request API Twitter
        getView().showLoading();
        Tweet t1 = new Tweet("mensagem 1", "hoje");
        Tweet t2 = new Tweet("mensagem 2", "ontem");
        Tweet t3 = new Tweet("mensagem 3", "quinta");
        lt = new ArrayList<>();
        lt.add(t1);
        lt.add(t2);
        lt.add(t3);
        getView().showTwittes(lt);
        getView().hideLoading();
    }

    public interface View extends Presenter.BaseView {
        void showTwittes (List<Tweet> tweetList);
    }

    private void requestAccessToken() {
        twitterAPI.getAccessToken(getAuthorizationHeader(), "client_credentials").enqueue(new Callback<AuthorizationToken>() {
            @Override
            public void onResponse(Call<AuthorizationToken> call, Response<AuthorizationToken> response) {
                String token = "Bearer " + response.body().getAccessToken();
                twitterAPI.getTweetsByUsername(token, "uellissonlopes", 2).enqueue(new Callback<List<TweetEntity>>() {
                    @Override
                    public void onResponse(Call<List<TweetEntity>> call, Response<List<TweetEntity>> response) {
                        response.body();
                    }

                    @Override
                    public void onFailure(Call<List<TweetEntity>> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<AuthorizationToken> call, Throwable t) {
                Log.e("Error", t.getMessage());
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
