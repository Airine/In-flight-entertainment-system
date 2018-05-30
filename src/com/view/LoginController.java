package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static com.util.Tool.getUser;


public class LoginController {
    public JFXTextField user_name;
    public JFXPasswordField user_pw;

    public JFXButton Login;
    public JFXButton Sign;
    private Stage dialogStage;
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp){this.mainApp=mainApp;}
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    public Stage getDialogStage() { return dialogStage; }


    @FXML
    private void initialize(){
    }

    /* *  这个方法用来监听关闭，以便于可以再次代开login.
     * @author PennaLia
     * @date 2018/5/29 0:48
     * @param
     * @return
     */
    public   void setCloseAction(){
        dialogStage.setOnCloseRequest(windowEvent -> {
            mainApp.setOpenLogin(false);
        });
    }



    public void pressLoginButton(MouseEvent mouseEvent) {
        Login.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void releaseLoginButton(MouseEvent mouseEvent) {
        Login.setButtonType(JFXButton.ButtonType.RAISED);
    }

    public void clickLoginButton(MouseEvent mouseEvent) {
        String input = user_name.getText();
        System.out.println(input);
    }


    public void pressSignButton(MouseEvent mouseEvent) {
        Sign.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void releaseSignButton(MouseEvent mouseEvent) {
        Sign.setButtonType(JFXButton.ButtonType.RAISED);
    }

    public void getUsernameChanged(InputMethodEvent inputMethodEvent) {
        String input = user_name.getText();
        System.err.println(input);
    }

    public void getPwChanged(InputMethodEvent inputMethodEvent) {
    }


    public void getUsernameTyped(KeyEvent keyEvent) {
        String input = user_name.getText()+keyEvent.getCharacter();
        System.err.println(input);
        System.err.println((getUser(input) !=null));
    }
}
