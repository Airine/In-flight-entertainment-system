//package com.model;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//
//class WebScraping {
//    Map<String, String> scrapeLink(ArrayList<String> IDs) throws IOException {
//        Map<String, String> URL_Title = new HashMap<>();
//        String link_str, title;
//        // fetch the document over HTTP
//        for (String ID : IDs) {
//            Document doc = Jsoup.connect("https://www.ku6.com/video/detail?id=" + ID).timeout(0).get();
//            // get the page title
//            Elements videoItems = doc.getElementsByTag("script");
//            int startIndex, endIndex;
//            for (Element vi : videoItems) {
//                String html = vi.html();
//                startIndex = html.indexOf("flvURL");
//                if (startIndex!=-1) {
//                    startIndex += 9;
//                    String tmp = html.substring(startIndex);
//                    endIndex = tmp.indexOf("\"");
//                    link_str = tmp.substring(0,endIndex);
//
//                    startIndex = html.indexOf("document.title") + 18;
//                    tmp = html.substring(startIndex);
//                    endIndex = tmp.indexOf("\"");
//                    title = tmp.substring(0,endIndex);
//                    URL_Title.put(link_str,title);
//                }
//            }
//        }
//        return URL_Title;
//    }
//}
//
