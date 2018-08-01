package com.rafamatias.nlp.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rafamatias.nlp.presentation.model.TweetModel;
import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;

import java.util.List;

/**
 * Simple {@link FragmentPagerAdapter} subclass.
 * Used to show fragments of {@link TweetFragment}
 */
public class TweetsAdapter extends FragmentStatePagerAdapter {

    private final List<TweetModel> items;

    public TweetsAdapter(FragmentManager fm, List<TweetModel> items) {
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

}

