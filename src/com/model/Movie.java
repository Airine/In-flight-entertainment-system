package com.model;

public class Movie {

    private String title_cn;
    private String title_en;
    private int year;
    private int time;
    private String language;

    public Movie(){

    }

    public Movie(String title_cn, String title_en, int year, int time, String language){
        setTitle_cn(title_cn);
        setTitle_en(title_en);
        setYear(year);
        setTime(time);
        setLanguage(language);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
