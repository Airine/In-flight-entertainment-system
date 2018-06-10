package com.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonLoader {


    public static void main(String args[]){
        System.out.println(getJsonValue("cn", "loginDialog", "login"));
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