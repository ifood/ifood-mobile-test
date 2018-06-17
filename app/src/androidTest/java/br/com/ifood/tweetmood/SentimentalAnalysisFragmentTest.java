package br.com.ifood.tweetmood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.Response.DocumentSentiment;
import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.presentation.view.tweets.MainTwitterActivity;
import br.com.ifood.tweetmood.presentation.view.tweets.SentimentalAnalysisFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;

/**
 * Created by uchoa on 16/06/18.
 */
@RunWith(AndroidJUnit4.class)
public class SentimentalAnalysisFragmentTest {

    @Rule
    public final ActivityTestRule<MainTwitterActivity> myActivityTestRule = new ActivityTestRule<>(MainTwitterActivity.class, false, false);


    @Rule
    public ActivityTestRule<MainTwitterActivity> mActivityRule =
            new ActivityTestRule<MainTwitterActivity>(MainTwitterActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, MainTwitterActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Consts.BUNDLE_TWEET_SEARCH_SUMMARY, getTweetSearchSummaryModel());
                    result.putExtras(bundle);
                    return result;
                }
            };

    @Before
    public void setup(){
        mActivityRule.getActivity().replaceFragment(SentimentalAnalysisFragment.newInstance(getTweet(), getSentimentalAnalysis(0)));
    }

    @Test
    public void verify_screen_color_and_sentiment(){
        onView(withId(R.id.tvScreenName)).check(matches(withText("@mock")));
        onView(withId(R.id.tvText)).check(matches(withText("some text here")));


        onView(withId(R.id.clSentimentalAnalysis)).check(matches(withTagValue(is(R.color.tweetMoodNeutral))));

        mActivityRule.getActivity().replaceFragment(SentimentalAnalysisFragment.newInstance(getTweet(), getSentimentalAnalysis(0.8)));
        onView(withId(R.id.clSentimentalAnalysis)).check(matches(withTagValue(is(R.color.tweetMoodHappy))));

        mActivityRule.getActivity().replaceFragment(SentimentalAnalysisFragment.newInstance(getTweet(), getSentimentalAnalysis(-0.8)));
        onView(withId(R.id.clSentimentalAnalysis)).check(matches(withTagValue(is(R.color.tweetMoodSad))));

    }

    private AnalyzeSentimentResponse getSentimentalAnalysis(Number score){
        return AnalyzeSentimentResponse.builder()
                        .documentSentiment(new DocumentSentiment(0, score))
                        .build();
    }

    private TweetModel getTweet(){
        return TweetModel.builder()
                .name("Mock")
                .screenName("@mock")
                .urlProfile("https://nothing")
                .text("some text here")
                .build();
    }

    public TweetSearchSummaryModel getTweetSearchSummaryModel(){
        ArrayList<TweetModel> tweets = new ArrayList<>();
        tweets.add(getTweet());

        return TweetSearchSummaryModel.builder()
                .metadata(TweetMetadataModel.builder().build())
                .tweets(tweets)
                .build();
    }
}
