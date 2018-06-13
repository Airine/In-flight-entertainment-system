package com.view.settingpage;

import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

/* *  this is class control, just used to contol collection pane
 * @author PennaLia
 * @date 2018/6/12 21:07
 * @version Player Version 1.0
 */
public class CollectionController {
    //this is mycolleciton pane
    @FXML
    private FlowPane mycollection;
    //this is roolayout controller that connect everything.
    private RootLayoutController rootLayoutController;

    public FlowPane getMycollection() {
        return mycollection;
    }

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }


}

