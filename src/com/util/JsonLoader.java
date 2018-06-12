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
import static com.util.DataUpdater.updateMovies;
import static com.util.SQLiteJDBC.connectToDB;
import static com.util.SQLiteJDBC.runSQLstatement;

public class JsonLoader {


    public static void main(String args[]){

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