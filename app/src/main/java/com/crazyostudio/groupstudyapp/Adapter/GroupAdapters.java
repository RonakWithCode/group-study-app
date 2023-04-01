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
import com.crazyostudio.groupstudyapp.databinding.UserlookBinding;

import java.util.ArrayList;

public class GroupAdapters extends RecyclerView.Adapter<GroupAdapters.GroupAdaptersViewHolder>{
    ArrayList<GroupModel> groupModels;
    Context context;

    public GroupAdapters(ArrayList<GroupModel> groupModels, Context context) {
        this.groupModels = groupModels;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupAdaptersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupAdapters.GroupAdaptersViewHolder(LayoutInflater.from(context).inflate(R.layout.grouplook,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdaptersViewHolder holder, int position) {
        GroupModel GM = groupModels.get(position);
        holder.binding.name.setText(GM.getName());
        holder.binding.lastStatus.setText(GM.getBio());
        Glide.with(context).load(GM.getGroupDP()).into(holder.binding.UserImage);
//        if (holder.binding)
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("Name",GM.getName());
            intent.putExtra("id",GM.getId());
            intent.putExtra("DP",GM.getGroupDP());
            intent.putExtra("Bio",GM.getBio());
            intent.putExtra("GroupId",GM.getGroupId());
            intent.putExtra("isChat","1");

            this.context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return groupModels.size();
    }

    public static class GroupAdaptersViewHolder  extends RecyclerView.ViewHolder {
        GrouplookBinding binding;
        public GroupAdaptersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = GrouplookBinding.bind(itemView);
        }
    }
}
