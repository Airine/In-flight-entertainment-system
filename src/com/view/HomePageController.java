package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.view.viewModel.MovieItem;
import com.view.viewModel.MovieSortItem;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class HomePageController {

    @FXML
    private JFXScrollPane HomeScrollPane;

    @FXML
    private JFXMasonryPane HomeMasonryPane;

    @FXML
    private FlowPane flowpane;

    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }


    @FXML
    private void initialize(){
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i <20; i++){
           try {

               StackPane sort = new MovieSortItem(200, 112,"cn","Erotic");
               flowpane.getChildren().add(sort);
               children.add(sort);

           }catch (Exception e){
               e.printStackTrace();
           }
        }


        flowpane.setHgap(10);
        flowpane.setVgap(20);
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




