package br.com.ifood.tweetmood.presentation.view.tweets;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.databinding.ActivityMainBinding;
import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.presentation.base.BaseActivity;
import br.com.ifood.tweetmood.presentation.base.BaseActivityView;
import br.com.ifood.tweetmood.presentation.util.ActivityUtils;

public class MainTwitterActivity extends BaseActivity implements BaseActivityView {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if(getIntent().getExtras() != null && getIntent().getExtras().containsKey(Consts.BUNDLE_TWEET_SEARCH_SUMMARY)){
            TweetSearchSummaryModel tweetSearchSummaryModel = (TweetSearchSummaryModel) getIntent().getExtras().getSerializable(Consts.BUNDLE_TWEET_SEARCH_SUMMARY);
            new ActivityUtils().addFragmentToActivity(getSupportFragmentManager(), ShowTweetsFragment.newInstance(tweetSearchSummaryModel), R.id.containerMain);
        } else{
            finishApplicationWithErrorMessage(R.string.error_tweet_search_not_found);
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        new ActivityUtils().replaceFragment(this.getSupportFragmentManager(), fragment, R.id.containerMain);
    }
}
