package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.Adapter.ChatAdapters;
import com.crazyostudio.groupstudyapp.ChatActivity;
import com.crazyostudio.groupstudyapp.Model.ChatModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.chatmenuOncheck;
import com.crazyostudio.groupstudyapp.databinding.FragmentChatGroupBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class ChatGroupFragment extends Fragment implements chatmenuOncheck {
    FragmentChatGroupBinding binding;
    String UserName,UserImage,UserBio,UserGroupId;
    FirebaseDatabase firebaseDatabase;
    ArrayList<ChatModel> ChatModels = new ArrayList<>();
    FirebaseAuth auth;
    UserAccountModel userAccountModel;
    ChatAdapters chatAdapters;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatGroupBinding.inflate(inflater,container,false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        UserName = getActivity().getIntent().getStringExtra("Name");
        UserImage = getActivity().getIntent().getStringExtra("DP");
        UserBio = getActivity().getIntent().getStringExtra("Bio");
        UserGroupId = getActivity().getIntent().getStringExtra("GroupId");
        binding.toolbar2.setOnClickListener(view -> {
//            Intent intent = new Intent(this, SeeUserProfile.class);
//            intent.putExtra("name",UserName);
//            intent.putExtra("Images",UserImage);
//            intent.putExtra("Bio",UserBio);
//            startActivity(intent);
        });

        userAccountModel = new UserAccountModel();
        firebaseDatabase.getReference().child("Accounts").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userAccountModel = dataSnapshot.getValue(UserAccountModel.class);
            }
            @Override public void onCancelled(@NonNull DatabaseError databaseError) {}
        });


        binding.username.setText(UserName);
        Glide.with(this).load(UserImage).into(binding.userImage);
        binding.BackBts.setOnClickListener(view -> getActivity().finish());

        chatAdapters = new ChatAdapters(ChatModels,getContext(),this);
        binding.recyclerView2.setAdapter(chatAdapters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView2.setLayoutManager(layoutManager);
        firebaseDatabase.getReference().child("chats").child(UserGroupId).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ChatModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    ChatModel _ChatModel = snapshot1.getValue(ChatModel.class);
                    ChatModels.add(_ChatModel);
                }
                chatAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.imageBts.setOnClickListener(view ->
                ImagePicker.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(800, 800)
                        .start(1));


        binding.SandBts.setOnClickListener(view -> {
            if (!binding.InputText.getText().toString().isEmpty()) {
                final ChatModel Chat = new ChatModel(false,auth.getUid(),binding.InputText.getText().toString(), Objects.requireNonNull(auth.getCurrentUser()).getDisplayName(), Objects.requireNonNull(auth.getCurrentUser().getPhotoUrl()).toString(),System.currentTimeMillis(),true,userAccountModel.getGander());
                binding.InputText.setText("");
                firebaseDatabase.getReference().child("chats").child(UserGroupId).push().setValue(Chat).addOnSuccessListener(unused -> chatAdapters.notifyDataSetChanged());
            }
            else {
                Toast.makeText(getContext(), "Enter your text", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onCheck(ChatModel chatModel, TextView button) {
        PopupMenu popupMenu = new PopupMenu(getContext(), button);
        popupMenu.getMenuInflater().inflate(R.menu.chatmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId()==R.id.message){
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("name",chatModel.getSanderName());
                intent.putExtra("UserId",chatModel.getID());
                intent.putExtra("Images",chatModel.getSanderImage());
                intent.putExtra("isChat","0");
                getActivity().startActivity(intent);
                getActivity().finish();
            }
            return true;
        });
        popupMenu.show();
    }
}