package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.view.RootLayoutController;
import javafx.fxml.FXML;




public class MoneyController {
    @FXML
    private JFXButton pay;

    private boolean payWay;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    @FXML
    public void handleBeHuiYuan(){
        rootLayoutController.getDrawerContentController().beHuiyuan();
    }

}
