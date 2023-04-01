package com.crazyostudio.groupstudyapp.MFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.crazyostudio.groupstudyapp.Adapter.UserInfoAdapters;
import com.crazyostudio.groupstudyapp.MainActivity;
import com.crazyostudio.groupstudyapp.Model.GroupModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentMakeGroupBinding;
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

public class MakeGroupFragment extends Fragment implements onCheck {
    FragmentMakeGroupBinding binding;
    FirebaseDatabase users;
    ProgressDialog bar;
    FirebaseAuth auth;
    private StorageReference reference;
    String Name;
    Uri uri;
    UserInfoAdapters userInfoAdapters;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMakeGroupBinding.inflate(getLayoutInflater());
//        Button button =
        auth = FirebaseAuth.getInstance();
        users = FirebaseDatabase.getInstance();
        reference = FirebaseStorage.getInstance().getReference("Image");
        bar = new ProgressDialog(getContext());
        bar.setMessage("Wait");
        Bundle bundle = this.getArguments();
        assert bundle != null;
        Name = bundle.getString("name");
        uri = Uri.parse(bundle.getString("image"));
//        binding.makeGroup.setOnClickListener(view -> CreateGroup(uri));
        getUser();

        return binding.getRoot();


    }


    void getUser() {
        ArrayList<UserAccountModel> userInfoS = new ArrayList<>();
        userInfoAdapters = new UserInfoAdapters(userInfoS, getContext(),binding.makeGroup   ,this);
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

    @Override
    public void setResult(ArrayList<String> groupModel) {
        //            GroupModel groupModel1 = new GroupModel(userid);
//            groupModels.add(groupModel1);
        groupModel.add(auth.getUid());
        bar.show();
        String endName = "";
        if (uri.getPath().endsWith(".png")) {
            endName = ".png";
        } else if (uri.getPath().endsWith(".jpg")) {
            endName = ".jpg";
        }
        String GroupId = Name + auth.getCurrentUser().getUid()+"----"+System.currentTimeMillis();
        StorageReference file = reference.child(System.currentTimeMillis() + endName);
        file.putFile(uri).addOnSuccessListener(taskSnapshot-> file.getDownloadUrl().addOnSuccessListener(uri->{
            GroupModel model = new  GroupModel(groupModel,uri.toString(),Name,"Is a Group",GroupId,false);
            users.getReference().child("Group").push().setValue(model).addOnSuccessListener(unused -> {
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

        })).addOnFailureListener(e -> {
            if (bar.isShowing()) {
                bar.dismiss();
            }
        });


    }
}