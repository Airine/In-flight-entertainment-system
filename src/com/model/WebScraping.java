package com.model;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.deploy.net.URLEncoder;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


class WebScraping {
    
    
    public ArrayList<String> addAllMoives() throws IOException {
        //构造一个webClient 模拟Chrome 浏览器
        String url = "https://www.ku6.com/detail/72";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //屏蔽日志信息
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        //支持JavaScript
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage rootPage = webClient.getPage(url);
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(5000);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        ArrayList<String> urls = new ArrayList<>();
        Element container = document.getElementById("video-container");
        Elements links = container.select("a[href]");
        for (Element e: links) {
            urls.add("https://www.ku6.com"+e.attr("href"));
        }
        return urls;
    }
    
    
    public Map<String, String> scrapeMovieLinks() throws IOException {
        ArrayList<String> urls = addAllMoives();
        Map<String, String> URL_Title = new HashMap<>();
        String link_str, title;
        // fetch the document over HTTP
        for (String url : urls) {
            Document doc = Jsoup.connect(url).timeout(0).get();
            // get the page title
            Elements videoItems = doc.getElementsByTag("script");
            int startIndex, endIndex;
            for (Element vi : videoItems) {
                String html = vi.html();
                startIndex = html.indexOf("flvURL");
                if (startIndex!=-1) {
                    startIndex += 9;
                    String tmp = html.substring(startIndex);
                    endIndex = tmp.indexOf("\"");
                    link_str = tmp.substring(0,endIndex);
                    startIndex = html.indexOf("document.title") + 18;
                    tmp = html.substring(startIndex);
                    endIndex = tmp.indexOf("\"");
                    title = tmp.substring(0,endIndex);
                    int index= link_str.indexOf("com/")+ 4;
                    String encodeStr = link_str.substring(0,index)
                            + URLEncoder.encode(link_str.substring(index),"UTF-8").replace("+","%20");
                    
                    URL_Title.put(encodeStr,title);
                }
            }
        }
        return URL_Title;
    }

    public StringBuilder scrapeMessage(String title) throws IOException {
        StringBuilder sb = new StringBuilder();
        String url = "https://v.qq.com/x/search/?q="+ URLEncoder.encode(title,"UTF-8");
        Document doc = Jsoup.connect(url).timeout(3000).get();
        Elements infos = doc.select("div._infos");
        boolean flag = false;
        for (Element e: infos) {
            if(e.selectFirst("span.type").text().equals("电影")){
                if(e.selectFirst("a.desc_more")==null){
                    continue;       
                }
                url = e.selectFirst("a.desc_more").attr("href");
                flag = true;
            }
        }
        if (flag)
            doc = Jsoup.connect(url).get();
        sb.append("中文名: ").append(title).append('\n');
        Element details = doc.selectFirst("div.mod_figure_detail.mod_figure_detail_en.cf");
        if(details!=null) {
            String poster = details.selectFirst("img").attr("src");
            Element enElement = details.selectFirst("span.title_en");
            Element typeElement = details.selectFirst("div.video_type.cf");
            Element timeElement = details.selectFirst("div.video_type.video_type_even.cf");
            Element genresElement = details.selectFirst("div.video_tag.cf");
            String EnglishTitle = "";
            String type = "";
            String time = "";
            String genres="";
            if(enElement!=null)
                EnglishTitle = enElement.text();
            if (typeElement!=null)
                type = typeElement.text();
            if (timeElement!=null)
                time = timeElement.text();
            if(genresElement!=null)
                genres = genresElement.text();
            String description = details.selectFirst("div.video_desc").text();
            sb.append("英文名: ").append(EnglishTitle).append('\n').append("封面: ").append("http:").append(poster)
                    .append('\n').append(time).append('\n').append(type).append('\n')
                    .append(genres).append('\n').append(description).append('\n');
        }
        return sb;
    }

}

