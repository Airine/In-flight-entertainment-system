package com.util;

import java.io.*;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {
        
        PrintWriter out = new PrintWriter("src/resources/movieMessage.json");
        WebScraping webScraping = new WebScraping();
        Map<String, String> URL_Title = webScraping.scrapeMovieLinks();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for(Map.Entry entry : URL_Title.entrySet()) {
            webScraping.scrapeMessage(entry,sb);
        }
        sb.append("}\n");
        out.print(sb);
        out.close();
    }
}
