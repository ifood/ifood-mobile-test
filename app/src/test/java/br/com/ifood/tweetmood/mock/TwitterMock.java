package br.com.ifood.tweetmood.mock;

import com.google.gson.Gson;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.SearchMetadata;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uchoa on 16/06/18.
 */

public class TwitterMock {

    public Search getSearchObject(){
        return new Search( getTweetList(), getSearchMetadata());
    }

    public Search getSearchObjectWithEmptyTweets(){
        List<Tweet> tweets = new ArrayList<>();

        return new Search( tweets, getSearchMetadata());
    }

    private List<Tweet> getTweetList(){
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(getTweet());
        return tweets;
    }

    private Tweet getTweet(){
        String json =
                "{" +
                "   \"created_at\":\"Fri Jun 15 16:07:21 +0000 2018\"," +
                "   \"id\":1007655811710115842," +
                "   \"text\":\"It’s been the greatest honor to live out my lifelong dream of being a @NASA Astronaut. Thank you to the… https://t.co/6zllROrey9\"," +
                "   \"user\":{" +
                "      \"name\":\"Peggy Whitson\"," +
                "      \"screen_name\":\"AstroPeggy\"," +
                "      \"profile_image_url\":\"http://pbs.twimg.com/profile_images/626900284149735424/AZTSesIM_normal.jpg\"" +
                "   }" +
                "}";

        return new Gson().fromJson(json, Tweet.class);
    }

    private SearchMetadata getSearchMetadata(){
        return new SearchMetadata(0,
                1,
                "",
                "?max_id=1007429986083459076&q=nasa&include_entities=1&result_type=popular",
                1,
                0.07,
                "",
                "nasa",
                "0");
    }
}
