package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.Tweet;

import org.jetbrains.annotations.Nullable;

public class TweetViewModel extends ViewModel {

    private MutableLiveData<Resource<String>> text = new MutableLiveData<>();

    public void init(@Nullable Tweet tweet){

        if(tweet == null){
            text.setValue(Resource.<String>error("Tweet cannot be null"));
            return;
        }

        text.setValue(Resource.success(tweet.getText()));
    }

    public LiveData<Resource<String>> getText(){
        return text;
    }

}
