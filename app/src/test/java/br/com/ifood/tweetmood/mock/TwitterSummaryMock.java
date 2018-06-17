package br.com.ifood.tweetmood.mock;

import java.util.ArrayList;

import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;

/**
 * Created by uchoa on 16/06/18.
 */

public class TwitterSummaryMock {

    public TweetSearchSummaryModel  getTweetSearchSummaryModel(){

        TweetModel tweet = TweetModel.builder()
                .name("Mock")
                .screenName("@mock")
                .urlProfile("https://nothing")
                .text("some text here")
                .build();

        ArrayList<TweetModel> tweets = new ArrayList<>();
        tweets.add(tweet);

        return TweetSearchSummaryModel.builder()
                .metadata(TweetMetadataModel.builder().build())
                .tweets(tweets)
                .build();
    }
}
