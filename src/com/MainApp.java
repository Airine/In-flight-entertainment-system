package com;

import com.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("视频");
        this.primaryStage.getIcons().add(new Image("resources/shulilogo.png"));
        initRootLayout();
    }
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout =  loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //连接controller
            RootLayoutController controller=loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
