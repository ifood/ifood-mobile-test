package com.rafamatias.nlp.presentation.viewModel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.TweetModel;

import net.bytebuddy.asm.Advice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TweetViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private TweetViewModel tweetViewModel;
    @Mock
    private Observer<Resource<TweetModel>> testObserver;
    @Mock
    private TweetModel testTweetModel;
    @Captor
    private ArgumentCaptor<Resource<TweetModel>> testCaptor;

    @Before
    public void setUp() throws Exception {
        tweetViewModel = new TweetViewModel();
        tweetViewModel.getTweet().observeForever(testObserver);
    }

    @Test
    public void init_withNull_resourceAssertError() {
        tweetViewModel.init(null);
        verify(testObserver).onChanged(testCaptor.capture());

        assertEquals(testCaptor.getValue().state, Resource.State.ERROR);
    }

    @Test
    public void init_withTweet_resourceAssertSucess() {
        tweetViewModel.init(testTweetModel);
        verify(testObserver).onChanged(testCaptor.capture());

        assertEquals(testCaptor.getValue().state, Resource.State.SUCCESS);
    }
}