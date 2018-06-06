package com.view;

import com.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    @FXML
    private StackPane playmovie;//这个用来外接电影的平面

    AnchorPane bar;

    //把接入的视频界面赋值给play，然后调用setplaymovie，界面就会替换
    StackPane play;
    public void setPlay(StackPane play){
        this.play=play;
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
