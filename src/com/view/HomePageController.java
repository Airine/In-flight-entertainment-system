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
import javafx.scene.image.ImageView;
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

        for (int i = 0; i < 5; i++){
//           try {
//               FXMLLoader loader = new FXMLLoader();
//               loader.setLocation(MainApp.class
//                       .getResource("view/MovieItem1.fxml"));
//               AnchorPane mv=loader.load();
               MovieItem mv=new MovieItem(148,281);
               children.add(mv);
//           }catch (IOException e){
//               e.printStackTrace();
//           }
        }
      HomeMasonryPane.getChildren().addAll(children);
        HomeMasonryPane.setHSpacing(40);
        HomeMasonryPane.setVSpacing(15);
        HomeMasonryPane.setStyle("-fx-background-color:#EED5D2;");
        HomeScrollPane.setContent(HomeMasonryPane);
        //封面图
        ImageView imageView=new ImageView("resources/moviepage.png");
       imageView.setFitWidth(640);
       imageView.setFitHeight(192);
//       Label label=new Label("Enjoy you movies");
//       label.setStyle("-fx-font-size:  20px;    -fx-text-fill: white;");
//       HomeScrollPane.getBottomBar().getChildren().add(label);
        HomeScrollPane.getMainHeader().getChildren().add(imageView);
       HomeScrollPane.getCondensedHeader().setStyle("-fx-background-color:#EED5D2;");



    }



}




