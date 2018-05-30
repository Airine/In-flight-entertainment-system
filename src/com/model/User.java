package com.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private int setting;

    public User(int id, String name, String password, int setting) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.setting = setting;
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

}
