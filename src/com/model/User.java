package com.model;

import java.util.Objects;

/**
 * <h>
 * User
 * </h>
 * <p>the objects of the users</p>
 */
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

    public User() {

    }

    /**
     * <h>
     * Constructor
     * </h>
     *
     * @param id        the unique id of the user
     * @param name      the account name of the user
     * @param nickName  the nick name of the user
     * @param password  the password
     * @param setting   the setting id in the database
     * @param ifVIP     whether it is VIP
     * @param ifAdmin   whether it is the administrator
     * @param signature the personal signature below the icon
     * @param iconUrl   the url of the user icon
     * @author 田闰心
     */
    public User(int id, String name, String nickName,
                String password, int setting,
                int ifVIP, int ifAdmin, String signature,
                String iconUrl) {
        setId(id);
        setName(name);
        setPassword(password);
        setSetting(setting);
        setNickName(nickName);
        setIfVIP(ifVIP);
        setIfAdmin(ifAdmin);
        setStatus(signature);
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


    public boolean verifyPassword(String password) {
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
