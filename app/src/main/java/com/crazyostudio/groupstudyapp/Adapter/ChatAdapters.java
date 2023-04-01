package com.crazyostudio.groupstudyapp.Adapter;


import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.crazyostudio.groupstudyapp.Model.ChatModel;
import com.crazyostudio.groupstudyapp.R;
import com.crazyostudio.groupstudyapp.chatmenuOncheck;
import com.crazyostudio.groupstudyapp.databinding.ReceiverBinding;
import com.crazyostudio.groupstudyapp.databinding.ReceivermessageBinding;
import com.crazyostudio.groupstudyapp.databinding.SendermessageBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapters extends  Adapter {
    ArrayList<ChatModel> ChatModels;
    Context context;
    chatmenuOncheck onCheck;
    int SANDER_VIEW_TYPE = 1, IMAGE_SANDER_VIEW_TYPE = 11;
    int GROUP_IMAGE_RECEIVER_VIEW_TYPE = 25, GROUP_RECEIVER_VIEW_TYPE = 24, RECEIVER_VIEW_TYPE = 2, IMAGE_RECEIVER_VIEW_TYPE = 22;


    public ChatAdapters(ArrayList<ChatModel> chatModels, Context context,chatmenuOncheck OnCheck) {
        ChatModels = chatModels;
        this.context = context;
        onCheck = OnCheck;
    }
    public ChatAdapters(ArrayList<ChatModel> chatModels, Context context) {
        ChatModels = chatModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==SANDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sendermessage,parent,false);
            return new SanderViewHolder(view);
        }
        else if (viewType==RECEIVER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.receiver,parent,false);
            return new ReceiverViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.receivermessage,parent,false);
            return new GroupReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (ChatModels.get(position).getID().equals(FirebaseAuth.getInstance().getUid())) {
//            if (ChatModels.get(position).isImage()) {
//                return IMAGE_SANDER_VIEW_TYPE;
//
//            }

                return SANDER_VIEW_TYPE;
//            }
        }
        else if (!ChatModels.get(position).isGroup()){
            return RECEIVER_VIEW_TYPE;
        }
        else {
            return GROUP_RECEIVER_VIEW_TYPE;
        }



    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chatModel = ChatModels.get(position);
        if (holder.getClass()==SanderViewHolder.class){
            ((SanderViewHolder)holder).SendBinding.text.setText(chatModel.getMessage());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            Date date = new Date(chatModel.getSandTime());
            String time = simpleDateFormat.format(date);
            ((SanderViewHolder)holder).SendBinding.time.setText(time);
        }
        else if (holder.getClass()==ReceiverViewHolder.class) {
            ((ReceiverViewHolder)holder).binding.text.setText(chatModel.getMessage());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            Date date = new Date(chatModel.getSandTime());
            String time = simpleDateFormat.format(date);
            ((ReceiverViewHolder)holder).binding.time.setText(time);
        }
        else {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            Date date = new Date(chatModel.getSandTime());
            String time = simpleDateFormat.format(date);
            ((GroupReceiverViewHolder)holder).binding.text.setText(chatModel.getMessage());
            ((GroupReceiverViewHolder)holder).binding.time.setText(time);
            ((GroupReceiverViewHolder)holder).binding.usernames.setText(chatModel.getSanderName());
            if (chatModel.getGander().equals("male")) {
                ((GroupReceiverViewHolder)holder).binding.usernames.setTextColor(R.color.lite_blue);
            }else {
                ((GroupReceiverViewHolder)holder).binding.usernames.setTextColor(R.color.Red_Color);
            }
//            Glide.with(context).load(chatModel.getSanderImage()).into((((ReceiverViewHolder) holder).binding.imageGchatProfileOther));

            ((GroupReceiverViewHolder)holder).binding.usernames.setOnClickListener(view-> onCheck.onCheck(chatModel, ((GroupReceiverViewHolder) holder).binding.usernames));

        }
    }
    @Override
    public int getItemCount() {
        return ChatModels.size();
    }
    public static class SanderViewHolder extends ViewHolder {
        SendermessageBinding SendBinding;
        public SanderViewHolder(@NonNull View itemView) {
            super(itemView);
            SendBinding = SendermessageBinding.bind(itemView);
        }
    }
    public static class GroupReceiverViewHolder extends ViewHolder {
        ReceivermessageBinding binding;
        public GroupReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ReceivermessageBinding.bind(itemView);
        }
    }
    public static class ReceiverViewHolder extends ViewHolder {
        ReceiverBinding binding;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ReceiverBinding.bind(itemView);
        }
    }

}