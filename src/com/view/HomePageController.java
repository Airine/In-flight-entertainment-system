package com.view;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HomePageController {

    @FXML
    private JFXScrollPane HomeScrollPane;

    @FXML
    private JFXMasonryPane HomeMasonryPane;


    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    @FXML
    private void initialize(){

        for (int i = 0; i < 100; i++) {
            Label label=new Label();
            label.setPrefSize(50,50);
            label.setStyle("-fx-background-color: red");
            HomeMasonryPane.getChildren().add(label);
        }
        HomeScrollPane.setContent(HomeMasonryPane);

    }



}
