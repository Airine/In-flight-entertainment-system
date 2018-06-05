package com.view.viewModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

import static javafx.animation.Interpolator.EASE_BOTH;


public class MovieSortItem extends StackPane {


    public MovieSortItem(double width, double height, String language, String type){
        String url = "resources/movie_type/"+language+"/"+type+".png";
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setMinWidth(width);
        this.setMinHeight(height);
        JFXDepthManager.setDepth(this, 1);

        // create content
//        StackPane header = new StackPane();
        JFXButton button = new JFXButton("");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setPrefSize(2*width, 2*height);
        button.setStyle(
                "-fx-background-radius: 5;" +
                        "-fx-background-image: url(\"" + url + "\");" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-size: 100% 100%;"
        );

        VBox.setVgrow(button, Priority.ALWAYS);
        VBox content = new VBox();

        button.setOnMousePressed(event -> {button.setButtonType(JFXButton.ButtonType.FLAT);});
        button.setOnMouseExited(event -> {button.setButtonType(JFXButton.ButtonType.RAISED);});
        button.setOnMouseClicked(event -> {});

        content.getChildren().addAll(button);

        this.getChildren().addAll(content);
        this.setStyle("-fx-padding: 20px 0px 0px 20px;");

    }
}
