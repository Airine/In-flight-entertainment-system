package com.view.settingpage;

import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingPageController {

    @FXML
    private AnchorPane settinguppane;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public AnchorPane getSettinguppane(){
        return  settinguppane;
    }
}
