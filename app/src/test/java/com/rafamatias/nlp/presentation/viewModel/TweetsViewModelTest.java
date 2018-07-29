package com.rafamatias.nlp.presentation.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.interactor.GetDefaultUsernameInteractor;
import com.rafamatias.nlp.domain.interactor.GetTweetsInteractor;
import com.rafamatias.nlp.presentation.model.TweetModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetsViewModelTest {

    private static final String FAKE_USERNAME = "rafamatias";
    private TweetsViewModel tweetsViewModel;
    @Mock private GetTweetsInteractor tweetsInteractor;
    @Mock private GetDefaultUsernameInteractor userDefaultInteractor;
    @Mock private Observer<Resource<List<TweetModel>>> observer;
    private MutableLiveData<Resource<List<TweetModel>>> fakeResource = new MutableLiveData<>();

    @Before
    public void setUp() throws Exception {
        tweetsViewModel = new TweetsViewModel(tweetsInteractor, userDefaultInteractor);
        when(tweetsInteractor.getTweets(FAKE_USERNAME)).thenReturn(fakeResource);
    }

    @Test
    public void getTweets_withResource_assertLoading() {
        tweetsViewModel.getTweets().observeForever(observer);
        verify(tweetsInteractor).getTweets(FAKE_USERNAME);
    }

}