package com;

import com.model.Movie;
import com.model.MovieType;
import com.model.User;
import com.util.DataUpdater;
import com.util.JsonLoader;
import com.view.LoginController;
import com.view.PlayerPageController;
import com.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.util.DataLoader.*;


public class MainApp extends Application {

    public static User mainUser;
    public static List<Movie> mainMovies;
    public static List<MovieType> mainMovieTypes;
    public static List<Movie> starMovies;
    public static PlayerPageController player;
    public static boolean huiyuan=false;
    public static boolean Admin=false;

    private LoginController controller;
    private RootLayoutController rootLayoutController;
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane Loginpage;
    private Boolean login=false,openLogin=false ;//判断是否已经登陆过，如果已经打开登陆窗口，或者已经登陆，就不能再打开登陆窗口
   //get 和 set
    public AnchorPane getRootLayout(){return  rootLayout;}

    public RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }

    public boolean getLogin(){return login;}
    public boolean getOpenLogin(){return openLogin;}
    public void setLogin(Boolean login) {
        this.login = login;
    }
    public void setOpenLogin(Boolean openLogin) {
        this.openLogin = openLogin;
    }

    public Stage getPrimaryStage() { return primaryStage; }

    public static PlayerPageController getPlayer() {
        return player;
    }

    public static void setPlayer(PlayerPageController player) {
        MainApp.player = player;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(640);
        this.primaryStage.setHeight(480);
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);//隐藏窗口
        this.primaryStage.setResizable(false);//不能改变窗口大小
        this.primaryStage.getIcons().add(new Image("resources/plane.png"));
        initRootLayout();
        initLogin();
        starMovies=new ArrayList<>();
    }
    private void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //连接controller
            rootLayoutController=loader.getController();
            rootLayoutController.setMainApp(this);
            rootLayoutController.getMovieTableViewController().addTextListen();
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    private void initLogin(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/login.fxml"));
            Loginpage = (AnchorPane) loader.load();
            controller = loader.getController();
            controller.setMainApp(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void login(){
        try {
            if(!login && !openLogin) {
                Stage dialogStage = new Stage();
                openLogin=true; //打开了窗口就不能再打开了
                dialogStage.setTitle("login");
                dialogStage.setResizable(false);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(Loginpage);
                dialogStage.setScene(scene);
                controller.setDialogStage(dialogStage);
                controller.setCloseAction();
                dialogStage.initStyle(StageStyle.TRANSPARENT);//隐藏窗口
                dialogStage.showAndWait();//只能用户主动关闭
                rootLayoutController.getDrawerContentController().setUser();

            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void closeWindows(){
        DataUpdater.writeBackCollection();
        if (mainUser!=null)
            DataUpdater.updateUser(mainUser);
        System.out.println("Write Back Compelete.");
        primaryStage.close();
    }

    public static void main(String[] args) {
        loadUsers();
        mainMovies = loadMovies();
        mainMovieTypes = loadMovieTypes("en");

        launch(args);
    }


    public void changeLanguage(String language){
        rootLayoutController.loadLanguage(language);
        controller.loadLanguage(language);
    }


}
