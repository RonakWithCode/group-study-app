package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyostudio.groupstudyapp.Adapter.GroupAdapters;
import com.crazyostudio.groupstudyapp.Adapter.UserInfoAdapters;
import com.crazyostudio.groupstudyapp.Adapter.getUserAdapters;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentUserlayoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class UserlayoutFragment extends Fragment {
    FragmentUserlayoutBinding binding;
    getUserAdapters groupAdapters;
    FirebaseDatabase users;
    ProgressDialog bar;
    FirebaseAuth auth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserlayoutBinding.inflate(inflater,container,false);
        auth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance();
        getUser();
        return binding.getRoot();
    }
    void getUser() {
        ArrayList<UserAccountModel> userInfoS = new ArrayList<>();
        groupAdapters = new getUserAdapters(userInfoS, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rec.setLayoutManager(layoutManager);
        binding.rec.setAdapter(groupAdapters);
        users.getReference().child("Accounts").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userInfoS.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UserAccountModel userInfo = snapshot1.getValue(UserAccountModel.class);
                    if (!Objects.equals(snapshot1.getKey(), auth.getUid())) {
                        assert userInfo != null;
                        userInfo.setId(snapshot1.getKey());
                        userInfoS.add(userInfo);

                    }
                    groupAdapters.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}