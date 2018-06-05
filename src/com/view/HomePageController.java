package com.view;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.view.viewModel.MovieItem;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

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
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i <30; i++){
           try {
              StackPane sort=new MovieItem(100,180,"resources/movieitem.png","电影分类");
               children.add(sort);

           }catch (Exception e){
               e.printStackTrace();
           }
        }

        //设置平面间距信息
      HomeMasonryPane.getChildren().addAll(children);
//     HomeMasonryPane.setHSpacing(8);
//     HomeMasonryPane.setVSpacing(10);
//        31302F
        HomeMasonryPane.setStyle("-fx-background-color:#FFFFFF;" +
                "-fx-border-color: transparent;");
        HomeScrollPane.setContent(HomeMasonryPane);
        HomeScrollPane.setStyle("-fx-border-color: transparent;");
        //封面图
        ImageView imageView=new ImageView("resources/moviepage2.png");
       imageView.setFitWidth(640);
       imageView.setFitHeight(192);
//       Label label=new Label("Enjoy you movies");
//       label.setStyle("-fx-font-size:  20px;    -fx-text-fill: white;");
//       HomeScrollPane.getBottomBar().getChildren().add(label);
        HomeScrollPane.getMainHeader().getChildren().add(imageView);
       HomeScrollPane.getCondensedHeader().setStyle("-fx-background-color: #FFFFFF;");



    }



}




