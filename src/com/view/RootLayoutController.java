package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Line homeline;

    @FXML
    private Line playline;

    @FXML
    private Line musicline;

    @FXML
    private AnchorPane homepage;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane SettingPane;

    //和主应用连接
    private MainApp mainApp;

    private  HamburgerNextArrowBasicTransition transition;
    private VBox box;//抽屉栏
    public AnchorPane homeP,musicP,playerP;

    /**
     * 设置主应用
     * @param app
     */
    public void  setMainApp(MainApp app){
        mainApp=app;
    }
    public MainApp getMainApp(){return mainApp;}
    public void setVBox(VBox box) {this.box=box;}
    public void setHomePage(AnchorPane page){homepage.getChildren().setAll(page);}
    public AnchorPane getHomepage() { return homepage; }
    public void setSettingPane(AnchorPane pane ){SettingPane.getChildren().setAll(pane);}
    public AnchorPane getSettingPane(){return SettingPane;}
    @FXML
    private void initialize(){
        try {
            transition = new HamburgerNextArrowBasicTransition(hamburger);
            transition.setRate(-1);

         initDrawerContent();
         initHomePage();
         initMusicPage();
         initPlayerPage();
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
            drawer.setVisible(false);
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
           homeP=loader.load();
          HomePageController HomePageController=loader.getController();
            HomePageController.setRootLayoutController(this);
            setHomePage(homeP);
            homeline.setVisible(true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void initMusicPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MusicPage.fxml"));
            musicP = loader.load();
            MusicPageController musicPageController = loader.getController();
            musicPageController.setRootLayoutController(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void initSettingPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/SettingPage.fxml"));
            AnchorPane setting = loader.load();
           SettingPageController settingPageController = loader.getController();
         settingPageController.setRootLayoutController(this);
         setSettingPane(setting);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initPlayerPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PlayerPage.fxml"));
            playerP = loader.load();
            PlayerPageController playerPageController = loader.getController();
            playerPageController.setRootLayoutController(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setSettingVisible(){
        getSettingPane().setVisible(true);
    }
    public void homePageNotSee(){
        homepage.setVisible(false);
    }
    public void homePageSee(){
        homepage.setVisible(true);
    }
    @FXML
    private void handlehamburger(){
            transition.setRate(transition.getRate()*-1);
            transition.play();
        if(drawer.isOpened()) {
            drawer.close();
            homePageSee();
            drawer.setVisible(false);
            getSettingPane().setVisible(false);
        }
        else {
            drawer.setVisible(true);
            drawer.open();
        }
    }

    @FXML
    private void seeHomepage(){
     setHomePage(homeP);
     musicline.setVisible(false);
     playline.setVisible(false);
     homeline.setVisible(true);
    }
    @FXML
    private void seePlaypage(){
        setHomePage(playerP);
        musicline.setVisible(false);
        playline.setVisible(true);
        homeline.setVisible(false);

    }
    @FXML
    private void seeMusicpage(){
        setHomePage(musicP);
        musicline.setVisible(true);
        playline.setVisible(false);
        homeline.setVisible(false);

    }

    @FXML
    private void handleClose(){
        mainApp.closeWindows();
    }

}
