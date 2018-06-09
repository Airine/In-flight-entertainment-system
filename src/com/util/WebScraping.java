package com.util;

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

    public void scrapeMessage(Map.Entry entry,StringBuilder sb) throws IOException {
        String title = (String) entry.getValue();
        String url = "https://v.qq.com/x/search/?q="+ URLEncoder.encode(title+"电影","UTF-8");
        Document doc = Jsoup.connect(url).timeout(10000).get();
        Elements infos = doc.select("div._infos");
        boolean flag = false;
        for (Element e : infos) {
            if (e.selectFirst("span.type").text().equals("电影")) {
                if (e.selectFirst("a.desc_more") == null) {
                    continue;
                }
                url = e.selectFirst("a.desc_more").attr("href");
                flag = true;
                break;
            }
        }
        if (flag)
            doc = Jsoup.connect(url).timeout(10000).get();
        Element details = doc.selectFirst("div.mod_figure_detail.mod_figure_detail_en.cf");

        String EnglishTitle = "";
        String poster = "";
        String region = "";
        String language="";
        String release= "";
        String genres="情色";
        String description= "";
        if(details!=null) {
            Element posterElement = details.selectFirst("img");
            Element enElement = details.selectFirst("span.title_en");
            Elements typeElements = details.select("div.type_item");
            Elements genreElements = details.select("a.tag");
            Element descElement = details.selectFirst("div.txt._desc_txt_lineHight");
            if(posterElement!=null)
                poster = posterElement.attr("src");
            if(enElement!=null)
                EnglishTitle = enElement.text();
            if (typeElements!=null) {
                String temp;
                for(Element e: typeElements){
                    temp = e.selectFirst("span.type_tit").text();
                    switch (temp){
                        case "地　区:":
                            region = e.selectFirst("span.type_txt").text();
                            break;
                        case "语　言:":
                            language = e.selectFirst("span.type_txt").text();
                            break;
                        case "上映时间:":
                            release = e.selectFirst("span.type_txt").text();
                        default:
                            break;
                    }
                }
            }
            if(genreElements!=null) {
                A:for (Element e:genreElements) {
                    switch (e.text()){
                        case "动作":
                        case "科幻":
                        case "爱情":
                        case "悬疑":    
                        case "犯罪":
                        case "战争":    
                        case "传记":
                        case "家庭":
                        case "喜剧":
                        case "剧情":
                            genres = e.text();
                            break A;
                        case "惊悚":
                            genres = "恐怖";
                            break A;
                        default:  
                            break;
                    }
                }
            }
            if(descElement!=null)
                description = descElement.text();

        }
        String comma = "\",";
        sb.append("{\"title_cn\": \"").append(title).append(comma);
        sb.append("\"title_en\": \"").append(EnglishTitle).append(comma);
        sb.append("\"region\": \"").append(region).append(comma);
        sb.append("\"release_time\": \"").append(release).append(comma);
        sb.append("\"language\": \"").append(language).append(comma);
        sb.append("\"genres\": \"").append(genres).append(comma);
        sb.append("\"description\": \"").append(description).append(comma);
        sb.append("\"href\": \"").append((String)entry.getKey()).append(comma);
        sb.append("\"post_url\": \"").append("http:").append(poster).append("\"},\n");
    }
}