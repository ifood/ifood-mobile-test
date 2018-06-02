package com.yke.twittershrink.Search;

import com.yke.twittershrink.Twitter.Tweet;

import java.util.List;

public interface SearchInterface {
    /**
     * Change the layout to loading state.
     */

    void setItems(List<Tweet> tweets);

    void dismissLoading();
}
