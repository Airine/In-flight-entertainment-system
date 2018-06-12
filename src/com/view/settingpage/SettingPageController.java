package com.view.settingpage;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.util.WebScraping;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import static com.util.DataLoader.loadMovieTypes;
import static com.util.DataLoader.loadMovies;


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
            if(newValue.getText().equals("中文"))
                System.out.println("中文");
            if(newValue.getText().equals("English"))
                System.out.println("英语");
            if(newValue.getText().equals("Français"))
                System.out.println("法语");
        });


    }

    @FXML
    private void handleWebScraping(){
        com.util.WebScraping webScraping = new WebScraping();
        webScraping.generateJson();
        webScraping.updateMovieDataBase();
        MainApp.mainMovies = loadMovies();
        MainApp.mainMovieTypes = loadMovieTypes("en");
    }
    
    public void loadLanguage(String language) {
    }
}
