package com.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private int setting;

    public User(){

    }

    public User(int id, String name, String password, int setting) {
        setId(id);
        setName(name);
        setPassword(password);
        setSetting(setting);
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
