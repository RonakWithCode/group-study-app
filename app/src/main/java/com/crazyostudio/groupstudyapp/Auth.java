package com.crazyostudio.groupstudyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.crazyostudio.groupstudyapp.MFragment.SignUpFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityAuthBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Auth extends AppCompatActivity {
    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        LoadFragment(new SignUpFragment(), 0);
    }

    public void LoadFragment(Fragment fragment, int id) {


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == 0) {

            transaction.add(binding.getRoot().getId(), fragment);
            transaction.addToBackStack(null).commit();
        } else if (id == 1) {
            transaction.replace(binding.getRoot().getId(), fragment);
            transaction.addToBackStack(null).commit();
        }
//        else if (id==2){
//            transaction.replace(binding.getRoot().getId(),fragment);
//            transaction.commit();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(FirebaseAuth.getInstance().getCurrentUser() == null)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }
    }
}