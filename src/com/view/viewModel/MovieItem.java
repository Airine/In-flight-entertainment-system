package com.view.viewModel;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static javafx.animation.Interpolator.EASE_BOTH;

public class MovieItem extends StackPane {

    public MovieItem(String postUrl, String title, String videoUrl){
        this.setPrefWidth(200);
        this.setPrefHeight(350);
        JFXDepthManager.setDepth(this, 1);

        // create content
        StackPane header = new StackPane();
        String headerColor = getDefaultColor(13);
        header.setPrefHeight(290);
        header.setStyle("-fx-background-radius: 5 5 0 0; " +
//                "-fx-background-color: " + headerColor +
                "-fx-background-image: url(\"" + postUrl + "\");" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;" +
                "-fx-moz-background-size: 100% 100%;");
        VBox.setVgrow(header, Priority.ALWAYS);

        StackPane body = new StackPane();
        body.setMinHeight(60);
        body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");
        Label label = new Label(title);
        body.getChildren().addAll(label);

        VBox content = new VBox();
        content.getChildren().addAll(header, body);

        // create button1
        JFXButton button = new JFXButton("");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        button.setPrefSize(40, 40);
        button.setRipplerFill(Color.valueOf(headerColor));
        button.setScaleX(0);
        button.setScaleY(0);
        //create button2
        JFXButton addCollection =new JFXButton("1");
        addCollection.setButtonType(JFXButton.ButtonType.RAISED);
        addCollection.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        addCollection.setPrefSize(40, 40);
        addCollection.setRipplerFill(Color.valueOf(headerColor));
        addCollection.setScaleX(0);
        addCollection.setScaleY(0);
        addCollection.setOnMouseClicked(event -> {
            try {
                MainApp.player.mediaPlayer.stop();
                MainApp.player.getRootLayoutController().seePlaypage();
                MainApp.player.getSpinner().setVisible(true);
                MainApp.player.mediaPlayer = new MediaPlayer(new Media(videoUrl));
                MainApp.player.mediaPlayer.setAutoPlay(true);
                MainApp.player.setMoviePane(new MediaView(MainApp.player.mediaPlayer));
                MainApp.player.setPlayMovie();
                MainApp.player.initPlayerBar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //create button 3
        JFXButton play=new JFXButton("2");
        play.setButtonType(JFXButton.ButtonType.RAISED);
        play.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        play.setPrefSize(40, 40);
        play.setRipplerFill(Color.valueOf(headerColor));
        play.setScaleX(0);
        play.setScaleY(0);
        //create nodelist
        JFXNodesList nodesList=new JFXNodesList();
        //add button to nodelist
        nodesList.addAnimatedNode(button);
        nodesList.addAnimatedNode(addCollection);
        nodesList.addAnimatedNode(play);

        //set button 1 graph
        SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                "M256 832c-11.712 0-23.36-3.2-33.664-9.536A64.170667 64.170667 0 0 1 192 768V256c0-22.208 11.52-42.816 30.336-54.464a64.298667 64.298667 0 0 1 62.272-2.816l512 256a64.064 64.064 0 0 1 0 114.56l-512 256c-8.96 4.48-18.88 6.72-28.608 6.72z",
                 Color.WHITE);
        glyph.setSize(20, 20);
        button.setGraphic(glyph);
        //set button 2 graph

        //set button 3 graph

        //set nodeilist height
        nodesList.translateYProperty().bind(Bindings.createDoubleBinding(() ->
                header.getBoundsInParent().getHeight() - nodesList.getHeight() / 2,
                header.boundsInParentProperty(), nodesList.heightProperty()));
        nodesList.setSpacing(20d);
        nodesList.setRotate(180);

        StackPane.setMargin(nodesList, new Insets(0, 12, 0, 0));
        StackPane.setAlignment(nodesList, Pos.TOP_RIGHT);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100),
                new KeyValue(button.scaleXProperty(),
                        1,
                        EASE_BOTH),
                new KeyValue(button.scaleYProperty(),
                        1,
                        EASE_BOTH)));
        animation.setDelay(Duration.millis( 3000));
        animation.play();
        this.getChildren().addAll(content, nodesList);

        this.setStyle("-fx-padding: 20px 0px 0px 20px;");
    }



    private String getDefaultColor(int i) {
        String color;
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
                color = "#000000";
                break;
        }
        return color;
    }
}
