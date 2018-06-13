package com.view.settingpage;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.util.JsonLoader;
import com.util.WebScraping;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.json.JSONException;
import org.json.JSONObject;

import static com.util.DataLoader.loadMovieTypes;
import static com.util.DataLoader.loadMovies;

/* *  this is used to control setting page .
 * @author PennaLia
 * @date 2018/6/12 22:03
 * @version Player Version 1.0
 */
public class SettingPageController {
    @FXML
    private JFXComboBox<Label> langComBoix;

    @FXML
    private AnchorPane settinguppane;

    @FXML
    private Label title;

    @FXML
    private Label language1;

    @FXML
    private Label scrap;

    @FXML
    private Label connect;

    @FXML
    private JFXCheckBox setdefault;

    @FXML
    private JFXButton sure;

    public JFXButton getWebScrapingButton() {
        return webScrapingButton;
    }

    @FXML
    private JFXButton webScrapingButton;
    private RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public AnchorPane getSettinguppane() {
        return settinguppane;
    }

    Label chinese = new Label("中文");
    Label english = new Label("English");
    Label français = new Label("Français");

    @FXML
    private void initialize() {
        initCombox();
        if (MainApp.mainUser == null || MainApp.mainUser.getIfAdmin() != 1) {
            webScrapingButton.setDisable(true);
        }
    }

    /* *  set different language for the app.
     * @author PennaLia
     * @date 2018/6/12 22:03
     * @param
     * @return
     */
    private void initCombox() {
        langComBoix.getItems().addAll(chinese, english, français);
        langComBoix.setEditable(false);
        langComBoix.setPromptText("选择语言");
        langComBoix.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object == null ? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });
        //选择box做什么
        langComBoix.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getText().equals("中文"))
                rootLayoutController.getMainApp().changeLanguage("cn");
            if (newValue.getText().equals("English"))
                rootLayoutController.getMainApp().changeLanguage("en");
            if (newValue.getText().equals("Français"))
                rootLayoutController.getMainApp().changeLanguage("fr");
        });


    }

    /* *  to scrap movie from web.
     * @author PennaLia
     * @date 2018/6/12 22:05
     * @param
     * @return
     */
    @FXML
    private void handleWebScraping() {
        com.util.WebScraping webScraping = new WebScraping();
        webScraping.generateJson();
        webScraping.updateMovieDataBase();
        MainApp.mainMovies = loadMovies();
        MainApp.mainMovieTypes = loadMovieTypes("en");
    }


    /* *  load language from json.
     * @author PennaLia
     * @date 2018/6/12 22:04
     * @param
     * @return
     */
    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "setting");
        try {
            assert jsonObject != null;
            title.setText(jsonObject.getString("title"));
            language1.setText(jsonObject.getString("lan"));
            scrap.setText(jsonObject.getString("scrap"));
            webScrapingButton.setText(jsonObject.getString("scrapbutton"));
            connect.setText(jsonObject.getString("connect"));
            setdefault.setText(jsonObject.getString("setdefault"));
            sure.setText(jsonObject.getString("sure"));
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
