package com.util;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) throws IOException {
        
        PrintWriter out = new PrintWriter("src/resources/json/movieMessage/movieMessage2.json");
        WebScraping webScraping = new WebScraping();
        Map<String, String> URL_Title = webScraping.scrapeMovieLinks();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        Set<Map.Entry<String, String>> set = URL_Title.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        int setSize = set.size();
        for(int i = 1; i<setSize; i++) {
            sb.append("\"").append(i).append("\":");
            webScraping.scrapeMessage(iterator.next(),sb);
            sb.append(",\n");
        }
        Map.Entry entry = iterator.next();
        sb.append("\"").append(setSize).append("\":");
        webScraping.scrapeMessage(entry,sb);
        sb.append("\n}\n");
        out.print(sb);
        out.close();
    }
}
