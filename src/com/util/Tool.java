package com.util;

import com.model.User;
import static com.util.SQLiteJDBC.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Tool {

    public static List<User> users;

    private static void loadUsers(){
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

                users.add(new User(id,name,pw,setting));

                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Pw: " + pw);
                System.out.print(", Setting: " + setting);
                System.out.print("\n");
            }

            if (connection!=null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User login(String user_name, String pw){
//        User tempt = users.stream().findFirst(user -> )
        return null;
    }
}
