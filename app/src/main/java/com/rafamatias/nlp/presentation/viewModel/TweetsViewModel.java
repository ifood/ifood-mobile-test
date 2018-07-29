package com.rafamatias.nlp.presentation.viewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.interactor.GetDefaultUsernameInteractor;
import com.rafamatias.nlp.domain.interactor.GetTweetsInteractor;
import com.rafamatias.nlp.presentation.model.TweetModel;

import java.util.List;

public class TweetsViewModel extends ViewModel {

    private final GetTweetsInteractor getTweetsInteractor;
    private final GetDefaultUsernameInteractor getDefaultUsernameInteractor;
    private final MutableLiveData<String> username;

    TweetsViewModel(){
        this(new GetTweetsInteractor(), new GetDefaultUsernameInteractor());
    }

    TweetsViewModel(GetTweetsInteractor tweetsInteractor, GetDefaultUsernameInteractor defaultUsernameInteractor) {
        this.getTweetsInteractor = tweetsInteractor;
        this.getDefaultUsernameInteractor = defaultUsernameInteractor;
        this.username = new MutableLiveData<>();
    }

    public LiveData<String> getUsername(){
        return this.username;
    }

    public LiveData<Resource<List<TweetModel>>> getTweets() {
        return Transformations.switchMap(getDefaultUsernameInteractor.getDefaultUsername(), new Function<String, LiveData<Resource<List<TweetModel>>>>() {
            @Override
            public LiveData<Resource<List<TweetModel>>> apply(String input) {
                username.postValue(input);
                return getTweetsInteractor.getTweets(input);
            }
        });
    }

    public LiveData<Resource<List<TweetModel>>> getTweets(String username) {
        this.username.postValue(username);
        return getTweetsInteractor.getTweets(username);
    }

}
