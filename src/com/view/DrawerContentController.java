package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class DrawerContentController {
    @FXML
    private VBox drawerBox;

    @FXML
    private Pane UserBackPane;

    @FXML
    private Pane UserBackUpPane;

    @FXML
    private ImageView userImage;

    @FXML
    private ImageView huiyuan;

    @FXML
    private JFXButton button1;

    @FXML
    private JFXButton button2;

    @FXML
    private JFXButton button3;

    @FXML
    private JFXButton user_icon;

    @FXML
    private JFXToggleButton night;

    @FXML
    private Label usersign;

    @FXML
    private Label username;


    private boolean clicked =false;
    private boolean Tonight=false;
    private  RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public String getUserName(){
        return username.getText();
    }
    public String getUserSign(){
        return usersign.getText();
    }


    public Pane getUserBackPane(){
        return UserBackPane;
    }
    public Pane getUserBackUpPane(){
        return UserBackUpPane;
    }

    @FXML
    public void initialize(){
        String url = "resources/shiyuan.png";
        user_icon.setStyle(
                "-fx-background-image: url(\"" + url + "\");" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;");
       initToggle();//初始化他的监听拖动
    }

    public void changNameAndSign(String name,String sign){
        username.setText(name);
        usersign.setText(sign);
    }
    public void changeUserImage(String url){
        user_icon.setStyle("-fx-background-image: url(\"" + url + "\");");
    }
    @FXML
    public void beHuiyuan(){
        huiyuan.setImage(new Image("resources/icon/truehuiyuan.png"));
        rootLayoutController.getMainApp().huiyuan=true;
    }

    @FXML
    private void handleSetting(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
       rootLayoutController.setSettingPane(rootLayoutController.setting);
    }
    @FXML
    private void handleAboutus(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.aboutus);
    }
    @FXML
    private void handleTheme(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.theme);
    }
    @FXML
    private void handleMoney(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.money);
    }
    @FXML
    private void handleTiming(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.timing);
    }
    @FXML
    private void handleCollection(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.collection);
    }
    @FXML
    private void handleEdit(){
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.edit);
    }

    @FXML
    private void handleLogin() {
        rootLayoutController.getMainApp().login();
    }

    private void initToggle(){
        night.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(night.isSelected()==true){//当拨动到开
                    rootLayoutController.ToNight();
                    System.out.println("晚安");
                }else{//当拨动到关
                    rootLayoutController.ToDefault();
                    System.out.println("早安");
                }
            }
        });
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
