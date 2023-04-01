package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.Adapter.ChatAdapters;
import com.crazyostudio.groupstudyapp.Model.ChatModel;
import com.crazyostudio.groupstudyapp.databinding.FragmentChatBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;


public class ChatFragment extends Fragment  {
    FragmentChatBinding binding;
//    private StorageReference reference;
    String UserName,UserImage,UserId,SandId,UserBio;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    ChatAdapters chatAdapters;
    String sanderRoom,recRoom;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater,container,false);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        reference = FirebaseStorage.getInstance().getReference("ChatImage");
        auth = FirebaseAuth.getInstance();
        UserName = getActivity().getIntent().getStringExtra("name");
        UserImage = getActivity().getIntent().getStringExtra("Images");
        UserBio = getActivity().getIntent().getStringExtra("Bio");
        SandId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        UserId = getActivity().getIntent().getStringExtra("UserId");

        binding.toolbar2.setOnClickListener(view -> {
//            Intent intent = new Intent(this, SeeUserProfile.class);
//            intent.putExtra("name",UserName);
//            intent.putExtra("Images",UserImage);
//            intent.putExtra("Bio",UserBio);
//            startActivity(intent);
        });


        binding.username.setText(UserName);
        Glide.with(getContext()).load(UserImage).into(binding.userImage);
        binding.BackBts.setOnClickListener(view -> getActivity().finish());

        ArrayList<ChatModel> ChatModels = new ArrayList<>();
        chatAdapters = new ChatAdapters(ChatModels,getContext());
        binding.recyclerView2.setAdapter(chatAdapters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView2.setLayoutManager(layoutManager);

        sanderRoom = SandId + UserId;
        recRoom = UserId + SandId;

        firebaseDatabase.getReference().child("chats").child(sanderRoom).addValueEventListener(new ValueEventListener() {

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
                final ChatModel Chat =  new ChatModel(false,SandId,binding.InputText.getText().toString(),System.currentTimeMillis(),false);
                binding.InputText.setText("");
                firebaseDatabase.getReference().child("chats").child(sanderRoom).push().setValue(Chat).addOnSuccessListener(unused -> firebaseDatabase.getReference().child("chats").child(recRoom).push().setValue(Chat).addOnSuccessListener(unused1 -> chatAdapters.notifyDataSetChanged()));
            }
            else {
                Toast.makeText(getContext(), "Enter your text", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }


}