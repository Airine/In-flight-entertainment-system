package com.util;

import com.model.User;

import java.util.ArrayList;

public class DataUpdater {

    public static void updateUsers(ArrayList<User> users){
        for (User user:users) {
            updateUser(user);
        }
    }

    public static void updateUser(User user){

    }

}
