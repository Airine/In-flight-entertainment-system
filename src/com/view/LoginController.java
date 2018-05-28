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
    //在main app 那面打开窗口后顺便调用这个方法触发监听事件，当窗口关闭的时候就设置关闭信息，以便下次可以打开
    public   void setCloseAction(){
        dialogStage.setOnCloseRequest(windowEvent -> {
            mainApp.setOpenLogin(false);
        });
    }


}
