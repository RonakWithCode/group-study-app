package com.crazyostudio.groupstudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.crazyostudio.groupstudyapp.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}