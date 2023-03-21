package com.crazyostudio.groupstudyapp.MFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentHomeBinding;

public class homeFragment extends Fragment {
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.createGroupBtu.setOnClickListener(view->
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,new MakeGroupFragment()).commit();

        });

        return binding.getRoot();
    }
}