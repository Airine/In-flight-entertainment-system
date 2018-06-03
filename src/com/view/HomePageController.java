package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.animation.Interpolator.EASE_BOTH;

public class HomePageController {

    @FXML
    private JFXScrollPane HomeScrollPane;

    @FXML
    private JFXMasonryPane HomeMasonryPane;


    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }


    @FXML
    private void initialize(){
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            MovieItem mv =new MovieItem(100,100);
            children.add(mv);
        }
        HomeMasonryPane.getChildren().addAll(children);
        HomeScrollPane.setContent(HomeMasonryPane);




    }



}




