package com.util;

import com.model.Movie;
import javafx.scene.media.MediaPlayer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.DataUpdater.updateMovie;
import static com.util.SQLiteJDBC.connectToDB;
import static com.util.SQLiteJDBC.runSQLstatement;

public class JsonLoader {

    private static Map<String,Integer> type = new HashMap<String, Integer>();

    public static void main(String args[]){
//        type.put("动作", 1);
//        type.put("喜剧", 2);
//        type.put("悬疑", 3);
//        type.put("剧情", 4);
//        type.put("科幻", 5);
//        type.put("恐怖", 6);
//        type.put("战争", 7);
//        type.put("犯罪", 8);
//        type.put("情色", 9);
//        type.put("家庭",10);
//        type.put("爱情",11);
//        type.put("传记",12);
//        List<Movie> newmovies = new ArrayList<>();
////        for (int i = 1; i < 40; i++) {
//            JSONObject tempt = getJsonValue("movieMessage/movieMessage", (new Integer(1)).toString());
////            System.out.println(tempt);
//            try {
//                assert tempt != null;
//                newmovies.add(new Movie(
//                        1,
//                        tempt.getString("title_cn"),
//                        tempt.getString("title_en"),
//                        tempt.getString("release_time"),
//                        tempt.getString("language"),
//                        type.get(tempt.getString("genres")),
//                        tempt.getString("href"),
//                        tempt.getString("post_url")
//                ));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////        }
//        updateMovie(newmovies.get(0));
        Connection connection = connectToDB();
        String sql = "INSERT INTO `movie`(`movie_id`,`title_cn`,`title_en`,`year`,`language`,`type`,`href`,`post_href`) VALUES (5, 'testing','testing','testing','testing',1,'testing','testing');" +
                "commit;";
        runSQLstatement(connection, sql);
        try {
            assert connection != null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static JSONTokener loadData(String fileName){
        String data = FileIO.getDatafromFile(fileName);
//        System.out.println(data);
        return new JSONTokener(data);
    }

    public static JSONObject getJsonValue(String fileName, String block){
        try{
            JSONObject jsonObject = (JSONObject) loadData(fileName).nextValue();
            return (JSONObject) jsonObject.get(block);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonValue(String fileName, String block, String item){
        try{
            return getJsonValue(fileName, block).getString(item);
        }catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}