package com.util;

import com.model.*;
import static com.util.SQLiteJDBC.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataLoader {

    private static List<User> users = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static List<MovieType> movieTypes = new ArrayList<>();
    private static final String[] types = {
            "Other",
            "Action",
            "Comedy",
            "Mystery",
            "Cartoon",
            "Sci-Fi",
            "Horror",
            "War",
            "Crime",
            "Erotic",
            "Disaster",
            "Romance",
            "Biography"
    };


    public static void loadUsers(){
        Connection connection = connectToDB();

        List<User> tempt = new ArrayList<>();
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
                tempt.add(u);
            }

            if (connection!=null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setUsers(tempt);
    }

    public static List<Movie> loadMovies(){
        Connection connection = connectToDB();

        List<Movie> tempt = new ArrayList<>();
        try {
            String sql = "select * from movie;";

            ResultSet rs = runSQLquery(connection,sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()){
                int id = rs.getInt("movie_id");
                String title_cn = rs.getString("title_cn");
                String title_en = rs.getString("title_en");
                int year = rs.getInt("year");
                int time = rs.getInt("time");
                String language = rs.getString("language");
                // 输出数据
                Movie m = new Movie(id, title_cn, title_en, year, time, language);
                tempt.add(m);
            }

            if (connection!=null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setMovies(tempt);
        return tempt;
    }

    public static List<MovieType> loadMovieTypes(String language){
        Connection connection = connectToDB();

        List<MovieType> tempt = new ArrayList<>();
        try {
            String sql = "select * from movie_type;";

            ResultSet rs = runSQLquery(connection,sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()){
                int id = rs.getInt("type_id");
                String type_cn = rs.getString("type_cn");
                String type_en = rs.getString("type_en");

                // 输出数据
                MovieType m = new MovieType(id, type_cn, type_en, language);
                tempt.add(m);
            }

            if (connection!=null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setMovieTypes(tempt);
        return tempt;
    }

    public static User getUser(String user_name){
        List<User> tempt = users.stream()
                            .filter(user -> Objects.equals(user.getName(), user_name))
                            .collect(Collectors.toList());
        if (tempt.size()!=1) return null;
        else return tempt.get(0);
    }

    public static List<Movie> getMoviesByType(String movie_type){
        return movies.stream()
                .filter(movie -> types[movie.getType()].equals(movie_type))
                .collect(Collectors.toList());
    }

    private static void setUsers(List<User> users) {
        DataLoader.users = users;
    }
    private static void setMovies(List<Movie> movies) {
        DataLoader.movies = movies;
    }
    private static void setMovieTypes(List<MovieType> movieTypes){
        DataLoader.movieTypes = movieTypes;
    }

}
