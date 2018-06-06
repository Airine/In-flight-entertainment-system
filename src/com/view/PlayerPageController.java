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

    //把接入的视频界面赋值给play，然后调用setplaymovie，界面就会替换
    StackPane play;
    public void setPlay(Media media){
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        this.play = new StackPane();
        play.setStyle("-fx-background-color: #bfc2c7;");
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.fitHeightProperty().bind(play.heightProperty());
        play.getChildren().add(mediaView);
        play.setStyle("-fx-background-color: black;");
    }
    
    private  void setPlaymovie(){
        playmovie.getChildren().addAll(play);
    }



    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    @FXML
    private void initialize(){
        try {
            initPlayerBar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initPlayerBar(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PlayerBar.fxml"));

            bar=(AnchorPane) loader.load();
            Bar.getChildren().setAll(bar);
            PlayerBarController playerBarController=new PlayerBarController();
            playerBarController.setPlayPageController(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
