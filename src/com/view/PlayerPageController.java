package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

/**
 * <p>
 * The video player page controller.
 * It should be connected to the bar controller and the other methods relate to video playing.
 * </p>
 */
public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private JFXButton skip;

    @FXML
    private StackPane playmovie;

    @FXML
    private StackPane warningPane;

    AnchorPane bar;
    PlayerBarController playerBarController;

    StackPane MoviePane;

    public MediaPlayer mediaPlayer; // This is the movie player
    public MediaPlayer advertisementPlayer;
    public MediaView mediaView;

    private RootLayoutController rootLayoutController;

    public RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public AnchorPane getBar() {
        return Bar;
    }

    @FXML
    private void handleSkip() {
        skip.setVisible(false);
        advertisementPlayer.stop();
        mediaView.setMediaPlayer(mediaPlayer);
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.setAutoPlay(true);
            spinner.setVisible(true);
        } else {
            advertisementPlayer.stop();
            mediaPlayer.play();
            mediaView.setMediaPlayer(mediaPlayer);
            Bar.setDisable(false);
        }
    }

    public JFXSpinner getSpinner() {
        return spinner;
    }

    public StackPane getWarningPane() {
        return warningPane;
    }

    public JFXButton getSkip() {
        return skip;
    }

    /**
     * Set the stackPane and mediaView and bind the properties to fit the size
     */
    public void setMoviePane() {
        MoviePane = new StackPane();
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(MoviePane.widthProperty());
        MoviePane.getChildren().add(mediaView);
        MoviePane.setStyle("-fx-background-color: black;");
        playmovie.getChildren().add(MoviePane);
    }

    public void initPlayerBar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PlayerBar.fxml"));
            bar = loader.load();
            Bar.getChildren().setAll(bar);
            playerBarController = loader.getController();
            playerBarController.setPlayPageController(this);
            playerBarController.controlPlayer(mediaPlayer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * Initialize the player page,
     * listeners
     * and all the local variables
     * </p>
     */
    @FXML
    private void initialize() {
        try {
            skip.setVisible(false);
            mediaView = new MediaView();
            advertisementPlayer = new MediaPlayer(new Media(
                    getClass().getResource("/resources/advertisement/flight.mp4").toExternalForm()));
            spinner.setVisible(false);
            mediaPlayer = new MediaPlayer(new Media("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
            mediaView.setMediaPlayer(mediaPlayer);
            setMoviePane();
            initPlayerBar();
            advertisementPlayer.setOnEndOfMedia(() -> {
                advertisementPlayer.stop();
                spinner.setVisible(true);
                mediaView.setMediaPlayer(mediaPlayer);
                if (mediaPlayer.getStatus() == MediaPlayer.Status.READY) {
                    mediaView.setMediaPlayer(mediaPlayer);
                    spinner.setVisible(false);
                    Bar.setDisable(false);
                    mediaPlayer.setAutoPlay(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param media The media that we want to set to the page
     * @author 黄珂邈
     * <h>
     * Initially Playing Movies
     * </h>
     * <p>
     * The method set the beginning of the movies.
     * There is an ad at the beginning in order to
     * break the air of waiting the movie to load from the websites.
     * </p>
     */
    public void setPlayerWithBar(Media media) {
        advertisementPlayer.play();
        Bar.setDisable(true);
        if (MainApp.huiyuan)
            skip.setVisible(true);
        spinner.setVisible(false);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(advertisementPlayer);
        setMoviePane();
        initPlayerBar();
    }
}
