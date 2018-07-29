package com.rafamatias.nlp.presentation.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rafamatias.nlp.R;
import com.rafamatias.nlp.databinding.ActivityTweetsBinding;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.Tweet;
import com.rafamatias.nlp.presentation.view.adapter.TweetsAdapter;
import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;
import com.rafamatias.nlp.presentation.viewModel.TweetsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link AppCompatActivity} subclass.
 * Uses {@link ViewPager} to show fragments of {@link TweetFragment}
 * @see TweetsAdapter
 * @see TweetFragment
 */
public class TweetsActivity extends AppCompatActivity {

    private ActivityTweetsBinding binding;
    private TweetsAdapter viewPagerAdapter;
    private List<Tweet> viewPagerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tweets);

        setupViewModel();
        setupViewPager();
    }

    private void setupViewModel() {
        TweetsViewModel viewModel = ViewModelProviders.of(this).get(TweetsViewModel.class);
        viewModel.getTweets().observe(this, getTweetsObserver());
        viewModel.getUsername().observe(this, getUsernameObserver());
    }

    private void setupViewPager() {
        viewPagerAdapter = new TweetsAdapter(getSupportFragmentManager(), viewPagerData);
        binding.viewPager.setAdapter(viewPagerAdapter);
    }

    private Observer<Resource<String>> getUsernameObserver(){
        return new Observer<Resource<String>>() {
            @Override
            public void onChanged(@Nullable Resource<String> stringResource) {
                binding.textName.setText(stringResource.data);
            }
        };
    }

    private Observer<Resource<List<Tweet>>> getTweetsObserver() {
        return new Observer<Resource<List<Tweet>>>() {
            @Override
            public void onChanged(Resource<List<Tweet>> resource) {
                switch (resource.state){
                    case ERROR:
                        // TODO: Implement error handler
                        break;
                    case LOADING:
                        // TODO: Implement loading handler
                        break;
                    default:
                        viewPagerData.addAll(resource.data);
                        viewPagerAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }
}
