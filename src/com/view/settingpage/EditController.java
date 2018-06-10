package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

public class EditController {

    public Label name;
    public JFXButton confirmButton;
    public Label modify;
    public Label ps;
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


    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"edit");
        try {
            assert jsonObject != null;
            name.setText(jsonObject.getString("name"));
            ps.setText(jsonObject.getString("ps"));
            confirmButton.setText(jsonObject.getString("confirm"));
            modify.setText(jsonObject.getString("modify"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
