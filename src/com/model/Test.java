package com.model;

import java.io.*;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {

        File movieMessages = new File("E:\\GitHub\\In-flight-entertainment-system\\src\\com\\model\\movieMessages.txt");
        PrintWriter out = new PrintWriter(movieMessages);
        com.model.WebScraping webScraping = new com.model.WebScraping();
        Map<String, String> URL_Title = webScraping.scrapeMovieLinks();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry entry : URL_Title.entrySet()) {
            sb.append("视频链接: ").append(entry.getKey()).append('\n');
            sb.append(webScraping.scrapeMessage((String) entry.getValue())).append("\n");
        }
        String result = sb.toString().replaceAll("\n",System.lineSeparator());
        out.print(result);
        out.close();
    }
}
