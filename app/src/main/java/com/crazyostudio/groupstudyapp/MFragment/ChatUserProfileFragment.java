package com.crazyostudio.groupstudyapp.MFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentChatUserProfileBinding;

public class ChatUserProfileFragment extends Fragment {
    private String name,bio,image,id;
    FragmentChatUserProfileBinding binding;

    public ChatUserProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            image = getArguments().getString("image");
            bio = getArguments().getString("bio");
            id = getArguments().getString("Userid");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatUserProfileBinding.inflate(inflater,container,false);
        Glide.with(requireContext()).load(image).into(binding.userImage);
        binding.Bio.setText(bio);
        binding.Name.setText(name);
        return binding.getRoot();
    }
}