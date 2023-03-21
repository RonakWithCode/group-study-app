package com.crazyostudio.groupstudyapp;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.crazyostudio.groupstudyapp.MFragment.homeFragment;
import com.crazyostudio.groupstudyapp.MFragment.profileFragment;
import com.crazyostudio.groupstudyapp.MFragment.settingFragment;
import com.crazyostudio.groupstudyapp.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoadFragment(new homeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
        switch (item.getItemId())  {
            case R.id.home:
                LoadFragment(new homeFragment());
                break;
            case R.id.profile:
                LoadFragment(new profileFragment());
                break;

            case R.id.setting:
                LoadFragment(new settingFragment());
                break;
        }
        return true;
    });
    }
    public void LoadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLayout.getId(),fragment);
        transaction.addToBackStack(null).commit();

    }

}









