package com.util;

import java.sql.*;

/**
 * <h>SQLiteJDBC</h>
 * <p>JDBC for SQlite</p>
 *
 * @author 田闰心
 */
public class SQLiteJDBC {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:src/resources/sqlite-database/sqliteDB.db";

    /**
     * The main method
     *
     * @param args arguments
     */
    public static void main(String args[]) {
        Connection connection = connectToDB();
        try {
            String sql = "select * from user;";

            ResultSet rs = runSQLquery(connection, sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print("\n");
            }

            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to connect to database
     *
     * @return the connection object
     */
    public static Connection connectToDB() {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
//            System.out.println("Connect database successfully.");
            return connection;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database not found.");
        return null;
    }

    /**
     * Run the sql statement without the result
     *
     * @param connection
     * @param sql
     */
    public static void runSQLstatement(Connection connection, String sql) {
        Statement statement;
        try {
            Class.forName(JDBC_DRIVER);
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * This method is to run the query with feedback
     *
     * @param connection the connection to database
     * @param sql        the string of the sql statement
     * @return The result of sql query
     */
    public static ResultSet runSQLquery(Connection connection, String sql) {
        Statement statement;
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
