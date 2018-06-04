package com.util;

import java.io.*;

public class FileIO {

    private static final String folderPath = "src/resources/json/";
    public static void saveDataToFile(String fileName,String data) {
        BufferedWriter writer = null;
        File file = new File(""+ fileName + ".json");
        Boolean flag;
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.getPath()+"创建成功！");
        System.out.println("文件写入成功！");
    }

    public static String getDatafromFile(String fileName) {

        String Path= folderPath + fileName+ ".json";
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
