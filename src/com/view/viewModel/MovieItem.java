package com.view.viewModel;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import com.model.Movie;
import com.util.DataUpdater;
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
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.MainApp.starMovies;
import static com.util.DataLoader.loadMovieTypes;
import static com.util.DataLoader.loadMovies;
import static javafx.animation.Interpolator.EASE_BOTH;

/**
 * @author 田闰心
 * the class is the model of the movies which display in the view page
 */
public class MovieItem extends StackPane {

    private Movie movie;

//    private Movie pram_movie;

    private JFXButton button = new JFXButton();

    private JFXButton playbutton = new JFXButton();

    private JFXButton collectionbutton = new JFXButton();

    private JFXButton deletebutton = new JFXButton();

    private boolean ifClick = false;
//    private boolean ifCollect = false;

    /**
     * <h>Constructor</h>
     *
     * @param movie The movie object
     * @param title the title of the movie
     */
    public MovieItem(Movie movie, String title) {
//        this.movie = new Movie(movie);
        this.movie = movie;
//        pram_movie = movie;
        this.setPrefWidth(200);
        this.setPrefHeight(350);
        JFXDepthManager.setDepth(this, 1);

        // create content
        StackPane header = new StackPane();
        header.setPrefHeight(290);
        header.setStyle("-fx-background-radius: 5 5 0 0; " +
//                "-fx-background-color: " + headerColor +
                "-fx-background-image: url(\"" + movie.getPost_href() + "\");" +
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


        initMainButton();

        initPlayButton(movie.getHref());

        initCollectionButton();

        initDeleteButton();

        //create nodelist
        JFXNodesList nodesList = new JFXNodesList();
        //add button to nodelist
        nodesList.addAnimatedNode(button);
        nodesList.addAnimatedNode(playbutton);
        nodesList.addAnimatedNode(collectionbutton);
        if (MainApp.admin)
            nodesList.addAnimatedNode(deletebutton);

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
        animation.setDelay(Duration.millis(500));
        animation.play();
        this.getChildren().addAll(content, nodesList);

        this.setStyle("-fx-padding: 20px 0px 0px 20px;");
    }

    /**
     * The method is to initialize the deletion button
     */
    private void initDeleteButton() {
        deletebutton = new JFXButton();

        deletebutton.setButtonType(JFXButton.ButtonType.RAISED);
        deletebutton.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        deletebutton.setPrefSize(40, 40);
        deletebutton.setRipplerFill(Color.valueOf(getDefaultColor(13)));
        deletebutton.setScaleX(0);
        deletebutton.setScaleY(0);
        SVGGlyph add_graph = new SVGGlyph(-1,
                "test",
                "M320 544z m505.6-236.8c0-19.2 12.8-32 32-32s32 12.8 32 32v544c0 25.6-12.8 51.2-32 70.4-19.2 19.2-44.8 25.6-70.4 25.6h-544c-25.6 0-51.2-12.8-70.4-32-19.2-19.2-32-44.8-32-70.4V307.2c0-19.2 12.8-32 32-32s32 12.8 32 32v544c0 12.8 6.4 19.2 12.8 32 0 6.4 12.8 12.8 25.6 12.8h544c12.8 0 19.2-6.4 25.6-12.8 6.4-6.4 12.8-19.2 12.8-32v-544zM480 332.8v454.4c0 19.2 12.8 32 32 32s32-12.8 32-32V332.8c0-19.2-12.8-32-32-32s-32 12.8-32 32z m-160 0v454.4c0 19.2 12.8 32 32 32s32-12.8 32-32V332.8c0-19.2-12.8-32-32-32s-32 12.8-32 32z m326.4 0v454.4c0 19.2 12.8 32 32 32s32-12.8 32-32V332.8c0-19.2-12.8-32-32-32s-32 12.8-32 32zM96 211.2c-19.2 0-32-12.8-32-32s12.8-32 32-32h243.2l70.4-70.4c6.4-6.4 12.8-6.4 19.2-6.4h166.4c6.4 0 19.2 6.4 25.6 12.8l64 64H928c19.2 0 32 12.8 32 32s-12.8 32-32 32H672c-6.4 0-12.8 0-19.2-6.4l-70.4-70.4H441.6l-64 64c-6.4 6.4-19.2 12.8-25.6 12.8H96z",
                Color.WHITE);
        add_graph.setSize(20, 20);
        deletebutton.setGraphic(add_graph);
        deletebutton.setOnMouseClicked(event -> {
            DataUpdater.deleteMovie(movie);
            starMovies.remove(movie);
            MainApp.mainMovies = loadMovies();
            MainApp.mainMovieTypes = loadMovieTypes("en");
            this.setVisible(false);
        });
    }

    /**
     * This method is to initialize the main button at the bottom
     */
    private void initMainButton() {

        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        button.setPrefSize(40, 40);
        button.setRipplerFill(Color.valueOf(getDefaultColor(13)));
        button.setScaleX(0);
        button.setScaleY(0);
        SVGGlyph add_graph = new SVGGlyph(-1,
                "test",
                "M512 832a32 32 0 0 0 32-32v-256h256a32 32 0 0 0 0-64h-256V224a32 32 0 0 0-64 0v256H224a32 32 0 0 0 0 64h256v256a32 32 0 0 0 32 32",
                Color.WHITE);
        add_graph.setSize(20, 20);

        SVGGlyph minus_graph = new SVGGlyph(-1,
                "test",
                "M864 448l-768 0C78.08 448 64 462.08 64 480 64 497.92 78.08 512 96 512l768 0C881.92 512 896 497.92 896 480 896 462.08 881.92 448 864 448z",
                Color.WHITE);
        minus_graph.setSize(20, 2);

        button.setGraphic(add_graph);

        button.setOnMouseClicked(event -> {
            if (!ifClick) {
                button.setGraphic(minus_graph);
            } else {
                button.setGraphic(add_graph);
            }
            ifClick = !ifClick;
        });

    }

    /**
     * This method is to initialize the playing button and set turning
     *
     * @param videoUrl The movie url
     */
    private void initPlayButton(String videoUrl) {

        playbutton.setButtonType(JFXButton.ButtonType.RAISED);
        playbutton.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        playbutton.setPrefSize(40, 40);
        playbutton.setRipplerFill(Color.valueOf(getDefaultColor(13)));
        playbutton.setScaleX(0);
        playbutton.setScaleY(0);
        playbutton.setOnMouseClicked(event -> {
            try {
                MainApp.player.mediaPlayer.stop();
                MainApp.player.advertisementPlayer.stop();
                MainApp.player.getRootLayoutController().seePlaypage();
                MainApp.player.getSpinner().setVisible(true);
                String local = "";
                if (movie.getType() == 13) local = "file:";
                MainApp.player.setPlayerWithBar(new Media(local + videoUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //set play button graph
        SVGGlyph play_graph = new SVGGlyph(-1,
                "test",
                "M256 832c-11.712 0-23.36-3.2-33.664-9.536A64.170667 64.170667 0 0 1 192 768V256c0-22.208 11.52-42.816 30.336-54.464a64.298667 64.298667 0 0 1 62.272-2.816l512 256a64.064 64.064 0 0 1 0 114.56l-512 256c-8.96 4.48-18.88 6.72-28.608 6.72z",
                Color.WHITE);
        play_graph.setSize(20, 20);
        playbutton.setGraphic(play_graph);

    }

    /**
     * This method is to initialize the button which can make the movies to be the favourite ones
     */
    private void initCollectionButton() {

        collectionbutton.setButtonType(JFXButton.ButtonType.RAISED);
        collectionbutton.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        collectionbutton.setPrefSize(40, 40);
        collectionbutton.setRipplerFill(Color.valueOf(getDefaultColor(13)));
        collectionbutton.setScaleX(0);
        collectionbutton.setScaleY(0);

        //set collection button graph
        SVGGlyph collection = new SVGGlyph(-1,
                "test",
                "M695.104 546.368c-20.16 19.808-31.328 54.4-26.56 82.368l26.976 158.56-140.992-74.688c-25.056-13.248-61.408-13.28-86.464 0l-140.992 74.656 27.008-158.56c4.736-27.904-6.464-62.528-26.56-82.336l-114.56-112.512 158.08-23.136c27.936-4.096 57.312-25.6 69.792-51.04l70.464-143.872 70.464 143.872c12.512 25.472 41.856 46.944 69.824 51.04l158.08 23.136-114.56 112.512z m182.528-89.536c14.976-14.72 20.384-32.96 14.816-50.016-5.536-17.024-20.64-28.512-41.344-31.552l-190.272-27.872c-6.944-1.024-18.432-9.472-21.6-15.872l-85.088-173.76c-9.248-18.88-24.896-29.76-42.88-29.76-17.92 0-33.568 10.88-42.848 29.76l-85.056 173.76c-3.136 6.4-14.656 14.848-21.632 15.872l-190.272 27.84c-20.704 3.072-35.744 14.56-41.28 31.584-5.6 17.024-0.192 35.264 14.784 50.016L282.624 592c5.12 5.024 9.6 18.848 8.352 25.92l-32.512 190.944c-3.52 20.8 2.784 38.816 17.344 49.344 7.52 5.44 16.256 8.16 25.472 8.16 8.576 0 17.632-2.368 26.56-7.104l170.176-90.144c6.08-3.2 20.48-3.2 26.56 0l170.144 90.144c18.496 9.824 37.504 9.408 52.032-1.056 14.56-10.56 20.896-28.512 17.376-49.312l-32.512-190.976c-1.216-7.072 3.232-20.896 8.32-25.92l137.696-135.2z",
                Color.WHITE);
        collection.setSize(20, 20);

        SVGGlyph collection_fill = new SVGGlyph(-1,
                "test",
                "M877.632 456.8c14.976-14.72 20.384-32.96 14.816-49.984-5.536-17.024-20.608-28.544-41.344-31.584l-190.24-27.84c-6.976-1.024-18.464-9.472-21.6-15.904l-85.12-173.696c-9.28-18.944-24.896-29.76-42.88-29.76-17.952 0-33.6 10.816-42.816 29.76l-85.12 173.696c-3.104 6.432-14.592 14.848-21.6 15.904l-190.24 27.84c-20.704 3.04-35.776 14.56-41.344 31.584-5.568 17.024-0.16 35.232 14.816 49.984l137.696 135.232c5.088 4.992 9.536 18.816 8.32 25.92l-32.48 190.912c-3.552 20.832 2.752 38.816 17.344 49.344 7.52 5.44 16.224 8.16 25.472 8.16 8.576 0 17.6-2.336 26.56-7.04l170.176-90.176c6.048-3.2 20.448-3.2 26.528 0l170.144 90.112c18.528 9.856 37.504 9.44 52.064-1.056 14.56-10.528 20.864-28.48 17.344-49.28l-32.48-190.976c-1.28-7.104 3.2-20.928 8.32-25.92l137.664-135.232z",
                Color.WHITE);
        collection_fill.setSize(20, 20);

        if (!movie.isStar())
            collectionbutton.setGraphic(collection);
        else
            collectionbutton.setGraphic(collection_fill);

        collectionbutton.setOnMouseClicked(event -> {
            if (movie.isStar()) {
                collectionbutton.setGraphic(collection);
                starMovies.remove(movie);
            } else {
                collectionbutton.setGraphic(collection_fill);
                starMovies.add(movie);
            }
            movie.setStar(!movie.isStar());
//            pram_movie.setStar(!movie.isStar());
        });
    }


    /**
     * @param i the id of the color type
     * @return the color with right format
     */
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
