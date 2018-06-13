package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import com.util.JsonLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static com.view.PlayerBarController.formatTime;

/**
 * @author 黄珂邈
 * <h>
 * The Controller of the Music Playing
 * </h>
 * @see PlayerBarController
 */
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

    private File[] musicList;

    private MediaPlayer currentPlayer;

    private int musicIndex;
    private boolean stopRequested = false;
    private Duration duration;
    private RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public AnchorPane getMusicPane() {
        return musicPane;
    }

    public void setMusicTitle(String music) {
        musicTitle.setText(music);
    }

    /* *  initialize everything.
     * @author PennaLia
     * @date 2018/6/13 10:27
     * @param
     * @return
     */
    @FXML
    private void initialize() {
        String folder = getClass().getResource("/resources/music/").getPath();
        musicList = new File(folder).listFiles();
        musicIndex = 0;
        assert musicList != null;
        controlMusic(new MediaPlayer(new Media(musicList[musicIndex].toURI().toString())));
        String fileName = musicList[musicIndex].getName();
        int index = fileName.lastIndexOf("-");
        if (index == -1)
            setMusicTitle(fileName.substring(0, fileName.length() - 4));
        else
            setMusicTitle(fileName.substring(index + 2, fileName.length() - 4));
    }

    /* *  handle music status to decide  what should we do
     * @author PennaLia
     * @date 2018/6/13 10:27
     * @param
     * @return
     */
    @FXML
    private void handleMusic() {
        MediaPlayer.Status status = currentPlayer.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
            return;
        }
        if (status == MediaPlayer.Status.PAUSED
                || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            currentPlayer.play();
        } else {
            currentPlayer.pause();
        }
    }

    /* *  when you click music bar, it will change the music state.
     * @author PennaLia
     * @date 2018/6/13 10:28
     * @param
     * @return
     */
    @FXML
    private void handleClickMusicBar() {
        currentPlayer.seek(duration.multiply(musicSlider.getValue() / 100.0));
    }

    /* *  click music volume it will change its volume.
     * @author PennaLia
     * @date 2018/6/13 10:29
     * @param
     * @return
     */
    @FXML
    private void handleClickMusicVolume() {
        currentPlayer.setVolume(MusicVolume.getValue() / 100.0);
    }

    /**
     * We only use local songs.
     * The songs are in order and they are stored in file list.
     * We can switch songs by pressing the button.
     */
    @FXML
    private void nextSong() {
        if (musicIndex >= musicList.length - 1) {
            System.out.println("This is already the last song.");
        } else {
            currentPlayer.stop();
            controlMusic(new MediaPlayer(new Media(musicList[++musicIndex].toURI().toString())));
            String fileName = musicList[musicIndex].getName();
            int index = fileName.lastIndexOf("-");
            if (index == -1)
                setMusicTitle(fileName.substring(0, fileName.length() - 4));
            else
                setMusicTitle(fileName.substring(index + 2, fileName.length() - 4));
            currentPlayer.play();
        }
    }

    @FXML
    private void previousSong() {
        if (musicIndex <= 0) {
            System.out.println("This is already the first song.");
        } else {
            currentPlayer.stop();
            controlMusic(new MediaPlayer(new Media(musicList[--musicIndex].toURI().toString())));
            String fileName = musicList[musicIndex].getName();
            int index = fileName.lastIndexOf("-");
            if (index == -1)
                setMusicTitle(fileName.substring(0, fileName.length() - 4));
            else
                setMusicTitle(fileName.substring(index + 2, fileName.length() - 4));
            currentPlayer.play();
        }
    }

    /* *  to control music.
     * @author PennaLia
     * @date 2018/6/13 10:29
     * @param
     * @return
     */
    public void controlMusic(MediaPlayer player) {
        currentPlayer = player;
        player.currentTimeProperty().addListener(ov -> updateValues());
        player.setOnPlaying(() -> {
            if (stopRequested) {
                currentPlayer.pause();
                stopRequested = false;
            } else {
                MusicPlayButton.setText("||");
            }
        });

        player.setOnPaused(() -> MusicPlayButton.setText(">"));

        player.setOnReady(() -> {
            duration = currentPlayer.getMedia().getDuration();
            updateValues();
        });

        player.setCycleCount(MediaPlayer.INDEFINITE);

        musicSlider.valueProperty().addListener(ov -> {
            if (musicSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                currentPlayer.seek(duration.multiply(musicSlider.getValue() / 100.0));
            }
        });

        MusicVolume.valueProperty().addListener(ov -> {
            if (MusicVolume.isValueChanging()) {
                currentPlayer.setVolume(MusicVolume.getValue() / 100.0);
            }
        });

        currentPlayer.setOnEndOfMedia(() -> {
            currentPlayer.seek(currentPlayer.getStartTime());
            currentPlayer.pause();
        });

        LoopPlayback.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!LoopPlayback.isSelected()) {
                currentPlayer.setOnEndOfMedia(() -> {
                    currentPlayer.seek(currentPlayer.getStartTime());
                    currentPlayer.pause();
                });
            } else {
                currentPlayer.setOnEndOfMedia(() -> currentPlayer.seek(currentPlayer.getStartTime()));
            }
        });

    }

    /* *  to load langugae from json.
     * @author PennaLia
     * @date 2018/6/13 10:30
     * @param
     * @return
     */
    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "music");
        try {
            assert jsonObject != null;
            LoopPlayback.setText(jsonObject.getString("loop"));
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* *  updates the music values.
     * @author PennaLia
     * @date 2018/6/13 10:30
     * @param
     * @return
     */
    private void updateValues() {
        if (MusicPlayTime != null && musicSlider != null && MusicVolume != null) {
            Platform.runLater(() -> {
                Duration currentTime = currentPlayer.getCurrentTime();
                MusicPlayTime.setText(formatTime(currentTime, duration));
                musicSlider.setDisable(duration.isUnknown());
                if (!musicSlider.isDisabled()
                        && duration.greaterThan(Duration.ZERO)
                        && !musicSlider.isValueChanging()) {
                    musicSlider.setValue(currentTime.divide(duration).toMillis()
                            * 100.0);
                }
                if (!MusicVolume.isValueChanging()) {
                    MusicVolume.setValue((int) Math.round(currentPlayer.getVolume()
                            * 100));
                }
            });
        }
    }
}
