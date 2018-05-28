package com;

import com.view.LoginController;
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
    private Boolean login=false,openLogin=false ;//判断是否已经登陆过，如果已经打开登陆窗口，或者已经登陆，就不能再打开登陆窗口
   //get 和 set
    public AnchorPane getRootLayout(){return  rootLayout;}
   public boolean getLogin(){return login;}
    public boolean getOpenLogin(){return openLogin;}
    public void setLogin(Boolean login) {
        this.login = login;
    }
    public void setOpenLogin(Boolean openLogin) {
        this.openLogin = openLogin;
    }

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


  public void login(){
        try {
            if(login==false&&openLogin==false) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class
                        .getResource("view/Login.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                openLogin=true; //打开了窗口就不能再打开了
                dialogStage.setTitle("Login");
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                LoginController controller = loader.getController();
                controller.setMainApp(this);
                controller.setDialogStage(dialogStage);
                controller.setCloseAction();
                dialogStage.showAndWait();//只能用户主动关闭
            }
        }catch (Exception e){
            e.getStackTrace();
        }
  }


    public static void main(String[] args) {
        launch(args);
    }
}
