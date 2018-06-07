package com.view.viewModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import com.model.Movie;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

import static com.MainApp.mainMovieTypes;
import static javafx.animation.Interpolator.EASE_BOTH;


public class MovieSortItem extends StackPane {

    private static final double width = 200;
    private static final double height = 112;

    private JFXButton button;

    public MovieSortItem(String language, String type){
        String url = "resources/movie_type/"+language+"/"+type+".png";
        this.setPrefWidth(width);
        this.setPrefHeight(height);

        JFXDepthManager.setDepth(this, 1);

        // create content
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
        button.setOnMouseReleased(event -> {button.setButtonType(JFXButton.ButtonType.RAISED);});
        this.button = button;

        content.getChildren().addAll(button);

        this.getChildren().addAll(content);
        this.setStyle("-fx-padding: 15px 0px 0px 15px;");
    }

    private void addMovie(Movie movie){

    }

    private void addMovies(List<Movie> movies){

    }

    public JFXButton getButton() {
        return button;
    }
}
