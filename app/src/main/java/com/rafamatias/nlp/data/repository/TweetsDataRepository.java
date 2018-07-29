package com.rafamatias.nlp.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rafamatias.nlp.presentation.model.Tweet;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;

public class TweetsDataRepository implements TweetRepository {

    private MutableLiveData<Resource<List<Tweet>>> tweets = new MutableLiveData<>();

    @Override
    public LiveData<Resource<List<Tweet>>> getTweets() {
        tweets.setValue(Resource.<List<Tweet>>loading());

        List<Tweet> result = new ArrayList<Tweet>();
        result.add(new Tweet("After a canal in Amsterdam was drained, everything that was found was presented in a chronological arranged archive that goes back 119,000 years. A captivating walk through history."));
        result.add(new Tweet("Real talk I don't even use grids anymore I just have this photo of Massimo Vignelli with a gun pointed at me open at all times to keep me from going too wild"));
        result.add(new Tweet("pretty sure the Brazilian one (look at the 8) was inspired by this old jersey my wife is wearing today."));

        tweets.setValue(Resource.success(result));

        return tweets;
    }

}
