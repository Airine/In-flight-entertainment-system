package com.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class MoviePlayer extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ArrayList<String> idList = new ArrayList<>();
            idList.add("SqAOgoZHEprCsviT0xJXybgrYc0.");
            idList.add("ez-CW_dTCfyjhzXh9-SbvWlFZrM.");
            com.model.WebScraping webScraping = new com.model.WebScraping();
            Map<String, String> URL_Title = webScraping.scrapeLink(idList);
            Set<String> urls = URL_Title.keySet();
            String url = (String) (urls.toArray())[0];
            String tmp = url.substring(url.indexOf("com/") + 4);
            Media media = new Media("https://m500.ku6.com/"+
                    URLEncoder.encode(tmp,"UTF-8").replace("+","%20"));


            // create media player
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            com.model.MovieControl movieControl = new com.model.MovieControl(mediaPlayer);
            Scene scene = new Scene(movieControl,1000,680);
            primaryStage.setTitle("Embedded Media Player");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setFullScreenExitHint("Press ESC to exit\nPress the PLAY button to replay.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

