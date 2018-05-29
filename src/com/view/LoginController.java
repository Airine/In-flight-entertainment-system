package com.view;

import com.MainApp;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class LoginController {
    private Stage dialogStage;
    MainApp mainApp;
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


}
