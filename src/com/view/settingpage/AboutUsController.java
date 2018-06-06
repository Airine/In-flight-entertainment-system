package com.view.settingpage;

import com.jfoenix.controls.JFXSpinner;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

public class AboutUsController {

    @FXML
    private JFXSpinner spinner;

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    @FXML
    private void initialize() {

    }

    private void initSpinner(){

    }

    @FXML
    public void handleCheckUpdata(){
        spinner.setVisible(true);

    }
}
