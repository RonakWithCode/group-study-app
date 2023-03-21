package com.crazyostudio.groupstudyapp.MFragment;

import static com.crazyostudio.groupstudyapp.ClassHolders.basic.Check;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.crazyostudio.groupstudyapp.MainActivity;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentSignUpBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    ProgressDialog bar;
     boolean imageBts;
     private StorageReference reference;
     FirebaseDatabase db;
     private Uri dataUri;
     private FirebaseAuth auth;
@Override
public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSignUpBinding.inflate(inflater, container, false);
    auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = FirebaseStorage.getInstance().getReference("Image");
        bar = new ProgressDialog(getContext());
        binding.userImage.setOnClickListener(view ->
                ImagePicker.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(1));
        binding.Login.setOnClickListener(view-> StartLogin());
        binding.SignBtu.setOnClickListener(view -> {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        if (Check(binding.Name)) {
                            binding.Name.setError("Enter Name");
                            i = 0;
                            break;
                        }
                    case 1:

                        if (Check(binding.mail)) {
                            binding.mail.setError("Enter Mail");
                            i = 0;
                            break;
                        }
                    case 2:
                        if (Check(binding.pass)) {
                            binding.pass.setError("Enter Password");
                            i = 0;
                            break;
                        }
                    case 3:
                        CreateAccount();
                        break;
                    default:
                        break;
                }
            }
        });
        return binding.getRoot();
    }
    public void StartLogin(){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_view,new LoginFragment());
            transaction.addToBackStack(null).commit();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (data.getData() != null && requestCode==1) {
            dataUri = data.getData();
            binding.userImage.setImageURI(dataUri);
            imageBts = true;
        }
    }
    private void CreateAccount() {
        bar.setMessage("Wait Account is Creating");
        bar.show();
        auth.createUserWithEmailAndPassword(binding.mail.getText().toString(),binding.pass.getText().toString()).addOnSuccessListener(authResult ->
                        UploadImage(dataUri))
                .addOnFailureListener(e ->
        {
            Toast.makeText(getContext(), "Some Things was Wornrg..", Toast.LENGTH_SHORT).show();
            bar.dismiss();
        });
    }
    private void UploadImage(Uri image) {
        String endName = "";
        if(image.getPath().endsWith(".png")){
            endName = ".png";
        }else if(image.getPath().endsWith(".jpg")) {
            endName = ".jpg";
        }
        StorageReference file = reference.child(System.currentTimeMillis() + endName);
        file.putFile(image).addOnSuccessListener(taskSnapshot -> file.getDownloadUrl().addOnSuccessListener(uri -> {
            UserAccountModel userAccountModel = new UserAccountModel(auth.getUid(),uri.toString(),binding.Name.getText().toString(),binding.mail.getText().toString(),binding.pass.getText().toString(),System.currentTimeMillis());
            db.getReference().child("Accounts").child(Objects.requireNonNull(auth.getUid())).setValue(userAccountModel);
            if (bar.isShowing()) {
                bar.dismiss();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        })).addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

}