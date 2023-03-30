package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyostudio.groupstudyapp.Adapter.UserInfoAdapters;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class homeFragment extends Fragment {
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.createGroupBtu.setOnClickListener(view->
        {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new GroupCreateLayoutFragment()).commit();

        });

        return binding.getRoot();
    }
//    void getGroup(){
//
//    }
    void getGroup() {

        ArrayList<UserAccountModel> userInfoS = new ArrayList<>();
        GroupAdapters = new UserInfoAdapters(userInfoS, getContext(),binding.makeGroup   ,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rec.setLayoutManager(layoutManager);
        binding.rec.setAdapter(userInfoAdapters);
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
                    userInfoAdapters.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}