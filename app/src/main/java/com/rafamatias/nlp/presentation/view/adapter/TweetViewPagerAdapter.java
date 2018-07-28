package com.rafamatias.nlp.presentation.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;

/**
 * Simple {@link FragmentPagerAdapter} subclass.
 * Used to show fragments of {@link TweetFragment}
 */
public class TweetViewPagerAdapter extends FragmentPagerAdapter {

    public TweetViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TweetFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 10;
    }
}
