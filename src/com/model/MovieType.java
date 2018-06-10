package com.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.view.viewModel.MovieSortItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

import static com.util.DataLoader.getMoviesByType;

public class MovieType {

    private int type_id;
    private String type_cn;
    private String type_en;

    private MovieSortItem movieSortItem_cn;
    private MovieSortItem movieSortItem_en;
    private FlowPane flowPane = new FlowPane();
    private List<Movie> movies = new ArrayList<>();


    public MovieType(){

    }

    public MovieType(int type_id, String type_cn, String type_en,
                     String language){
        setType_id(type_id);
        setType_cn(type_cn);
        setType_en(type_en);
        initMovieSortItem();
        initMovies(language);
    }

    private void initMovies(String language) {
        movies = getMoviesByType(type_en);
        for (Movie movie:movies) {
            try{
//                StackPane movieItem = movie.getMi_cn();
                movie.initMI();
                StackPane movieItem = (language.equals("cn"))? movie.getMi_cn() : movie.getMi_en();
                flowPane.getChildren().add(movieItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initMovieSortItem() {
        setMovieSortItem_cn(new MovieSortItem("cn", type_en));
        setMovieSortItem_en(new MovieSortItem("en", type_en));
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_cn() {
        return type_cn;
    }

    public void setType_cn(String type_cn) {
        this.type_cn = type_cn;
    }

    public String getType_en() {
        return type_en;
    }

    public void setType_en(String type_en) {
        this.type_en = type_en;
    }

    public MovieSortItem getMovieSortItem_cn() {
        return movieSortItem_cn;
    }

    public void setMovieSortItem_cn(MovieSortItem movieSortItem_cn) {
        this.movieSortItem_cn = movieSortItem_cn;
    }

    public MovieSortItem getMovieSortItem_en() {
        return movieSortItem_en;
    }

    public void setMovieSortItem_en(MovieSortItem movieSortItem_en) {
        this.movieSortItem_en = movieSortItem_en;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void setFlowPane(FlowPane flowPane) {
        this.flowPane = flowPane;
    }

    // 这个地方没写好
    public void handleClick(JFXScrollPane homeScrollPane, JFXButton button) {
        movieSortItem_cn.getButton().setOnMouseClicked(event -> {
            button.setVisible(true);
            homeScrollPane.setContent(flowPane);
//            System.out.println(type_cn);
//            for (Movie movie:movies){
//                System.out.println(movie.getTitle_cn());
//            }

        });
        movieSortItem_en.getButton().setOnMouseClicked(event -> {
            homeScrollPane.setContent(flowPane);
            System.out.println(type_en);
        });
    }
}
