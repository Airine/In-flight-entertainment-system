package com.view;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerBarController {
    @FXML
    private JFXSlider ProgressBar;

    @FXML
    private JFXSlider volume;

    @FXML
    private ImageView PlayOrStop;
    private PlayerPageController playerPageController;
    Image stop;
    Image play;
    Boolean playing=true;

/* *  连接上一层的playpage
 * @author PennaLia
 * @date 2018/6/2 19:03
 * @param
 * @return
 */
    public void setPlayPageController(PlayerPageController Controller){
        this.playerPageController=Controller;
    }
/* *  初始化设置
 * @author PennaLia
 * @date 2018/6/2 19:03
 * @param
 * @return
 */
    @FXML
    private void initialize(){
     stop=new Image("resources/icon/vediostop.png");
     play=new Image("resources/icon/vedioplay.png");
    }

    /* *  用于设置播放按钮和暂停按钮。一开始是默认播放的.
     * @author PennaLia
     * @date 2018/6/2 19:03
     * @param
     * @return
     */
    @FXML
    public void handlePlayOrStop(){
       if(playing){
           PlayOrStop.setImage(play);
           playing=false;
       }else{
           PlayOrStop.setImage(stop);
           playing=true;
       }
    }

}
