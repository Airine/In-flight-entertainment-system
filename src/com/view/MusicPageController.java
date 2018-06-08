package com.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MusicPageController {
    @FXML
    private AnchorPane musicuppane;
    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public AnchorPane getMusicuppane() {
        return musicuppane;
    }
}
