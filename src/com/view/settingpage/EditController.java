package com.view.settingpage;

import com.jfoenix.controls.JFXTextField;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class EditController {

    @FXML
    private JFXTextField textname;

    @FXML
    private AnchorPane edituppane;
    @FXML
    private JFXTextField textsign;
    public JFXTextField getTextname(){return  textname;}
    public JFXTextField getTextsign(){return  textsign;}


    public AnchorPane getEdituppane(){
        return edituppane;
    }
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

   public void handleEdit(){
        rootLayoutController.getDrawerContentController().changNameAndSign(
                textname.getText(),textsign.getText()
        );
   }


}
