package com.crazyostudio.groupstudyapp.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<androidx.fragment.app.Fragment> fragmentsArray = new ArrayList<>();
    private final ArrayList<String> fragmentString = new ArrayList<>();
    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        return fragmentsArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsArray.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentsArray.add(fragment);
        fragmentString.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentString.get(position);
    }
}