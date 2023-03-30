package com.crazyostudio.groupstudyapp.Model;

import java.util.ArrayList;

public class GroupModel{
    private ArrayList<String> id;
    private String UserDP,Name,Bio;
    private boolean isPublic;

    public GroupModel(){}

    public GroupModel(ArrayList<String> id, String userDP, String name, String bio, boolean isPublic) {
        this.id = id;
        UserDP = userDP;
        Name = name;
        Bio = bio;
        this.isPublic = isPublic;
    }

//    public GroupModel(ArrayList<String> id) {
//        this.id = id;
//    }

    public ArrayList<String> getId() {
        return id;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }

    public String getUserDP() {
        return UserDP;
    }

    public void setUserDP(String userDP) {
        UserDP = userDP;
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
