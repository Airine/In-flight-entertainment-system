package com.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String nickName;
    private String password;
    private int setting;
    private int ifVIP;
    private int ifAdmin;
    private String status;
    private String iconUrl;


    public User(){

    }

    public User(int id, String name, String nickName,
                String password, int setting,
                int ifVIP, int ifAdmin, String status,
                String iconUrl) {
        setId(id);
        setName(name);
        setPassword(password);
        setSetting(setting);
        setNickName(nickName);
        setIfVIP(ifVIP);
        setIfAdmin(ifAdmin);
        setStatus(status);
        setIconUrl(iconUrl);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getIfVIP() {
        return ifVIP;
    }

    public void setIfVIP(int ifVIP) {
        this.ifVIP = ifVIP;
    }

    public int getIfAdmin() {
        return ifAdmin;
    }

    public void setIfAdmin(int ifAdmin) {
        this.ifAdmin = ifAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    public boolean verifyPassword(String password){
        return (Objects.equals(this.password, password));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSetting() {
        return setting;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setSetting(int setting) {
        this.setting = setting;
    }
}
