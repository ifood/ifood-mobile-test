package br.com.ifood.tweetmood.presentation.view.tweets;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.databinding.FragmentSentimentalAnalysisBinding;
import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.Response.AnalyzeSentimentResponse;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.presentation.base.BaseFragment;
import br.com.ifood.tweetmood.presentation.component.CircleTransform;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SentimentalAnalysisFragment extends BaseFragment {


    private final double NEUTRAL_MIN = -0.2;
    private final double NEUTRAL_MAX = 0.5;
    Unbinder unbinder;

    private FragmentSentimentalAnalysisBinding mBinding;

    public static SentimentalAnalysisFragment newInstance(TweetModel tweet, AnalyzeSentimentResponse analyzeSentimentResponse) {
        Bundle args = new Bundle();
        args.putSerializable(Consts.BUNDLE_ANALYSES_SENTIMENT, analyzeSentimentResponse);
        args.putSerializable(Consts.BUNDLE_TWEET, tweet);
        SentimentalAnalysisFragment fragment = new SentimentalAnalysisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null)
            popFragmentWithErrorMessage(R.string.error_google_sentimental_analysis);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sentimental_analysis, container, false);

        setupView();

        unbinder = ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    private AnalyzeSentimentResponse getAnalyzeSentiment() {
        return (AnalyzeSentimentResponse) getArguments().getSerializable(Consts.BUNDLE_ANALYSES_SENTIMENT);
    }

    private TweetModel getTweet() {
        return (TweetModel) getArguments().getSerializable(Consts.BUNDLE_TWEET);
    }

    private void setupView() {

        mBinding.tweetItem.setTweet(getTweet());

        Picasso.get()
                .load(getTweet().getUrlProfile())
                .transform(new CircleTransform())
                .into(mBinding.tweetItem.ivAvatar);

        Number score = getAnalyzeSentiment().getDocumentSentiment().getScore();

        if (score.doubleValue() < NEUTRAL_MIN) {
            setSadMood();
        } else if (score.doubleValue() < NEUTRAL_MAX) {
            setNeutralMood();
        } else {
            setHappyMood();
        }

    }

    public void setHappyMood() {
        mBinding.clSentimentalAnalysis.setBackgroundColor(getResources().getColor(R.color.tweetMoodHappy));
        mBinding.clSentimentalAnalysis.setTag(R.color.tweetMoodHappy);
        mBinding.ivMood.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.happy));
    }

    public void setNeutralMood() {
        mBinding.clSentimentalAnalysis.setBackgroundColor(getResources().getColor(R.color.tweetMoodNeutral));
        mBinding.clSentimentalAnalysis.setTag(R.color.tweetMoodNeutral);

        mBinding.ivMood.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.neutral));
    }

    public void setSadMood() {
        mBinding.clSentimentalAnalysis.setBackgroundColor(getResources().getColor(R.color.tweetMoodSad));
        mBinding.clSentimentalAnalysis.setTag(R.color.tweetMoodSad);
        mBinding.ivMood.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.sad));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btReturn)
    public void onReturnClick() {
        getFragmentManager().popBackStack();
    }
}
