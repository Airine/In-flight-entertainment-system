package com.view.settingpage;

import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;


public class CollectionController {
    @FXML
    private FlowPane mycollection;

    public FlowPane getMycollection(){return mycollection;}
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }


}

