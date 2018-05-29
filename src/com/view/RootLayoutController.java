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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane playpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    //和主应用连接
    private MainApp mainApp;

    private  HamburgerNextArrowBasicTransition transition;
    private VBox box;//抽屉栏
     private AnchorPane homePage;//主页面


    /**
     * 设置主应用
     * @param app
     */
    public void  setMainApp(MainApp app){
        mainApp=app;
    }
    public MainApp getMainApp(){return mainApp;}
    public void setVBox(VBox box) {this.box=box;}
    public void setHomePage(AnchorPane page){this.homePage=page;}
    public AnchorPane getPlaypane(){return playpane;}
    @FXML
    private void initialize(){
        try {
            transition = new HamburgerNextArrowBasicTransition(hamburger);
            transition.setRate(-1);

         initDrawerContent();
         initHomePage();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    /* *  初始化抽屉栏.
     * @author PennaLia
     * @date 2018/5/29 1:08
     * @param
     * @return
     */
    public void initDrawerContent(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/DrawerContent.fxml"));
            this.setVBox(loader.load());
            DrawerContentController drawerContentController=loader.getController();
            drawerContentController.setRootLayoutController(this);
            drawer.setSidePane(box);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
/* *  初始化主页面，就是默认主页面时文件页面
 * @author PennaLia
 * @date 2018/5/29 1:18
 * @param
 * @return
 */
    public void initHomePage(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/HomePage.fxml"));
            this.setHomePage(loader.load());

          HomePageController HomePageController=loader.getController();
            HomePageController.setRootLayoutController(this);
            playpane=mainPane;
        }catch(IOException e){
            e.printStackTrace();
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
