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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafamatias.nlp.R;
import com.rafamatias.nlp.databinding.FragmentTweetBinding;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.Tweet;
import com.rafamatias.nlp.presentation.viewModel.TweetViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TweetFragment extends Fragment {

    private static final String PARAM_TWEET = "TweetFragment.Param.Tweet";

    private FragmentTweetBinding binding;
    private TweetViewModel viewModel;
    private Tweet tweet;
    private AssetManager assetManager;

    public TweetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TweetFragment.
     */
    public static TweetFragment newInstance(Tweet tweet) {
        TweetFragment fragment = new TweetFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_TWEET, tweet);

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

        tweet = args.getParcelable(PARAM_TWEET);
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
        setupViewModel(tweet);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        assetManager = context.getAssets();
        viewModel = ViewModelProviders.of(this).get(TweetViewModel.class);
    }

    private void setupLayout(){
        Typeface typeFace = Typeface.createFromAsset(assetManager, "fonts/BodoniXT.ttf");
        binding.textTweet.setTypeface(typeFace);
    }

    private void setupViewModel(Tweet tweet) {
        viewModel.init(tweet);
        viewModel.getText().observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(@Nullable Resource<String> stringResource) {
                switch (stringResource.state){
                    case SUCCESS:
                        binding.textTweet.setText(getString(R.string.text_tweet, stringResource.data));
                        break;
                }
            }
        });
    }



}
