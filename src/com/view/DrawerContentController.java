package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class DrawerContentController {
    @FXML
    private VBox drawerBox;

    @FXML
    private ImageView userImage;

    @FXML
    private JFXButton button1;

    @FXML
    private JFXButton button2;

    @FXML
    private JFXButton button3;
    private boolean clicked =false;

    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }




  @FXML
  private  void handleSetting(){
        rootLayoutController.initSettingPage();
        rootLayoutController.setSettingVisible();
        rootLayoutController.homePageNotSee();
  }

    @FXML
    private void handleLogin() {
    rootLayoutController.getMainApp().login();
    }
    @FXML
    private void handlebutton1() {
        AnchorPane homepage= rootLayoutController.getHomepage();
        JFXFillTransition transition=new JFXFillTransition();
        transition.setDuration(Duration.millis(5000));
        transition.setRegion(homepage);
        if(!clicked){
            transition.setFromValue(Color.WHITE);
            transition.setToValue(Color.BLACK);
            transition.play();
            clicked=true;
        }else {
            transition.setFromValue(Color.PINK);
            transition.setToValue(Color.WHITE);
            transition.play();
            clicked=false;
        }
    }
}
