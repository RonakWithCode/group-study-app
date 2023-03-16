package com.crazyostudio.groupstudyapp.Fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    FirebaseAuth auth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, true);
        auth = FirebaseAuth.getInstance();
        binding.SignBtu.setOnClickListener(view -> {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        if (!isEmpty(binding.Name)) {
                            binding.Name.setError("Enter Name");
                            i = 0;
                            break;
                        }
                    case 1:
                        if (!isEmpty(binding.mail)) {
                            binding.mail.setError("Enter Mail");
                            i = 0;
                            break;
                        }
                    case 2:
                        if (!isEmpty(binding.pass)) {
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

    private void CreateAccount() {
        // make Account
        auth.createUserWithEmailAndPassword(binding.mail.getText().toString(),binding.pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                UserAccountModel userAccountModel = new UserAccountModel(authResult.getUser().getUid(),binding.Name.getText().toString(),binding.mail.getText().toString(),binding.pass.getText().toString(),System.currentTimeMillis());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() == 0) return false;
        return true;
    }

}