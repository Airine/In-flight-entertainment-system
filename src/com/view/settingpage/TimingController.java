package com.view.settingpage;

import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TimingController {
    @FXML
    private AnchorPane timinguppane;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public AnchorPane getTiminguppane() {
        return timinguppane;
    }
}
