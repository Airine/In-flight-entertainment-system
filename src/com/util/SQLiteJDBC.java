package com.util;

import java.sql.*;

public class SQLiteJDBC {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:src/resources/sqlite-database/sqliteDB.db";

    public static void main(String args[]) {
        Connection connection = connectToDB();


        try {
            String sql = "select * from user;";

            ResultSet rs = runSQLquery(connection,sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()){
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
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

    public static Connection connectToDB(){
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connect database successfully.");
            return connection;
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database not found.");
        return null;
    }

    public static void runSQLstatement(Connection connection, String sql) {
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static ResultSet runSQLquery(Connection connection, String sql) {
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
