package com.crazyostudio.groupstudyapp.MFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyostudio.groupstudyapp.GroupManger2Activity;
import com.crazyostudio.groupstudyapp.GroupMangerActivity;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.FragmentGroupCreateLayoutBinding;
import com.crazyostudio.groupstudyapp.onCheck;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.database.FirebaseDatabase;

public class GroupCreateLayoutFragment extends Fragment  {
    FragmentGroupCreateLayoutBinding binding;
    Uri dataUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroupCreateLayoutBinding.inflate(inflater,container,false);

        binding.userImage.setOnClickListener(view ->
                ImagePicker.with(this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(1));

        binding.next.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), GroupManger2Activity.class);
            intent.putExtra("Name",binding.Groupname.getText().toString());
            intent.putExtra("Images",dataUri.toString());
            requireContext().startActivity(intent);
            getActivity().finish();
//            Bundle bundle = new Bundle();
//             MakeGroupFragment makeGroupFragment = new MakeGroupFragment();
//            makeGroupFragment.setArguments(bundle);
//            assert getFragmentManager() != null;
//            getFragmentManager().beginTransaction().replace(R.id.view,makeGroupFragment).commit();
        });


        return binding.getRoot();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (data.getData() != null && requestCode==1) {
            dataUri = data.getData();
            binding.userImage.setImageURI(dataUri);
        }
    }

}