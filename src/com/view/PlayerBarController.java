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
        PlayOrStop.setImage(play);

    }

    /* *  用于设置播放按钮和暂停按钮。一开始是默认播放的.
     * @author PennaLia
     * @date 2018/6/2 19:03
     * @param
     * @return
     */
    @FXML
    public void handlePlayOrStop(){

        MediaPlayer.Status status = mp.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
            // don't do anything in these states
            return;
        }

        if (status == MediaPlayer.Status.PAUSED
                || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            // rewind the movie if we're sitting at the end
            if (atEndOfMedia) {
                mp.seek(mp.getStartTime());
                atEndOfMedia = false;
            }
            mp.play();
        } else {
            mp.pause();
        }
    }
    private MediaPlayer mp;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;

    public void controlPlayer(MediaPlayer player) {
        this.mp = player;

        player.currentTimeProperty().addListener(ov -> updateValues());

        player.setOnPlaying(() -> {
            if (stopRequested) {
                player.pause();
                stopRequested = false;
            } else {
                PlayOrStop.setImage(stop);
            }
        });

        player.setOnPaused(() -> PlayOrStop.setImage(play));

        player.setOnReady(() -> {
            duration = player.getMedia().getDuration();
            updateValues();
        });
        
        player.setCycleCount(MediaPlayer.INDEFINITE);
        
        player.setOnEndOfMedia(() -> {
            player.pause();
            stopRequested = true;
            atEndOfMedia = true;
        });
        
        TimeBar.valueProperty().addListener(ov -> {
            if (TimeBar.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                mp.seek(duration.multiply(TimeBar.getValue() / 100.0));
            }
        });

        volume.valueProperty().addListener(ov -> {
            if (volume.isValueChanging()) {
                mp.setVolume(volume.getValue() / 100.0);
            }
        });

    }

}
