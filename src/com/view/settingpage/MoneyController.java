package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class MoneyController {
    @FXML
    private JFXButton pay;
    @FXML
    private AnchorPane moneyuppane;

    private boolean payWay;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public AnchorPane getMoneyuppane(){
        return  moneyuppane;
    }

    @FXML
    public void handleBeHuiYuan(){
        rootLayoutController.getDrawerContentController().beHuiyuan();
    }

}
