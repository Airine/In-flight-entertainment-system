package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.model.Movie;
import com.model.MovieType;
import com.view.viewModel.MovieItem;
import com.view.viewModel.MovieSortItem;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static com.MainApp.mainMovieTypes;
import static com.MainApp.mainMovies;

public class HomePageController {


    @FXML
    private JFXScrollPane HomeScrollPane;

    @FXML
    private FlowPane flowpane;

    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    private void addMovieSortItem(){
        for (MovieType movieType : mainMovieTypes) {
            try {
                StackPane sort = movieType.getMovieSortItem_cn();
                movieType.handleClick(HomeScrollPane);
                flowpane.getChildren().add(sort);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void initialize(){
        addMovieSortItem();
//        for (Movie mainMovy : mainMovies) {
//            try {
//                StackPane movieItem = mainMovy.getMi_cn();
//                flowpane.getChildren().add(movieItem);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        HomeScrollPane.setContent(flowpane);
        HomeScrollPane.setStyle("-fx-border-color: transparent;");
        //封面图
        ImageView imageView=new ImageView("resources/moviepage2.png");
        imageView.setFitWidth(640);
        imageView.setFitHeight(192);

        HomeScrollPane.getMainHeader().getChildren().add(imageView);
        HomeScrollPane.getCondensedHeader().setStyle("-fx-background-color: #FFFFFF;");



    }



}




