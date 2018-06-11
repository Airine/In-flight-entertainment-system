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

public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    @FXML
    private JFXSpinner spinner;//转不转

    @FXML
    private JFXButton skip;

    @FXML
    private StackPane playmovie;//这个用来外接电影的平面

    AnchorPane bar;
    PlayerBarController playerBarController;
    //把接入的视频界面赋值给play，然后调用setplaymovie，界面就会替换
    StackPane MoviePane;
    public MediaPlayer mediaPlayer;
    private RootLayoutController rootLayoutController;
    
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public JFXSpinner getSpinner() {
        return spinner;
    }

    public void setMoviePane(MediaView mediaView){
        MoviePane = new StackPane();
        MoviePane.autosize();
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(MoviePane.widthProperty());
        MoviePane.getChildren().add(mediaView);
        MoviePane.setStyle("-fx-background-color: black;");
    }

    public void setPlayMovie(){
        playmovie.getChildren().add(MoviePane);
    }
    
    public RootLayoutController getRootLayoutController(){
        return  rootLayoutController;
    }
    
    @FXML
    private void initialize(){
        try {
            //这里随便加了个视频
            mediaPlayer = new MediaPlayer(new Media(
                    "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
            setMoviePane(new MediaView(mediaPlayer));
            setPlayMovie();
            initPlayerBar();
        }catch (Exception e){
            e.printStackTrace();
        }
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

}
