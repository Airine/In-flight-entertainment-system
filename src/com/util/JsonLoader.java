package com.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;

/**
 *
 */
public class JsonLoader {

    private static JSONTokener loadData(String fileName) {
        String data = FileIO.getDataFromFile(fileName);
//        System.out.println(data);
        return new JSONTokener(data);
    }

    public static JSONObject getJsonValue(String fileName, String block) {
        try {
            JSONObject jsonObject = (JSONObject) loadData(fileName).nextValue();
            return (JSONObject) jsonObject.get(block);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonValue(String fileName, String block, String item) {
        try {
            return Objects.requireNonNull(getJsonValue(fileName, block)).getString(item);
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}