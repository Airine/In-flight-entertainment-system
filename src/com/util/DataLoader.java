package com.util;

import com.MainApp;
import com.model.*;

import static com.util.SQLiteJDBC.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h>DataLoader</h>
 */
public class DataLoader {

    private static List<User> users = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static List<MovieType> movieTypes = new ArrayList<>();
    private static final String[] types = {
            "Other",
            "Action",
            "Comedy",
            "Mystery",
            "Plot",
            "Sci-Fi",
            "Horror",
            "War",
            "Crime",
            "Erotic",
            "Family",
            "Romance",
            "Biography",
            "Local"
    };

    public static void loadUsers() {
        Connection connection = connectToDB();

        List<User> tempt = new ArrayList<>();
        try {
            String sql = "select * from user;";

            ResultSet rs = runSQLquery(connection, sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String pw = rs.getString("user_password");
                int setting = rs.getInt("user_setting");
                String nickName = rs.getString("nick_name");
                int ifVIP = rs.getInt("if_VIP");
                int ifAdmin = rs.getInt("if_Admin");
                String status = rs.getString("status");
                String icon_url = rs.getString("icon_url");
                // 输出数据
                User u = new User(id, name, nickName, pw, setting, ifVIP, ifAdmin, status, icon_url);
                tempt.add(u);
            }

            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setUsers(tempt);
        System.out.println("Load users successfully.");
    }

    public static List<Movie> loadMovies() {
        Connection connection = connectToDB();

        List<Movie> tempt = new ArrayList<>();
        try {
            String sql = "select * from movie;";

            ResultSet rs = runSQLquery(connection, sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()) {
                int id = rs.getInt("movie_id");
                String title_cn = rs.getString("title_cn");
                String title_en = rs.getString("title_en");
                String year = rs.getString("year");
                String language = rs.getString("language");
                int type = rs.getInt("type");
                String href = rs.getString("href");
                String post_href = rs.getString("post_href");
                // 输出数据
                Movie m = new Movie(id, title_cn, title_en, year, language, type, href, post_href);
                tempt.add(m);
            }

            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setMovies(tempt);
        System.out.println("Load movies successfully.");
        return tempt;
    }

    public static List<MovieType> loadMovieTypes(String language) {
        Connection connection = connectToDB();

        List<MovieType> tempt = new ArrayList<>();
        try {
            String sql = "select * from movie_type;";

            ResultSet rs = runSQLquery(connection, sql);
            assert rs != null : "Result set is null!!!";
            while (rs.next()) {
                int id = rs.getInt("type_id");
                String type_cn = rs.getString("type_cn");
                String type_en = rs.getString("type_en");

                // 输出数据
                MovieType m = new MovieType(id, type_cn, type_en, language);
                tempt.add(m);
            }

            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setMovieTypes(tempt);
        System.out.println("Load movie types successfully.");
        return tempt;
    }

    public static void loadStarRelation() {
        Connection connection = connectToDB();

        User user = MainApp.mainUser;
        List<Movie> starMovies = MainApp.starMovies;

        try {
            String sql = "select * from star_relations;";

            ResultSet rs = runSQLquery(connection, sql);
            assert rs != null : "Result set is null";
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int movie_id = rs.getInt("movie_id");
                if (user.getId() == user_id) {
                    Movie tempt = getMovie(movie_id);
                    if (tempt != null) {
                        tempt.setStar(true);
                        starMovies.add(tempt);
                    }
                }
            }

            if (connection != null) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Load user collection successfully.");
        for (Movie m : starMovies) {
            System.out.println(m);
        }
    }

    public static User getUser(String user_name) {
        List<User> tempt = users.stream()
                .filter(user -> Objects.equals(user.getName(), user_name))
                .collect(Collectors.toList());
        if (tempt.size() != 1) return null;
        else return tempt.get(0);
    }

    public static Movie getMovie(int id) {
        List<Movie> tempt = MainApp.mainMovies.stream()
                .filter(movie -> movie.getMovie_id() == id)
                .collect(Collectors.toList());
        if (tempt.size() != 1) return null;
        else return tempt.get(0);
    }

    public static List<Movie> getMoviesByType(String movie_type) {
        return MainApp.mainMovies.stream()
                .filter(movie -> types[movie.getType()].equals(movie_type))
                .collect(Collectors.toList());
    }

    public static List<Movie> getMoviesByTitle(String movie_title) {
        return MainApp.mainMovies.stream()
                .filter(movie -> movie.getTitle_cn().equals(movie_title))
                .collect(Collectors.toList());
    }

    private static void setUsers(List<User> users) {
        DataLoader.users = users;
    }

    private static void setMovies(List<Movie> movies) {
        DataLoader.movies = movies;
    }

    private static void setMovieTypes(List<MovieType> movieTypes) {
        DataLoader.movieTypes = movieTypes;
    }


}
