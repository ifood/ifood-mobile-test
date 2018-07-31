package com.rafamatias.nlp.presentation.viewModel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.rafamatias.nlp.domain.interactor.GetTweetsInteractor;
import com.rafamatias.nlp.domain.interactor.GetUsernameInteractor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetsViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private static final String FAKE_USERNAME = "rafamatias";
    private TweetsViewModel tweetsViewModel;
    @Mock private GetTweetsInteractor tweetsInteractor;
    @Mock private GetUsernameInteractor usernameInteractor;

    @Before
    public void setUp() throws Exception {
        tweetsViewModel = new TweetsViewModel(tweetsInteractor, usernameInteractor);
        when(usernameInteractor.getValue()).thenReturn(FAKE_USERNAME);
    }

    @Test
    public void loadTweets_withDefaultUsername_verifyTweetsInteractor() {
        tweetsViewModel.loadTweets();
        verify(tweetsInteractor).getTweets(FAKE_USERNAME);
    }

    @Test
    public void loadTweets_withUsername_verifyTweetsInteractor() {
        tweetsViewModel.loadTweets("rafael");
        verify(usernameInteractor).setValue("rafael");
    }

}