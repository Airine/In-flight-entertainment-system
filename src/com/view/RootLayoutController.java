package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    //和主应用连接
    private MainApp mainApp;

    private  HamburgerNextArrowBasicTransition transition;




    /**
     * 设置主应用
     * @param app
     */
    public void  setMainApp(MainApp app){
        mainApp=app;
    }

    @FXML
    private void initialize(){
        try {
            transition = new HamburgerNextArrowBasicTransition(hamburger);
            transition.setRate(-1);

            VBox box = FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
            drawer.setSidePane(box);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @FXML
    private void handlehamburger(){
            transition.setRate(transition.getRate()*-1);
            transition.play();
        if(drawer.isOpened())
            drawer.close();
        else
            drawer.open();
    }


}
