package com.yke.twittershrink.Search;

import com.yke.twittershrink.Twitter.Tweet;
import com.yke.twittershrink.Twitter.TweetDownloader;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchImplementation {

    private final TweetDownloader tweetsDownloader;

    private final SearchInterface searchInterface;

    private final TweetsCallback tweetsCallback;

    SearchImplementation(SearchInterface searchInt) {
        tweetsDownloader = new TweetDownloader();
        tweetsCallback = new TweetsCallback();
        searchInterface = searchInt;
    }

    public void onSearch(String userName) {
        tweetsDownloader.getTweets(userName, tweetsCallback);
    }

    /**
     * Listener Called at the end of Download:
     */
    private class TweetsCallback implements TweetDownloader.RequestListener<Tweet[]> {

        @Override
        public void onFinishDownload(Tweet[] tweets) {
            if (tweets != null && tweets.length > 0) {
                ArrayList<Tweet> tweetList = new ArrayList<>(Arrays.asList(tweets));
                searchInterface.setItems(tweetList);
                searchInterface.dismissLoading();
            }
        }
    }
}
