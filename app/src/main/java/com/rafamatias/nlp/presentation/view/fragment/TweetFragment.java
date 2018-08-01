package com.rafamatias.nlp.presentation.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.AssetManager;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafamatias.nlp.R;
import com.rafamatias.nlp.databinding.FragmentTweetBinding;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.SentimentModel;
import com.rafamatias.nlp.presentation.model.TweetModel;
import com.rafamatias.nlp.presentation.viewModel.TweetViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetFragment extends Fragment {

    private static final String PARAM_TWEET = "TweetFragment.Param.Tweet";

    private FragmentTweetBinding binding;
    private TweetViewModel viewModel;
    private TweetModel tweetModel;
    private AssetManager assetManager;
    private SimpleDateFormat dateFormat;
    private Context context;

    public TweetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TweetFragment.
     */
    public static TweetFragment newInstance(TweetModel tweetModel) {
        TweetFragment fragment = new TweetFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_TWEET, tweetModel);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args == null) {
            args = new Bundle();
        }

        // TODO: get from string resource because of localization
        dateFormat = new SimpleDateFormat("MMM d", Locale.getDefault());
        tweetModel = args.getParcelable(PARAM_TWEET);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tweet, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLayout();
        setupViewModel(tweetModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.assetManager = context.getAssets();
        this.viewModel = ViewModelProviders.of(this).get(TweetViewModel.class);
    }

    private void setupLayout(){
        Typeface typeFace = Typeface.createFromAsset(assetManager, "fonts/BodoniXT.ttf");
        binding.textTweet.setTypeface(typeFace);
    }

    private void setupViewModel(TweetModel tweetModel) {
        viewModel.init(tweetModel);
        viewModel.getTweet().observe(this, new Observer<Resource<TweetModel>>() {
            @Override
            public void onChanged(Resource<TweetModel> resource) {
                switch (resource.state){
                    case SUCCESS:
                        showTweet(resource.data);
                        break;
                }
            }
        });

        viewModel.getSentiment().observe(this, new Observer<Resource<SentimentModel>>() {
            @Override
            public void onChanged(Resource<SentimentModel> resource) {
                switch (resource.state){
                    case SUCCESS:
                        onSentimentSuccess(resource.data);
                        break;
                    default:
                        onSentimentDefault();
                        break;
                }
            }
        });
    }

    private void onSentimentDefault(){
        binding.textSentiment.setText(getString(R.string.text_sentiment_finish));
    }

    private void onSentimentSuccess(SentimentModel data) {
        int textResourceId = R.string.text_sentiment_neutral;
        int backgroundColor = ContextCompat.getColor(context, R.color.sentimentNeutral);

        switch (data.getSentiment()){
            case HAPPY:
                textResourceId = R.string.text_sentiment_happy;
                backgroundColor = ContextCompat.getColor(context, R.color.sentimentHappy);
                break;
            case SAD:
                textResourceId = R.string.text_sentiment_sad;
                backgroundColor = ContextCompat.getColor(context, R.color.sentimentSad);
                break;
        }

        binding.textSentiment.setText(getString(textResourceId));
        binding.textSentiment.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.cardview.setCardBackgroundColor(backgroundColor);

    }

    private void showTweet(TweetModel data) {
        binding.textTweet.setText(getString(R.string.text_tweet, data.getText()));
        binding.textDate.setText(dateFormat.format(data.createdAt()));
    }

}
