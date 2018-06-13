package com.util;

import com.MainApp;
import com.model.Movie;
import com.model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.util.DataLoader.getUser;
import static com.util.DataLoader.loadUsers;
import static com.util.SQLiteJDBC.connectToDB;
import static com.util.SQLiteJDBC.runSQLstatement;

/**
 * <h>DataUpdater</h>
 * <p>This class is to update all the information in database</p>
 */
public class DataUpdater {

    public static void updateUsers(ArrayList<User> users) {
        for (User user : users) {
            updateUser(user);
        }
    }

    public static void updateUser(User user) {
        Connection connection = connectToDB();
        int VIP = (MainApp.huiyuan) ? 1 : 0;
        String sql = "UPDATE `user`" +
                "SET `nick_name` = '" + user.getNickName() + "'," +
                "`status` = '" + user.getStatus() + "'," +
                "`if_VIP` = '" + VIP + "'," +
                "`icon_url` = '" + user.getIconUrl() + "'" +
                "WHERE `user_id` = '" + user.getId() + "';" +
                "commit;";
        runSQLstatement(connection, sql);
    }

    /**
     * This method is to update the movies
     *
     * @param movieArrayList the list of all the movies
     */
    static void updateMovies(ArrayList<Movie> movieArrayList) {
        for (Movie movie : movieArrayList) {
            updateMovie(movie);
        }
    }

    /**
     * This method is to update the information of movies
     *
     * @param movie The movie which will be changed
     */
    private static void updateMovie(Movie movie) {
        Connection connection = connectToDB();
        String sql = "INSERT INTO `movie`(`title_cn`,`title_en`,`year`,`language`,`type`,`href`,`post_href`)" +
                "values('" +
                movie.getTitle_cn() + "', '" +
                movie.getTitle_en() + "', '" +
                movie.getYear() + "', '" +
                movie.getLanguage() + "', " +
                movie.getType() + ", '" +
                movie.getHref() + "', '" +
                movie.getPost_href() + "'" +
                ");" +
                "commit;";
        try {
            runSQLstatement(connection, sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Movie update failed.");
        }

    }

    /**
     * The method is to return the favourite movies which has been chosen to the database.
     */
    public static void writeBackCollection() {
        Connection connection = connectToDB();
        User user = MainApp.mainUser;
        if (user == null) return;
        List<Movie> starMovies = MainApp.starMovies;

        System.out.println("Delete Previous....");

        String deleteSql = "DELETE FROM `star_relations` where `user_id` = ";
        runSQLstatement(connection, deleteSql + user.getId() + ";");

        System.out.println("Write Back....");

        String sql = "INSERT INTO `star_relations`(`user_id`,`movie_id`) VALUES";
        for (Movie m : starMovies) {
            try {
                runSQLstatement(connection, sql + " (" + user.getId() + "," + m.getMovie_id() + ");");
            } catch (Exception e) {
                System.err.println(m + "is already in " + user + "'s collection.");
            }
        }
    }

    /**
     * This method is to delete the movies in database
     *
     * @param movie Movie object
     */
    public static void deleteMovie(Movie movie) {
        Connection connection = connectToDB();
        System.out.println("Delete Collection relation to the movie...");
        String deleteCollection = "DELETE FROM `star_relations` where `movie_id` = ";
        try {
            runSQLstatement(connection, deleteCollection + movie.getMovie_id() + ";");
            System.out.println("DELETE Collection relation successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Delete Movie from database...");
        String deleteMovie = "DELETE FROM `movie` where `movie_id` = ";
        try {
            runSQLstatement(connection, deleteMovie + movie.getMovie_id() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url The url of the local movie file with .mp4 extension
     * @author 田闰心
     * This method is to insert movies using regular expression to judge the string
     */
    public static void insertLocalMovie(String url) {
        String title = "";
        String href = "";
//        String url = "file:/Users/aaron/Documents/GitHub/In-flight-entertainment-system/src/resources/sakai/A-Funny-Thing-Happened-Official-Trailer-1-Michael-Crawford-M.mp4";
        String pattern = "(\\S*\\/)(\\S*)(\\.mp4)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find()) {
            href = m.group(0).substring(5, m.group(0).length());
            title = m.group(2);
        } else {
            System.out.println("No match!");
        }
        Movie movie = new Movie(1, "本地电影\n" + title, title, "2018-6-13", "英语", 13, href, "resources/moviePoster/default.png");
        MainApp.mainMovies.add(movie);
        System.err.println(movie);
//        DataLoader.movies.add(movie);
        try {
            updateMovie(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static User insertUser(String user_nameText, String user_pwText) {
        Connection connection = connectToDB();
        String sql = "INSERT INTO `user`(`user_name`,`user_password`)" +
                "values('" +
                user_nameText + "', '" +
                user_pwText + "'" +
                ");" +
                "commit;";
        runSQLstatement(connection, sql);
        loadUsers();
        return getUser(user_nameText);
    }


}
