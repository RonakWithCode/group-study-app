package com.crazyostudio.groupstudyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.ChatActivity;
import com.crazyostudio.groupstudyapp.Model.GroupModel;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.GrouplookBinding;

import java.util.ArrayList;
import java.util.Objects;

public class getUserAdapters extends RecyclerView.Adapter<getUserAdapters.getUserAdaptersViewHolder>{

    ArrayList<UserAccountModel> userAccount;
    Context context;

    public getUserAdapters(ArrayList<UserAccountModel> userAccount, Context context) {
        this.userAccount = userAccount;
        this.context = context;
    }

    @NonNull
    @Override
    public getUserAdaptersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new getUserAdapters.getUserAdaptersViewHolder(LayoutInflater.from(context).inflate(R.layout.grouplook,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull getUserAdaptersViewHolder holder, int position) {
        UserAccountModel user = userAccount.get(position);
        holder.binding.name.setText(user.getName());
//        holder.binding.lastStatus.setText(user);
        Glide.with(context).load(user.getUserDP()).into(holder.binding.UserImage);
//        if (holder.binding)
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("name",user.getName());
            intent.putExtra("UserId",user.getId());
            intent.putExtra("Images",user.getUserDP());
            intent.putExtra("isChat","0");
//            intent.putExtra("Bio",user.getBio());
//            intent.putExtra("GroupId",user.getGroupId());
            this.context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return userAccount.size();
    }

    public static class getUserAdaptersViewHolder  extends RecyclerView.ViewHolder {
        GrouplookBinding binding;
        public getUserAdaptersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = GrouplookBinding.bind(itemView);
        }
    }

}
