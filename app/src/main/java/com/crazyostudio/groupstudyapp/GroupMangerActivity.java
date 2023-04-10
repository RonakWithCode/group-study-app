package com.crazyostudio.groupstudyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.crazyostudio.groupstudyapp.MFragment.GroupCreateLayoutFragment;
import com.crazyostudio.groupstudyapp.MFragment.MakeGroupFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityGroupMangerBinding;
import com.crazyostudio.groupstudyapp.databinding.FragmentMakeGroupBinding;

import java.util.Objects;

public class GroupMangerActivity extends AppCompatActivity {
    ActivityGroupMangerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupMangerBinding
                .inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();


//        int Id;
//        Id = Integer.parseInt(getIntent().getStringExtra("ID"));
//
//        if (Id == 0) {
            getSupportFragmentManager().beginTransaction().add(binding.frame.getId(), new GroupCreateLayoutFragment()).commit();
//        }

    }
}