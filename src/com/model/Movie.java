package com.model;

import com.view.viewModel.MovieItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class Movie {
    private int movie_id;
    private String title_cn;
    private String title_en;
    private String year;
//    private int time;
    private String language;
    private int type;
    private String href;
    private String post_href;

    private static final String moviePosterPath = "resources/moviePoster/";
    private MovieItem mi_cn;
    private MovieItem mi_en;

    private FlowPane detailsPane_cn;
    private FlowPane detailsPane_en;

    public Movie(){

    }

    public Movie(int movie_id,String title_cn, String title_en,
                 String year, String language, int type, String href, String post_href){
        setMovie_id(movie_id);
        setTitle_cn(title_cn);
        setTitle_en(title_en);
        setYear(year);
//        setTime(time);
        setLanguage(language);
        setType(type);
        setHref(href);
        setPost_href(post_href);

    }

    public void initMI(){
        mi_cn = new MovieItem(post_href, title_cn, href);
        mi_en = new MovieItem(post_href, title_en, href);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public FlowPane getDetailsPane_cn() {
        return detailsPane_cn;
    }

    public void setDetailsPane_cn(FlowPane detailsPane_cn) {
        this.detailsPane_cn = detailsPane_cn;
    }

    public FlowPane getDetailsPane_en() {
        return detailsPane_en;
    }

    public void setDetailsPane_en(FlowPane detailsPane_en) {
        this.detailsPane_en = detailsPane_en;
    }

    public MovieItem getMi_cn() {
        return mi_cn;
    }

    public void setMi_cn(MovieItem mi_cn) {
        this.mi_cn = mi_cn;
    }

    public MovieItem getMi_en() {
        return mi_en;
    }

    public void setMi_en(MovieItem mi_en) {
        this.mi_en = mi_en;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle_cn() {
        return title_cn;
    }

    public void setTitle_cn(String title_cn) {
        this.title_cn = title_cn;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPost_href() {
        return post_href;
    }

    public void setPost_href(String post_href) {
        this.post_href = post_href;
    }

}
