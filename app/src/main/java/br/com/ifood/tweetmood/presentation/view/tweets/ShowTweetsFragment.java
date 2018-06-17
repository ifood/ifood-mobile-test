package br.com.ifood.tweetmood.presentation.view.tweets;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.twitter.sdk.android.core.models.Search;

import javax.inject.Inject;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.databinding.FragmentShowTweetsBinding;
import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.domain.model.WrapperResponse;
import br.com.ifood.tweetmood.presentation.base.BaseFragment;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetSummaryDataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.TranslatorException;
import br.com.ifood.tweetmood.presentation.viewmodel.GoogleViewModel;
import br.com.ifood.tweetmood.presentation.viewmodel.TwitterViewModel;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static br.com.ifood.tweetmood.domain.Consts.LOG_ERROR;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowTweetsFragment extends BaseFragment implements TweetListAdapter.OnItemClick {

    private FragmentShowTweetsBinding mBinding;
    Unbinder unbinder;

    private TweetListAdapter mAdapter;
    private TweetModel mSelectedTweet;

    private TwitterViewModel twitterViewModel;
    private GoogleViewModel googleViewModel;

    @Inject
    SearchToTweetSummaryDataTranslator mTranslator;

    public static ShowTweetsFragment newInstance(TweetSearchSummaryModel tweetSearchSummaryModel) {

        Bundle args = new Bundle();
        args.putSerializable(Consts.BUNDLE_TWEET_SEARCH_SUMMARY, tweetSearchSummaryModel);
        ShowTweetsFragment fragment = new ShowTweetsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null)
            finishApplicationWithErrorMessage(R.string.error_tweet_search_not_found);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_tweets, container, false);
        unbinder = ButterKnife.bind(this, mBinding.getRoot());

        googleViewModel = ViewModelProviders.of(this).get(GoogleViewModel.class);
        twitterViewModel=  ViewModelProviders.of(this).get(TwitterViewModel.class);

        setupView();

        return mBinding.getRoot();
    }

    private TweetSearchSummaryModel getTweetSearch() {
        return (TweetSearchSummaryModel) getArguments().getSerializable(Consts.BUNDLE_TWEET_SEARCH_SUMMARY);
    }

    private void setupView() {

        mBinding.etUserName.setText(getTweetSearch().getTweets().get(0).getScreenName());

        mBinding.etUserName.setOnEditorActionListener((textView, keyId, keyEvent) -> {
            if (keyId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });


        mAdapter = new TweetListAdapter(getTweetSearch().getTweets(), this);

        mBinding.rvTweets.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mBinding.rvTweets.setLayoutManager(llm);

    }

    @Override
    public void onItemClick(TweetModel tweet) {
        showProgress();

        mSelectedTweet = tweet;

        Log.i(Consts.GOOGLE_REQUEST_START, "Start Google request with text " + tweet.getText());

        googleViewModel.analyzeSentiment(tweet.getText()).observe(this, analyzeSentimentResponseWrapperResponse ->
                onGetTextMoodSuccess(analyzeSentimentResponseWrapperResponse));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onGetTextMoodSuccess(WrapperResponse<AnalyzeSentimentResponse> response) {
        hideProgress();
        Log.i(Consts.GOOGLE_REQUEST_FINISH, "Google request finish with response " + response.toString());

        if(response.getError() != null){
            Log.i(LOG_ERROR, "Google error trying to analysis text. Error: " + response.getError().toString());

            makeSnackbar(getContext(), getString(R.string.error_google_sentimental_analysis));
        } else {
            replaceFragment(SentimentalAnalysisFragment.newInstance(mSelectedTweet, response.getResponse()));
        }

    }

    @OnClick(R.id.ivSearch)
    public void onViewClicked() {
        performSearch();
    }

    public void performSearch() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

        if(!getTweetSearch().getTweets().get(0).getScreenName().equalsIgnoreCase(mBinding.etUserName.getText().toString())){
            showProgress();

            Log.i(Consts.TWITTER_REQUEST_START, "start Twitter request with username" + mBinding.etUserName.getText().toString());
            twitterViewModel.getTweetSummaryData(mBinding.etUserName.getText().toString()).observe(this, observerGetTweetSummaryData);
        }
    }

    private Observer<WrapperResponse<Search>> observerGetTweetSummaryData = searchWrapperResponse -> {
        hideProgress();
        Log.i(Consts.TWITTER_REQUEST_FINISH, "Twitter request finished with response " + searchWrapperResponse.toString());

        if(searchWrapperResponse.getError() != null){

            Log.i(LOG_ERROR , "Twitter error with response" + searchWrapperResponse.getError().getMessage());

            makeSnackbar(getContext(), getString(R.string.error_generic));
        } else if (searchWrapperResponse.getResponse() != null &&
                (searchWrapperResponse.getResponse().tweets == null ||
                        searchWrapperResponse.getResponse().tweets.isEmpty())) {
            makeSnackbar(getContext(), getString(R.string.error_tweet_not_found));
        } else {
            try {
                reloadTweetList(mTranslator.translate(searchWrapperResponse.getResponse()));
            } catch (TranslatorException e) {

                Log.i(Consts.LOG_TRANSLATOR_ERROR , e.getMessage());

                makeSnackbar(getContext(), getString(R.string.error_generic));
            }
        }
    };

    public void reloadTweetList(TweetSearchSummaryModel data) {

        mAdapter.updateTweets(data.getTweets());
        mAdapter.notifyDataSetChanged();

    }
}
