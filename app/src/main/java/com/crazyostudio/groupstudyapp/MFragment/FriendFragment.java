package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crazyostudio.groupstudyapp.Adapter.UserInfoAdapters;
import com.crazyostudio.groupstudyapp.MainActivity;
import com.crazyostudio.groupstudyapp.Model.GroupModel;
import com.crazyostudio.groupstudyapp.Model.MakeFriendModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentFriendBinding;
import com.crazyostudio.groupstudyapp.onCheck;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;


public class FriendFragment extends Fragment implements onCheck {
    FragmentFriendBinding binding;
    FirebaseDatabase users;
    ProgressDialog bar;
    FirebaseAuth auth;
    //    private StorageReference reference;
    UserInfoAdapters userInfoAdapters;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFriendBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance();
//        reference = FirebaseStorage.getInstance().getReference("Image");
        bar = new ProgressDialog(getContext());
        bar.setMessage("Wait");
        getUser();
        return binding.getRoot();
    }

    void getUser() {
        ArrayList<UserAccountModel> userInfoS = new ArrayList<>();
        userInfoAdapters = new UserInfoAdapters(userInfoS, getContext(), binding.makeGroup, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rec.setLayoutManager(layoutManager);
        binding.rec.setAdapter(userInfoAdapters);
        ArrayList<String> list = new ArrayList<>();
        users.getReference().child("Friend").child("F+" + auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    MakeFriendModel model = snapshot.getValue(MakeFriendModel.class);
                    assert model != null;
                    list.addAll(model.getFriendId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (list.isEmpty()) {
            users.getReference().child("Accounts").addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userInfoS.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        UserAccountModel userInfo = snapshot1.getValue(UserAccountModel.class);
//                        if (list.isEmpty()) {
                            assert userInfo != null;
                            userInfo.setId(snapshot1.getKey());
                            userInfoS.add(userInfo);
//                        }
//                        else {
                        userInfoAdapters.notifyDataSetChanged();
                        binding.rec.setVisibility(View.VISIBLE);
                        binding.text.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else {
            users.getReference().child("Accounts").addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userInfoS.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        UserAccountModel userInfo = snapshot1.getValue(UserAccountModel.class);
//                        if (list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            if (!Objects.equals(snapshot1.getKey(), list.get(i))) {
                                assert userInfo != null;
                                userInfo.setId(snapshot1.getKey());
                                userInfoS.add(userInfo);
                            }
                        }
//                        }
//                        else {
                        userInfoAdapters.notifyDataSetChanged();
                        binding.rec.setVisibility(View.VISIBLE);
                        binding.text.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    @Override
    public void setResult(ArrayList<String> groupModel) {
        MakeFriendModel model = new MakeFriendModel(groupModel, auth.getUid());
        users.getReference().child("Friend").child("F+" + auth.getUid()).setValue(model).addOnSuccessListener(unused -> {
            if (bar.isShowing()) {
                bar.dismiss();
                getActivity().finish();
                startActivity(new Intent(requireContext(), MainActivity.class));
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frameLayout,new homeFragment());
//                    transaction.addToBackStack(null).commit();
            }
        }).addOnFailureListener(e -> Toast.makeText(getContext(), "Fall", Toast.LENGTH_SHORT).show()
        );
    }
}