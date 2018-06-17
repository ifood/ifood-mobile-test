package br.com.ifood.tweetmood;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import br.com.ifood.tweetmood.data.rest.config.ApiCreatorBase;
import br.com.ifood.tweetmood.presentation.di.AppComponent;
import br.com.ifood.tweetmood.presentation.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import lombok.Data;

/**
 * Created by uchoa on 10/06/18.
 */

@Data
public class TMApplication extends DaggerApplication {

    private static TMApplication instance;
    private ApiCreatorBase apiCreator;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig("GjRYstuXfhdgH7o55xW8O3klO" , "4Mq7YxoCpSPda0c0zTnN73PXBbWhtDJ18uRLOv29iveM3iWInU"))
                .debug(true)
                .build();

        TwitterAuthConfig auth = new TwitterAuthConfig("", "");


        Twitter.initialize(config);

        apiCreator = new ApiCreatorBase();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    public static TMApplication getInstance(){
        if (instance == null) {
            synchronized (TMApplication.class) {
                if (instance == null) {
                    instance = new TMApplication();
                }
            }
        }

        return instance;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
