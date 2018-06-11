package com.view.settingpage;

import com.jfoenix.controls.*;
import com.view.RootLayoutController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimingController {
    @FXML
    private AnchorPane timinguppane;
    @FXML
    private JFXDatePicker choosedata;

    @FXML
    private JFXButton commit;


    @FXML
    private JFXTimePicker chosetime;
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public JFXButton getCommit() {
        return commit;
    }

    public AnchorPane getTiminguppane() {
        return timinguppane;
    }

    String closeTime;

    public Timeline timer;
    public boolean waitForClose=false;
    int count=0;
    @FXML
    public void handleTimingClose(){
        if(choosedata.getValue()==null||chosetime.getValue()==null){
            return;
        }
        commit.setStyle("-fx-background-color: #A9A9A9;" +
                "    -fx-font-color: #000000;");
        closeTime=choosedata.getValue().toString().substring(0,5)+choosedata.getValue().toString().substring(6,10)+" "+chosetime.getValue();
        timer = new Timeline(new KeyFrame(Duration.seconds(10),ev->{
            if(waitForClose){
                rootLayoutController.getMainApp().closeWindows();
            }else {
                System.out.println("开始"+count);count++;
                String now = new Date().toLocaleString().toString().substring(0, 15);
                if (now.equals(closeTime)) {
                    waitForClose=true;//next time it will close auto
                    rootLayoutController.timerOutClose();
                }
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }



    public void loadLanguage(String language) {

    }
}
