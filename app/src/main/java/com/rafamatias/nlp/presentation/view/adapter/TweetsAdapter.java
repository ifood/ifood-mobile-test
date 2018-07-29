package com.rafamatias.nlp.presentation.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rafamatias.nlp.presentation.model.Tweet;
import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;

import java.util.List;

/**
 * Simple {@link FragmentPagerAdapter} subclass.
 * Used to show fragments of {@link TweetFragment}
 */
public class TweetsAdapter extends FragmentPagerAdapter {

    private final List<Tweet> items;

    public TweetsAdapter(FragmentManager fm, List<Tweet> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        return TweetFragment.newInstance(items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
