package com.crazyostudio.groupstudyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.Model.GroupModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.UserlookBinding;
import com.crazyostudio.groupstudyapp.onCheck;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class UserInfoAdapters extends RecyclerView.Adapter<UserInfoAdapters.UserInfoAdaptersViewHolder>{
    FloatingActionButton floatingActionButton;
    ArrayList<UserAccountModel> userInfo;
    ArrayList<String> userid = new ArrayList<>();
    onCheck onCheck;
    ArrayList<GroupModel> groupModels;
    Context context;

    public UserInfoAdapters(ArrayList<UserAccountModel> userInfo, Context context,FloatingActionButton floatingActionButton1,onCheck onCheck1) {
        this.userInfo = userInfo;
        this.context = context;
        this.floatingActionButton = floatingActionButton1;
        this.onCheck = onCheck1;
    }
    @NonNull
    @Override
    public UserInfoAdaptersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserInfoAdaptersViewHolder(LayoutInflater.from(context).inflate(R.layout.userlook,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdaptersViewHolder holder, int position) {
        UserAccountModel product = userInfo.get(position);
        Glide.with(context).load(product.getUserDP()).into(holder.binding.UserImage);
        holder.binding.name.setText(product.getName());
        groupModels = new ArrayList<>();
        holder.itemView.setOnClickListener(view -> {
            if (holder.binding.selected.getVisibility()==View.GONE) {
                holder.binding.selected.setVisibility(View.VISIBLE);
                userid.add(position,product.getId());
            }else {
                holder.binding.selected.setVisibility(View.GONE);
                userid.remove(position);

            }
            if (!userid.isEmpty()) {
                floatingActionButton.setVisibility(View.VISIBLE);
            }
            else {
                floatingActionButton.setVisibility(View.GONE);
            }
        });

        floatingActionButton.setOnClickListener(view ->{

            onCheck.setResult(userid);
        });

    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }


    public static class UserInfoAdaptersViewHolder  extends RecyclerView.ViewHolder {
        UserlookBinding binding;
        public UserInfoAdaptersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = UserlookBinding.bind(itemView);
        }
    }
}
