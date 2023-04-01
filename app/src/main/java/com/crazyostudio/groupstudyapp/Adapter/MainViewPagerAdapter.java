package com.crazyostudio.groupstudyapp.Adapter;
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//
//import java.util.ArrayList;
//
//public class MainViewPagerAdapter extends FragmentPagerAdapter {
//    private final ArrayList<Fragment> fragmentsArray = new ArrayList<>();
//    private final ArrayList<String> fragmentString = new ArrayList<>();
//
//    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return fragmentsArray.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragmentsArray.size();
//    }
//
//    public void addFragment(Fragment fragment, String title) {
//        fragmentsArray.add(fragment);
//        fragmentString.add(title);
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentString.get(position);
//    }
//}
