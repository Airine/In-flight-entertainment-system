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

    /* *  init the Combox for changing language.
     * @author PennaLia
     * @date 2018/6/12 10:33
     * @param
     * @return
     */
    private void initCombox(){
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
        //选择box做什么
        langComBoix.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.getText()=="中文")
                System.out.println("中文");
            if(newValue.getText()=="English")
                System.out.println("英语");
            if(newValue.getText()=="Français")
                System.out.println("法语");
        });


    }

    public void loadLanguage(String language) {
    }
}
