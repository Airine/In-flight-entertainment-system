package com.util;

import java.io.*;

/**
 * <h>FileIO</h>
 * <p>This class is to load the json file and store the data into database</p>
 *
 * @author 田闰心
 */
public class FileIO {

    private static final String folderPath = "src/resources/json/";

    /**
     * This method is to execute the loading
     *
     * @param fileName the file name of the json file
     * @return
     */
    public static String getDataFromFile(String fileName) {

        String Path = folderPath + fileName + ".json";
        BufferedReader reader = null;
        StringBuilder laststr = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr.toString();
    }
}
