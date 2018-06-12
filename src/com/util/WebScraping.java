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

    /**
     * @author 黄珂邈
     * <h>
     *     Web scraping data from website with Javascript dynamically loading
     * </h>
     * <p>
     *     The method is to get the urls which contain the playable movie urls from ku6.
     *     In order to get the data after Javascript, 
     *     we have to simulate a new chrome browser.
     * </p>
     * @return
     * The array list of the player page urls from
     * @see <a href="https://www.ku6.com/detail/72">
     *     https://www.ku6.com/detail/72
     *     </a>
     * @throws IOException
     */
    public ArrayList<String> addAllMoives() throws IOException {
        //construct a web client
        String url = "https://www.ku6.com/detail/72";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //ignore the log
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        //Support JavaScript
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage rootPage = webClient.getPage(url);
        //set the runtime for JS
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

    /**
     * @author 黄珂邈
     * <h>
     *     Movie Scraping
     * </h>
     * <p>
     *     This method is to get the movie urls end with .mp4 or .mpg
     *     They can be directly load by media in javafx.
     * </p>
     * @return Map with urls as keys and titles as values
     * @throws IOException
     */
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

    /**
     * @author 黄珂邈
     * <h>
     *     Movie Message Scraping
     * </h>
     * <p>
     *     This method is to get the movies information on 
     *     <a href = "https://v.qq.com">https://v.qq.com</a>
     *     by using the Chinese titles from ku6.
     *     (There is no English title on ku6)
     *     The results will be stored in StringBuilder for json file.
     * </p>
     * 
     * @param entry The entry which contains urls and titles. 
     * @param sb The StringBuilder which will append information.
     * @throws IOException
     */
    public void scrapeMessage(Map.Entry entry,StringBuilder sb) throws IOException {
        String title = (String) entry.getValue();
        String url = "https://v.qq.com/x/search/?q="+ URLEncoder.encode(title+"电影","UTF-8");
        Document doc = Jsoup.connect(url).timeout(10000).get();
        Elements infos = doc.select("div._infos");
        
        /* Below we have to ensure whether there is a null pointer for many times
            since there are information may not shown on the websites.
         */
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
                            genres = "恐怖"; // here we change one type name
                            break A;
                        default:  
                            break;
                    }
                }
            }
            if(descElement!=null)
                description = descElement.text();

        }
        /*
            Here we are creating text with json format.
         */
        String comma = "\",";
        sb.append("{\"title_cn\": \"").append(title).append(comma);
        sb.append("\"title_en\": \"").append(EnglishTitle).append(comma);
        sb.append("\"region\": \"").append(region).append(comma);
        sb.append("\"release_time\": \"").append(release).append(comma);
        sb.append("\"language\": \"").append(language).append(comma);
        sb.append("\"genres\": \"").append(genres).append(comma);
        sb.append("\"description\": \"").append(description).append(comma);
        sb.append("\"href\": \"").append((String)entry.getKey()).append(comma);
        sb.append("\"post_url\": \"").append("http:").append(poster).append("\"}");
    }
}