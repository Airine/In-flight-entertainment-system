package com.model;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MovieControl extends BorderPane {
    
    private MediaPlayer mp;
    MediaView mediaView;
    HBox mediaBar;
    StackPane pane;
    Stage stage;
    private final boolean repeat = true;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    
    public MovieControl(final MediaPlayer player) {
        this.mp = player;
        mediaView = new MediaView(player);
        mediaView.setPreserveRatio(true);
        pane = new StackPane();
        mediaView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(mediaView);
        pane.setStyle("-fx-background-color: black;");
        setCenter(pane);
        
        //////////////////////////
        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        
        final Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if(!pane.getChildren().contains(mediaView)){
                pane.getChildren().add(mediaView);
            }
            Status status = player.getStatus();
            if (status == Status.UNKNOWN || status == Status.HALTED) {
                // don't do anything in these states
                return;
            }

            if (status == Status.PAUSED
                    || status == Status.READY
                    || status == Status.STOPPED) {
                // rewind the movie if we're sitting at the end
                if (atEndOfMedia) {
                    player.seek(player.getStartTime());
                    atEndOfMedia = false;
                }
                player.play();
            } else {
                player.pause();
            }
        });
        
        player.currentTimeProperty().addListener(ov -> updateValues());

        player.setOnPlaying(() -> {
            if (stopRequested) {
                player.pause();
                stopRequested = false;
            } else {
                playButton.setText("||");
            }
        });

        player.setOnPaused(() -> playButton.setText(">"));

        player.setOnReady(() -> {
            duration = player.getMedia().getDuration();
            updateValues();
        });

        player.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        
        player.setOnEndOfMedia(() -> {
            playButton.setText(">");
            stopRequested = true;
            atEndOfMedia = true;
        });

        mediaBar.getChildren().add(playButton);
        // Add spacer
        Label spacer1 = new Label("   ");
        mediaBar.getChildren().add(spacer1);

        // Add Time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(ov -> {
            if (timeSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                player.seek(duration.multiply(timeSlider.getValue() / 100.0));
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(110);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(ov -> {
            if (volumeSlider.isValueChanging()) {
                player.setVolume(volumeSlider.getValue() / 100.0);
            }
        });
        mediaBar.getChildren().add(volumeSlider);
        
        Label spacer2 = new Label("  ");
        mediaBar.getChildren().add(spacer2);
        
        Image image = new Image("resources/icon/fullScreen.png");
        final Button fullScreenButton = new Button();
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(17);
        fullScreenButton.setGraphic(imageView);
        fullScreenButton.setAlignment(Pos.CENTER);
        fullScreenButton.setOnAction(event -> {
            Scene scene = getScene();
            stage = (Stage)scene.getWindow();
            if(!stage.isFullScreen())
                stage.setFullScreen(true);
            else {
                stage.setFullScreen(false);
                if(pane.getChildren().contains(mediaView))
                    pane.getChildren().remove(mediaView);
            }
        });
        mediaBar.getChildren().add(fullScreenButton);

        setBottom(mediaBar);
        
    }
    
    private void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(() -> {
                Duration currentTime = mp.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                timeSlider.setDisable(duration.isUnknown());
                if (!timeSlider.isDisabled()
                        && duration.greaterThan(Duration.ZERO)
                        && !timeSlider.isValueChanging()) {
                    timeSlider.setValue(currentTime.divide(duration).toMillis()
                            * 100.0);
                }
                if (!volumeSlider.isValueChanging()) {
                    volumeSlider.setValue((int) Math.round(mp.getVolume()
                            * 100));
                }
            });
        }
    }

    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        int temp = intElapsed;
        if (elapsedHours > 0) {
            temp -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = temp / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            temp = intDuration;
            if (durationHours > 0) {
                temp -= durationHours * 60 * 60;
            }
            int durationMinutes = temp / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
}