package com.view.settingpage;

import com.jfoenix.controls.JFXTextField;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

public class EditController {

    @FXML
    private JFXTextField textname;

    @FXML
    private JFXTextField textsign;
    public JFXTextField getTextname(){return  textname;}
    public JFXTextField getTextsign(){return  textsign;}

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
