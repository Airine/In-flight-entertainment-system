package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;


public class MoneyController {
    public Label VIP;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public Label month;
    @FXML
    private JFXButton pay;
    @FXML
    private AnchorPane moneyuppane;

    private boolean payWay;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public AnchorPane getMoneyuppane(){
        return  moneyuppane;
    }

    @FXML
    public void handleBeHuiYuan(){
        rootLayoutController.getDrawerContentController().beHuiyuan();
    }

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"money");
        try {
            assert jsonObject != null;
            label1.setText(jsonObject.getString("label1"));
            label2.setText(jsonObject.getString("label2"));
            label3.setText(jsonObject.getString("label3"));
            label4.setText(jsonObject.getString("label4"));
            label5.setText(jsonObject.getString("label5"));
            pay.setText(jsonObject.getString("pay"));
            month.setText(jsonObject.getString("month"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
