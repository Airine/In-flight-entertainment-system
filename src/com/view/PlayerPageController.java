package com.view;

import com.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    @FXML
    private StackPane playmovie;//这个用来外接电影的平面

    AnchorPane bar;
    PlayerBarController playerBarController;
    //把接入的视频界面赋值给play，然后调用setplaymovie，界面就会替换
    StackPane play;
    
    public void setPlay(MediaView mediaView){
        play = new StackPane();
        play.autosize();
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(play.widthProperty());
        play.getChildren().add(mediaView);
        play.setStyle("-fx-background-color: black;");
    }

    private void setPlaymovie(){
        playmovie.getChildren().add(play);
    }

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    
    MediaPlayer mediaPlayer;
    
    @FXML
    private void initialize(){
        try {
            //这里随便加了个视频
            mediaPlayer = new MediaPlayer(new Media(
                    "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
            setPlay(new MediaView(mediaPlayer));
            setPlaymovie();
            initPlayerBar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initPlayerBar(){
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

}
