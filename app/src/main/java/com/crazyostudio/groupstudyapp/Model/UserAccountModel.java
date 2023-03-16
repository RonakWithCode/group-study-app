package com.crazyostudio.groupstudyapp.Model;

public class UserAccountModel {
    private String id,name,mail,pass;
    private long createTime;

    public UserAccountModel() {
    }

    public UserAccountModel(String id, String name, String mail, String pass, long createTime) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
        this.createTime = createTime;
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
