package com.crazyostudio.groupstudyapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.crazyostudio.groupstudyapp.Adapter.MainViewPagerAdapter;
import com.crazyostudio.groupstudyapp.MFragment.FriendFragment;
import com.crazyostudio.groupstudyapp.MFragment.UserlayoutFragment;
import com.crazyostudio.groupstudyapp.MFragment.homeFragment;
import com.crazyostudio.groupstudyapp.MFragment.profileFragment;
import com.crazyostudio.groupstudyapp.MFragment.settingFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tabMode.setupWithViewPager(binding.views);
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new homeFragment(), "Groups");
        viewPagerAdapter.addFragment(new UserlayoutFragment(), "MY Friends");
//        viewPagerAdapter.addFragment(new FriendFragment(), "Find Friends");
        binding.views.setAdapter(viewPagerAdapter);
    }
}









