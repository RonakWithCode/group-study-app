package com.crazyostudio.groupstudyapp.Model;

public class UserAccountModel {
    private String id,UserDP,name,mail,pass,gander,Bio,Work;
    private long createTime,Number;
    private int age;
    private boolean isPublic;
    public UserAccountModel(){}
    public UserAccountModel(String id, String userDP, String name, String mail, String pass, long createTime,long number,String Gander,String bio,String work) {
        this.id = id;
        UserDP = userDP;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
        this.createTime = createTime;
        this.Number = number;
        this.gander = Gander;
        this.Bio =bio;
        this.Work = work;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public String getUserDP() {
        return UserDP;
    }

    public void setUserDP(String userDP) {
        UserDP = userDP;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public long getNumber() {
        return Number;
    }

    public void setNumber(long number) {
        Number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
