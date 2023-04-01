package com.crazyostudio.groupstudyapp.Model;

public class ChatModel {
    private boolean IsImage;
    private String ID,Message,SanderName,SanderImage,Gander;
    private long SandTime;
    private boolean isGroup;


    public ChatModel(boolean isImage, String ID, String message, String sanderName, String sanderImage, long sandTime, boolean isGroup,String gander) {
        IsImage = isImage;
        this.ID = ID;
        Message = message;
        SanderName = sanderName;
        SanderImage = sanderImage;
        SandTime = sandTime;
        this.isGroup = isGroup;
        Gander = gander;
    }
    public ChatModel(boolean isImage, String ID, String message,long sandTime, boolean isGroup) {
        IsImage = isImage;
        this.ID = ID;
        Message = message;
        SandTime = sandTime;
        this.isGroup = isGroup;
    }
    public ChatModel(){}

    public String getGander() {
        return Gander;
    }

    public void setGander(String gander) {
        Gander = gander;
    }

    public boolean isImage() {
        return IsImage;
    }

    public void setImage(boolean image) {
        IsImage = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSanderName() {
        return SanderName;
    }

    public void setSanderName(String sanderName) {
        SanderName = sanderName;
    }

    public String getSanderImage() {
        return SanderImage;
    }

    public void setSanderImage(String sanderImage) {
        SanderImage = sanderImage;
    }

    public long getSandTime() {
        return SandTime;
    }

    public void setSandTime(long sandTime) {
        SandTime = sandTime;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
