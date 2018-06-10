package com.view.settingpage;

import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class CollectionController {
    @FXML
    private AnchorPane collectionuppane;

    public AnchorPane getCollectionuppane(){
        return collectionuppane;
    }
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public void loadLanguage(String language) {
    }
}
