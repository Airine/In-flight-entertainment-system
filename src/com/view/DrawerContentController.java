package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
    private void handleClickedImage() {
    rootLayoutController.getMainApp().login();
    }
    @FXML
    private void handlebutton1() {
        AnchorPane rootLayout= rootLayoutController.getPlaypane();
        JFXFillTransition transition=new JFXFillTransition();
        transition.setDuration(Duration.millis(5000));
        transition.setRegion(rootLayout);
        if(!clicked){
            transition.setFromValue(Color.WHITE);
            transition.setToValue(Color.RED);
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
