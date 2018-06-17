package br.com.ifood.tweetmood.presentation.view.getuser;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Search;

import javax.inject.Inject;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.databinding.FragmentGetUserTweetsBinding;
import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;
import br.com.ifood.tweetmood.presentation.base.BaseFragment;
import br.com.ifood.tweetmood.presentation.util.ActivityUtils;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetSummaryDataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.TranslatorException;
import br.com.ifood.tweetmood.presentation.view.tweets.MainTwitterActivity;
import br.com.ifood.tweetmood.presentation.viewmodel.TwitterViewModel;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetUserTweetsFragment extends BaseFragment {

    private FragmentGetUserTweetsBinding mBinding;

    private TwitterViewModel twitterViewModel;

    @Inject
    SearchToTweetSummaryDataTranslator mTranslator;

    public static GetUserTweetsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GetUserTweetsFragment fragment = new GetUserTweetsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        twitterViewModel = ViewModelProviders.of(this)
                .get(TwitterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_user_tweets, container, false);

        ButterKnife.bind(this, mBinding.getRoot());

        return mBinding.getRoot();
    }

    @OnClick(R.id.btSearchUser)
    public void onSearchUserClicked() {
        if( mBinding.etUsername.getText().toString().isEmpty()){
            mBinding.tlUsername.setError(getString(R.string.label_mandatory_field));
            return;
        } else {
            mBinding.tlUsername.setError(null);
        }

        showProgress();
        Log.i(Consts.TWITTER_REQUEST_START, "start Twitter request with username" + mBinding.etUsername.getText().toString());

        twitterViewModel.getTweetSummaryData(mBinding.etUsername.getText().toString()).observe(this, observerGetTweetSummaryData);
    }

    private Observer<WrapperResponse<Search>> observerGetTweetSummaryData = searchWrapperResponse -> {
        Log.i(Consts.TWITTER_REQUEST_FINISH, "Twitter request finished with response " + searchWrapperResponse.toString());

        hideProgress();

        if(searchWrapperResponse.getError() != null){
            Log.i(Consts.LOG_ERROR , "Twitter error with response" + searchWrapperResponse.getError().getMessage());

            makeSnackbar(getContext(), getString(R.string.error_generic));
        } else if (searchWrapperResponse.getResponse() != null &&
                (searchWrapperResponse.getResponse().tweets == null ||
                searchWrapperResponse.getResponse().tweets.isEmpty())) {
            makeSnackbar(getContext(), getString(R.string.error_tweet_not_found));
        } else {
            try {
                getUserTweetsSuccess(mTranslator.translate(searchWrapperResponse.getResponse()));
            } catch (TranslatorException e) {

                Log.i(Consts.LOG_TRANSLATOR_ERROR , e.getMessage());

                makeSnackbar(getContext(), getString(R.string.error_generic));
            }
        }
    };


    public void getUserTweetsSuccess(TweetSearchSummaryModel data) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(Consts.BUNDLE_TWEET_SEARCH_SUMMARY, data);

        new ActivityUtils().replaceActivity(getActivity(), MainTwitterActivity.class, bundle, true);
    }

}
