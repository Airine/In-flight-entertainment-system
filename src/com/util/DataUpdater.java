package com.util;

import com.MainApp;
import com.model.Movie;
import com.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO `movie`(`title_cn`,`title_en`,`year`,`language`,`type`,`href`,`post_href`)" +
                "values('"+
                movie.getTitle_cn() +"', '" +
                movie.getTitle_en() +"', '" +
                movie.getYear()     +"', '" +
                movie.getLanguage() +"', " +
                movie.getType()     +", '" +
                movie.getHref()     +"', '" +
                movie.getPost_href()+"'" +
                ");" +
                "commit;";
        runSQLstatement(connection, sql);
    }

    public static void writeBackCollection(){
        Connection connection = connectToDB();
        User user = MainApp.mainUser;
        if (user == null) return;
        List<Movie> starMovies = MainApp.starMovies;

        System.out.println("Delete Previous....");

        String deleteSql = "DELETE FROM `star_relations` where `user_id` = ";
        runSQLstatement(connection, deleteSql+user.getId()+";");

        System.out.println("Write Back....");

        String sql = "INSERT INTO `star_relations`(`user_id`,`movie_id`) VALUES";
        for (Movie m:starMovies) {
            try {
                runSQLstatement(connection,sql+" ("+user.getId()+","+m.getMovie_id()+");");
            } catch (Exception e){
                System.err.println(m+"is already in "+user+"'s collection.");
            }
        }
    }
}
