package com.crazyostudio.groupstudyapp.Model;

import java.util.ArrayList;

public class GroupModel{
    private ArrayList<String> id;
    private String GroupDP,Name,Bio,GroupId;
    private boolean isPublic;

    public GroupModel(){}

    public GroupModel(ArrayList<String> id, String groupDP, String name, String bio,String groupId ,boolean isPublic) {
        this.id = id;
        GroupDP = groupDP;
        Name = name;
        Bio = bio;
        GroupId = groupId;
        this.isPublic = isPublic;
    }



    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public ArrayList<String> getId() {
        return id;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }

    public String getGroupDP() {
        return GroupDP;
    }

    public void setGroupDP(String groupDp) {
        GroupDP = groupDp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
