package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.interactor.GetSentimentInteractor;
import com.rafamatias.nlp.presentation.model.SentimentModel;
import com.rafamatias.nlp.presentation.model.TweetModel;

import org.jetbrains.annotations.Nullable;

public class TweetViewModel extends ViewModel {

    private final MutableLiveData<Resource<TweetModel>> tweetModel;
    private final GetSentimentInteractor sentimentModel;

    TweetViewModel() {
        this.tweetModel = new MutableLiveData<>();
        this.sentimentModel = new GetSentimentInteractor();
    }

    public void init(@Nullable TweetModel tweetModel){
        if(tweetModel == null){
            this.tweetModel.setValue(Resource.error("Tweet cannot be null"));
            return;
        }

        this.tweetModel.setValue(Resource.success(tweetModel));
        this.sentimentModel.addSource(this.tweetModel, new Observer<Resource<TweetModel>>() {
            @Override
            public void onChanged(@Nullable Resource<TweetModel> resource) {
                if(resource == null || resource.data == null){
                    Resource.error("No sentiment");
                    return;
                }

                sentimentModel.getSentiment(resource.data.getText());
            }
        });
    }

    public LiveData<Resource<TweetModel>> getTweet(){
        return tweetModel;
    }

    public LiveData<Resource<SentimentModel>> getSentiment(){
        return sentimentModel;
    }

}
