package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.interactor.GetTweetsInteractor;
import com.rafamatias.nlp.presentation.model.Tweet;

import java.util.List;

public class TweetsViewModel extends ViewModel {

    private final GetTweetsInteractor getTweetsInteractor;

    TweetsViewModel(){
        this(new GetTweetsInteractor());
    }

    TweetsViewModel(GetTweetsInteractor tweetsInteractor) {
        this.getTweetsInteractor = tweetsInteractor;
    }

    public LiveData<Resource<String>> getUsername(){
        MutableLiveData<Resource<String>> username = new MutableLiveData<>();
        username.setValue(Resource.success("rafamatias"));
        return username;
    }

    public LiveData<Resource<List<Tweet>>> getTweets() {
        return getTweetsInteractor.getTweets();
    }

}
