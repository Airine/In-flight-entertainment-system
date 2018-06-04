package com.view;

import com.jfoenix.effects.JFXDepthManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;

import java.util.Random;


public class MovieSortItem extends Label {
     public MovieSortItem(String title){
         Random random=new Random(12);
         this.setText(title);
         this.setStyle("-fx-background-color: "+getDefaultColor((int)(Math.random()*12)%12));
        ImageView imageView= new ImageView("resources/moviepage.png");
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
         this.setGraphic(imageView);
         this.setPrefHeight(80);
         this.setPrefWidth(80);
//         this.setMinSize(80,60);
//         this.setMaxSize(80,60);
         JFXDepthManager.setDepth(this, 3);
     }
    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }

}
