package br.com.ifood.tweetmood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.presentation.view.tweets.MainTwitterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by uchoa on 16/06/18.
 */
@RunWith(AndroidJUnit4.class)
public class ShowTweetsFragmentTest {

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

    @Test
    public void verify_scree_name_on_twitter(){
        onView(withId(R.id.etUserName)).check(matches(withText("@mock")));
    }

    public TweetSearchSummaryModel getTweetSearchSummaryModel(){

        TweetModel tweet = TweetModel.builder()
                .name("Mock")
                .screenName("@mock")
                .urlProfile("https://nothing")
                .text("some text here")
                .build();

        ArrayList<TweetModel> tweets = new ArrayList<>();
        tweets.add(tweet);

        return TweetSearchSummaryModel.builder()
                .metadata(TweetMetadataModel.builder().build())
                .tweets(tweets)
                .build();
    }
}
