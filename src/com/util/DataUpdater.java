package com.util;

import com.model.Movie;
import com.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.util.SQLiteJDBC.connectToDB;
import static com.util.SQLiteJDBC.runSQLstatement;

public class DataUpdater {

    public static void updateUsers(ArrayList<User> users){
        for (User user:users) {
            updateUser(user);
        }
    }

    public static void updateUser(User user){

    }

    public static void updateMovies(ArrayList<Movie> movies){
        for (Movie movie:movies) {
            updateMovie(movie);
        }
    }

    public static void updateMovie(Movie movie){
        Connection connection = connectToDB();
        String sql = "insert into movie " +
                "(title_cn, title_en, " +
                "year, language, type, href, post_href) " +
                "values("+
                movie.getTitle_cn() +"," +
                movie.getTitle_en() +"," +
                movie.getYear()     +"," +
                movie.getLanguage() +"," +
                movie.getType()     +"," +
                movie.getHref()     +"," +
                movie.getPost_href()+"," +
                ");" +
                "commit;";
        runSQLstatement(connection, sql);
    }
}
