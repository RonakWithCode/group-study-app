package com.crazyostudio.groupstudyapp.Model;

import java.util.ArrayList;

public class MakeFriendModel {
    private ArrayList<String> FriendId;
    private String id;

    public MakeFriendModel() {
    }

    public MakeFriendModel(ArrayList<String> friendId, String id) {
        FriendId = friendId;
        this.id = id;
    }

    public ArrayList<String> getFriendId() {
        return FriendId;
    }

    public void setFriendId(ArrayList<String> friendId) {
        FriendId = friendId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
