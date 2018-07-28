package com.rafamatias.nlp.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rafamatias.nlp.R;
import com.rafamatias.nlp.databinding.ActivityMainBinding;
import com.rafamatias.nlp.presentation.view.adapter.TweetViewPagerAdapter;
import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;

/**
 * A simple {@link AppCompatActivity} subclass.
 * Uses {@link ViewPager} to show fragments of {@link TweetFragment}
 * @see TweetViewPagerAdapter
 * @see TweetFragment
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupViewPager();
    }

    private void setupViewPager() {
        TweetViewPagerAdapter viewPagerAdapter = new TweetViewPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);
    }
}
