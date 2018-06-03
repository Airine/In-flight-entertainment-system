package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

import static javafx.animation.Interpolator.EASE_BOTH;

public class MovieItem extends StackPane {

    public MovieItem(double width, double height){
        Random rd =new Random(12);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        JFXDepthManager.setDepth(this, 1);

        //create content
//        StackPane header = new StackPane();
//        //上面那一块的颜色
//        String headerColor = getDefaultColor((int) ((Math.random() * 12) % 12));
//        header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
//        VBox.setVgrow(header, Priority.ALWAYS);
        ImageView imageView=new ImageView("resources/movieitem1.png");
        imageView.setFitWidth(190);
        imageView.setFitHeight(224);
        StackPane body = new StackPane();
        body.setMinHeight(73);
        VBox content = new VBox();
        content.getChildren().addAll(imageView, body);
        body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");

        // create button
        JFXButton button = new JFXButton("");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        button.setPrefSize(40, 40);
//        button.setRipplerFill(Color.valueOf(headerColor));
        button.setScaleX(0);
        button.setScaleY(0);

        SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                "M402.746 877.254l-320-320c-24.994-24.992-24.994-65.516 0-90.51l320-320c24.994-24.992 65.516-24.992 90.51 0 24.994 24.994 "
                        + "24.994 65.516 0 90.51l-210.746 210.746h613.49c35.346 0 64 28.654 64 64s-28.654 64-64 64h-613.49l210.746 210.746c12.496 "
                        + "12.496 18.744 28.876 18.744 45.254s-6.248 32.758-18.744 45.254c-24.994 24.994-65.516 24.994-90.51 0z",
                Color.WHITE);
        glyph.setSize(20, 20);
        button.setGraphic(glyph);
        button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
            return imageView.getBoundsInParent().getHeight() - button.getHeight() / 2;
        }, imageView.boundsInParentProperty(), button.heightProperty()));
        StackPane.setMargin(button, new Insets(0, 12, 0, 0));
        StackPane.setAlignment(button, Pos.TOP_RIGHT);


        Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                new KeyValue(button.scaleXProperty(),
                        1,
                        EASE_BOTH),
                new KeyValue(button.scaleYProperty(),
                        1,
                        EASE_BOTH)));
        animation.setDelay(Duration.millis(5000));
        animation.play();
        this.getChildren().addAll(content, button);

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
