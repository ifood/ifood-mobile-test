package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.TweetModel;

import org.jetbrains.annotations.Nullable;

public class TweetViewModel extends ViewModel {

    private MutableLiveData<Resource<TweetModel>> tweetModel = new MutableLiveData<>();

    public void init(@Nullable TweetModel tweetModel){
        if(tweetModel == null){
            this.tweetModel.setValue(Resource.<TweetModel>error("Tweet cannot be null"));
            return;
        }

        this.tweetModel.setValue(Resource.success(tweetModel));
    }

    public LiveData<Resource<TweetModel>> getTweet(){
        return tweetModel;
    }

}
