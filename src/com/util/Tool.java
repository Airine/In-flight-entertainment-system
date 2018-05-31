package com.util;

import com.model.*;
import static com.util.SQLiteJDBC.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tool {

    private static List<User> users = new LinkedList<>();

    public static void loadUsers(){
        Connection connection = connectToDB();


        try {
            String sql = "select * from user;";

            ResultSet rs = runSQLquery(connection,sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()){
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String pw = rs.getString("user_password");
                int setting = rs.getInt("user_setting");
                // 输出数据

                User u = new User(id,name,pw,setting);
                users.add(u);
//
//                System.out.print("ID: " + id);
//                System.out.print(", Name: " + name);
//                System.out.print(", Pw: " + pw);
//                System.out.print(", Setting: " + setting);
//                System.out.print("\n");
            }

            if (connection!=null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String user_name){
        List<User> tempt = users.stream()
                            .filter(user -> Objects.equals(user.getName(), user_name))
                            .collect(Collectors.toList());
        if (tempt.size()!=1) return null;
        else return tempt.get(0);
    }

    public static void setUsers(List<User> users) {
        Tool.users = users;
    }
}
