package com.crazyostudio.groupstudyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crazyostudio.groupstudyapp.Model.UserAccountModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.databinding.UserlookBinding;

import java.util.ArrayList;

public class UserInfoAdapters extends RecyclerView.Adapter<UserInfoAdapters.UserInfoAdaptersViewHolder>{

    ArrayList<UserAccountModel> userInfo;
    Context context;

    public UserInfoAdapters(ArrayList<UserAccountModel> userInfo, Context context) {
        this.userInfo = userInfo;
        this.context = context;
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
//        holder.binding.lastStatus.setText(product.getLastStatus());
//        holder.binding.lastTime.setText(product.getLastStatus());
        holder.itemView.setOnClickListener(view -> {
            holder.binding.selected.setVisibility(View.VISIBLE);

//            Intent intent = new Intent(context, chat.class);
//            intent.putExtra("name",product.getName());
//            intent.putExtra("Images",product.getUserImage());
//            intent.putExtra("Bio",product.getBio());
//            intent.putExtra("UserId",product.getUserid());
//            context.startActivity(intent);

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
