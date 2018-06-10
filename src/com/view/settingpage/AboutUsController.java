package com.view.settingpage;

import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXSpinner;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AboutUsController {

    @FXML
    private JFXSpinner spinner;

    @FXML
    private AnchorPane aboutusuppane;

    @FXML
    private JFXChipView<?> pingjia;
    public AnchorPane getAboutusuppane(){
        return aboutusuppane;
    }

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
