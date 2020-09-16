package com.tunyin.ui.adapter.mine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tunyin.ui.fragment.mine.RecordsListFragment;

public class TransactionVPAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public TransactionVPAdapter(@NonNull FragmentManager fm, String[] titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return RecordsListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}