package com.crazyostudio.groupstudyapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.crazyostudio.groupstudyapp.MFragment.ChatFragment;
import com.crazyostudio.groupstudyapp.MFragment.ChatGroupFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityChatBinding;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity{
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        int a = Integer.parseInt(getIntent().getStringExtra("isChat"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (a==0) {
            transaction.add(binding.fr.getId(),new ChatFragment());
            transaction.addToBackStack(null).commit();
        }
        else {
            transaction.replace(binding.fr.getId(),new ChatGroupFragment());
            transaction.addToBackStack(null).commit();
        }

    }

}