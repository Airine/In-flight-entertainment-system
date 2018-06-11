package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;

public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    @FXML
    private JFXSpinner spinner;//转不转

    @FXML
    private JFXButton skip;

    @FXML
    private StackPane playmovie;//这个用来外接电影的平面

    @FXML
    private StackPane warningPane;        
    
    AnchorPane bar;
    PlayerBarController playerBarController;
    //把接入的视频界面赋值给play，然后调用setplaymovie，界面就会替换
    StackPane MoviePane;

    public MediaPlayer mediaPlayer;
    public MediaPlayer advertismentPlayer;
    public MediaView mediaView;
    
    private RootLayoutController rootLayoutController;
    
    public RootLayoutController getRootLayoutController(){
        return  rootLayoutController;
    }
    
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    
    @FXML
    private void handleSkip(){
        skip.setVisible(false);
        advertismentPlayer.stop();
        mediaView.setMediaPlayer(mediaPlayer);
        if(mediaPlayer.getStatus()!=MediaPlayer.Status.READY){
            spinner.setVisible(true);
        }
        else {
            advertismentPlayer.stop();
            mediaView.setMediaPlayer(mediaPlayer);
            Bar.setDisable(false);
        }
    }
    
    public StackPane getPlaymovie() {
        return playmovie;
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
    
    public void setMoviePane(){
        MoviePane = new StackPane();
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(MoviePane.widthProperty());
        MoviePane.getChildren().add(mediaView);
        MoviePane.setStyle("-fx-background-color: black;");
    }

    public void setPlayMovie(){
        playmovie.getChildren().add(MoviePane);
    }
    
    public void initPlayerBar(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PlayerBar.fxml"));
            bar = loader.load();
            Bar.getChildren().setAll(bar);
            playerBarController = loader.getController();
            playerBarController.setPlayPageController(this);
            playerBarController.controlPlayer(mediaPlayer);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void initialize(){
        try {
            skip.setVisible(false);
            mediaView = new MediaView();
            advertismentPlayer = new MediaPlayer(new Media(
                    getClass().getResource("/resources/advertisement/flight.mp4").toExternalForm()));
            spinner.setVisible(false);
            mediaPlayer = new MediaPlayer(new Media("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
            mediaView.setMediaPlayer(mediaPlayer);
            setMoviePane();
            setPlayMovie();
            initPlayerBar();
            advertismentPlayer.setOnEndOfMedia(()->{
                advertismentPlayer.stop();
                spinner.setVisible(true);
                if(mediaPlayer.getStatus()==MediaPlayer.Status.READY){
                    mediaView.setMediaPlayer(mediaPlayer);
                    Bar.setDisable(false);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void setPlayerWithBar(Media media){
        advertismentPlayer.play();
        Bar.setDisable(true);
        if(rootLayoutController.getMainApp().huiyuan)
            skip.setVisible(true);
        spinner.setVisible(false);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(advertismentPlayer);
        setMoviePane();
        setPlayMovie();
        initPlayerBar();
    }
}
