package com.view.viewModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import javafx.scene.layout.*;

public class MovieSortItem extends StackPane {

    private static final double width = 200;
    private static final double height = 112;

    private JFXButton button;

    public MovieSortItem(String language, String type) {
        String url = "resources/movie_type/" + language + "/" + type + ".png";
        this.setPrefWidth(width);
        this.setPrefHeight(height);

        JFXDepthManager.setDepth(this, 1);

        // create content
        JFXButton button = new JFXButton("");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setPrefSize(2 * width, 2 * height);
        button.setStyle(
                "-fx-background-radius: 5;" +
                        "-fx-background-image: url(\"" + url + "\");" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-size: 100% 100%;"
        );

        VBox.setVgrow(button, Priority.ALWAYS);
        VBox content = new VBox();

        button.setOnMousePressed(event -> button.setButtonType(JFXButton.ButtonType.FLAT));
        button.setOnMouseReleased(event -> button.setButtonType(JFXButton.ButtonType.RAISED));
        this.button = button;

        content.getChildren().addAll(button);

        this.getChildren().addAll(content);
        this.setStyle("-fx-padding: 15px 0px 0px 15px;");
    }

    public JFXButton getButton() {
        return button;
    }
}
