package com.util;

import java.io.*;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {

        File movieMessages = new File("");
        PrintWriter out = new PrintWriter(movieMessages);
        WebScraping webScraping = new WebScraping();
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
