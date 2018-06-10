package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXSpinner;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

public class AboutUsController {

    public Label title;
    public JFXButton comment;
    public Label commentTo;
    public JFXButton checkVersion;
    public Label version;
    @FXML
    private JFXSpinner spinner;

    @FXML
    private AnchorPane aboutusuppane;

    @FXML
    private JFXChipView<?> pingjia;
    public AnchorPane getAboutusuppane(){
        return aboutusuppane;
    }

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    @FXML
    private void initialize() {
    }

    private void initSpinner(){

    }

    @FXML
    public void handleCheckUpdata(){
        spinner.setVisible(true);

    }

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"about");
        try {
            assert jsonObject != null;
            title.setText(jsonObject.getString("title"));
            comment.setText(jsonObject.getString("comment"));
            commentTo.setText(jsonObject.getString("commentTo"));
            checkVersion.setText(jsonObject.getString("checkVersion"));
            version.setText(jsonObject.getString("version"));

        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
