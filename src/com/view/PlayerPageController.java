package com.view;

import com.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayerPageController {
    @FXML
    private AnchorPane Bar;

    AnchorPane bar;
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
