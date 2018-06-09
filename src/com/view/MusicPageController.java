package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import static com.view.PlayerBarController.formatTime;

public class MusicPageController {
    @FXML
    private AnchorPane musicPane;
    @FXML
    private Label musicTitle;
    @FXML
    private JFXSlider musicSlider;
    @FXML
    private JFXButton MusicPlayButton;
    @FXML
    private JFXSlider MusicVolume;
    @FXML
    private Label MusicPlayTime;
    private MediaPlayer musicPlayer;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    public AnchorPane getMusicPane() {
        return musicPane;
    }

    @FXML
    private void initialize(){
        MusicPlayButton.setText(">");
        String url = getClass().getResource("/resources/music/Richard_Sanderson-Reality.mp3").toString();
        controlMusic(new MediaPlayer(new Media(url)));
    }
    @FXML
    private void handleMusic(){
        MediaPlayer.Status status = musicPlayer.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
            // don't do anything in these states
            return;
        }
        if (status == MediaPlayer.Status.PAUSED
                || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            // rewind the movie if we're sitting at the end
            if (atEndOfMedia) {
                musicPlayer.seek(musicPlayer.getStartTime());
                atEndOfMedia = false;
            }
            musicPlayer.play();
        } else {
            musicPlayer.pause();
        }
    }
    @FXML
    private void musicCyclePlay(){
        musicPlayer.setOnEndOfMedia(() -> {
            stopRequested = false;
            atEndOfMedia = true;
        });
    }
    
    public void setMusicTitle(String music){
        musicTitle.setText(music);
    }

    @FXML
    private void nextSong(){
       
    }
    @FXML
    private void previousSong(){
        
    }
    
    public void controlMusic(MediaPlayer player){
        musicPlayer = player;
        player.currentTimeProperty().addListener(ov -> updateValues());
        player.setOnPlaying(() -> {
            if (stopRequested) {
                player.pause();
                stopRequested = false;
            } else {
                MusicPlayButton.setText("||");
            }
        });

        player.setOnPaused(() -> MusicPlayButton.setText(">"));

        player.setOnReady(() -> {
            duration = player.getMedia().getDuration();
            updateValues();
        });

        player.setCycleCount(MediaPlayer.INDEFINITE);

        player.setOnEndOfMedia(() -> {
            player.pause();
            atEndOfMedia = true;
        });

        musicSlider.valueProperty().addListener(ov -> {
            if (musicSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                musicPlayer.seek(duration.multiply(musicSlider.getValue() / 100.0));
            }
        });

        MusicVolume.valueProperty().addListener(ov -> {
            if (MusicVolume.isValueChanging()) {
                musicPlayer.setVolume(MusicVolume.getValue() / 100.0);
            }
        });
        
    }
    private void updateValues() {
        if (MusicPlayTime != null && musicSlider != null && MusicVolume != null) {
            Platform.runLater(() -> {
                Duration currentTime = musicPlayer.getCurrentTime();
                MusicPlayTime.setText(formatTime(currentTime, duration));
                musicSlider.setDisable(duration.isUnknown());
                if (!musicSlider.isDisabled()
                        && duration.greaterThan(Duration.ZERO)
                        && !musicSlider.isValueChanging()) {
                    musicSlider.setValue(currentTime.divide(duration).toMillis()
                            * 100.0);
                }
                if (!MusicVolume.isValueChanging()) {
                    MusicVolume.setValue((int) Math.round(musicPlayer.getVolume()
                            * 100));
                }
            });
        }
    }
}
