package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.interactor.GetUsernameInteractor;
import com.rafamatias.nlp.domain.interactor.GetTweetsInteractor;
import com.rafamatias.nlp.presentation.model.TweetModel;

import java.util.List;

public class TweetsViewModel extends ViewModel {

    private final GetTweetsInteractor tweetsInteractor;
    private final GetUsernameInteractor usernameInteractor;

    TweetsViewModel() {
        this(new GetTweetsInteractor(), new GetUsernameInteractor());
    }

    TweetsViewModel(GetTweetsInteractor getTweetsInteractor, GetUsernameInteractor getUsernameInteractor) {
        this.tweetsInteractor = getTweetsInteractor;
        this.usernameInteractor = getUsernameInteractor;
        this.tweetsInteractor.addSource(usernameInteractor, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tweetsInteractor.getTweets(s);
            }
        });
    }

    public LiveData<String> getUsername(){
        return this.usernameInteractor;
    }

    public LiveData<Resource<List<TweetModel>>> getTweets() {
        return this.tweetsInteractor;
    }

    public void loadTweets() {
        tweetsInteractor.getTweets(usernameInteractor.getValue());
    }

    public void loadTweets(String username) {
        this.usernameInteractor.setValue(username);
    }

}
