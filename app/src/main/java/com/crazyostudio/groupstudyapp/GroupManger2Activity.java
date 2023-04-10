package com.crazyostudio.groupstudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.crazyostudio.groupstudyapp.MFragment.MakeGroupFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityGroupManger2Binding;

import java.util.Objects;

public class GroupManger2Activity extends AppCompatActivity {
    ActivityGroupManger2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupManger2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().add(binding.getRoot().getId(),new MakeGroupFragment()).commit();


    }
}