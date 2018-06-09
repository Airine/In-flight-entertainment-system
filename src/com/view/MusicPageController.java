package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
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
    @FXML
    private JFXToggleButton LoopPlayback;
    
    private MediaPlayer musicPlayer;
    private boolean stopRequested = false;
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
        setMusicTitle("Reality");
        controlMusic(new MediaPlayer(new Media(url)));
    }
    @FXML
    private void handleMusic(){
        MediaPlayer.Status status = musicPlayer.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
            return;
        }
        if (status == MediaPlayer.Status.PAUSED
                || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            musicPlayer.play();
        } else {
            musicPlayer.pause();
        }
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
                musicPlayer.pause();
                stopRequested = false;
            } else {
                MusicPlayButton.setText("||");
            }
        });

        player.setOnPaused(() -> MusicPlayButton.setText(">"));

        player.setOnReady(() -> {
            duration = musicPlayer.getMedia().getDuration();
            updateValues();
        });

        player.setCycleCount(MediaPlayer.INDEFINITE);

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

        musicPlayer.setOnEndOfMedia(() -> {
            musicPlayer.seek(musicPlayer.getStartTime());
            musicPlayer.pause();
        });
        
        LoopPlayback.selectedProperty().addListener(change ->{
            if (!LoopPlayback.isSelected()){
                musicPlayer.setOnEndOfMedia(() -> {
                    musicPlayer.seek(musicPlayer.getStartTime());
                    musicPlayer.pause();
                });
            }
            else{
                musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(musicPlayer.getStartTime()));
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
