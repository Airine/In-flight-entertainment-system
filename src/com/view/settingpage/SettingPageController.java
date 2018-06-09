package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;


public class SettingPageController {
    @FXML
    private JFXComboBox<Label> langComBoix;


    @FXML
    private AnchorPane settinguppane;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public AnchorPane getSettinguppane(){
        return  settinguppane;
    }
    Label chinese=new Label("中文");
    Label english=new Label("English");
    Label français=new Label("Français");

    @FXML
    private void initialize(){
        initCombox();
    }
    private  void initCombox(){

        langComBoix.getItems().addAll(chinese,english,français);
        langComBoix.setEditable(false);
        langComBoix.setPromptText("选择语言");
        langComBoix.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }
            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });

    }
}
