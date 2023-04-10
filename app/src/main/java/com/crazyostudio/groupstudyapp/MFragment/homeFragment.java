package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.crazyostudio.groupstudyapp.Adapter.GroupAdapters;
import com.crazyostudio.groupstudyapp.GroupMangerActivity;
import com.crazyostudio.groupstudyapp.Model.GroupModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class homeFragment extends Fragment {
    FragmentHomeBinding binding;
    FirebaseDatabase users;

    ArrayList<GroupModel> userInfoS = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        users = FirebaseDatabase.getInstance();
        getGroup();



        binding.createGroupBtu.setOnClickListener(view->
        {
            Intent intent =new Intent(getContext(), GroupMangerActivity.class);intent.putExtra("ID","0");
            requireContext().startActivity(intent);

//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.view,new GroupCreateLayoutFragment()).commit();

        });

        return binding.getRoot();
    }
//    void getGroup(){
//
//    }
    void getGroup() {
        GroupAdapters groupAdapters = new GroupAdapters(userInfoS, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(groupAdapters);
        users.getReference().child("Group").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userInfoS.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    GroupModel userInfo = snapshot1.getValue(GroupModel.class);
                    if (userInfo != null) {
                        binding.recycler.setVisibility(View.VISIBLE);
                        binding.text.setVisibility(View.GONE);
                        for (int i = 0; i < Objects.requireNonNull(userInfo).getId().size(); i++) {
                            if (userInfo.getId().get(i).matches(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))) {
                                userInfoS.add(userInfo);

                            }
                        }


                    }
                }
                groupAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}