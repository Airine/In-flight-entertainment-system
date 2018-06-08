package com.util;

import java.io.*;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws IOException {

        File movieMessages = new File("E:");
        PrintWriter out = new PrintWriter(movieMessages);
        WebScraping webScraping = new WebScraping();
        Map<String, String> URL_Title = webScraping.scrapeMovieLinks();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry entry : URL_Title.entrySet()) {
            webScraping.scrapeMessage(entry,sb);
        }
        out.print(sb);
        out.close();
    }
}
