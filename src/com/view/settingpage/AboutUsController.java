package com.view.settingpage;

import com.jfoenix.controls.*;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

public class AboutUsController {
    @FXML
    private StackPane stackpane;


    public Label title;
    public JFXButton comment;
    public Label commentTo;
    public JFXButton checkVersion;
    public Label version;

    @FXML
    private AnchorPane aboutusuppane;

    @FXML
    private JFXChipView<?> pingjia;
    public AnchorPane getAboutusuppane(){
        return aboutusuppane;
    }
    String WaringTitle="当前版本";
    String WaringMessage="当前已经是最新版本";
    String WaringButton="我知道了";

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    @FXML
    private void initialize() {
        initPingJia();
    }
    public void setWaringText(String language){
        if (language.equals("cn")){
            WaringTitle="当前版本";
            WaringMessage="当前已经是最新版本";
            WaringButton="我知道了";
        }else if(language.equals("en")){
            WaringTitle="Current version";
            WaringMessage="Currently is the latest version";
            WaringButton="OK, I konw";
        }else if(language.equals("fr")){
            WaringTitle="Version actuelle";
            WaringMessage="Actuellement la dernière version";
            WaringButton="Je sais";
        }
    }


    @FXML
    public void handleCheckUpdata(){
        stackpane.setVisible(true);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(WaringTitle));
        content.setBody(new Text(WaringMessage));
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton(WaringButton);
        button.setOnAction(event -> {
            stackpane.setVisible(false);
            dialog.close();
        });
        content.setActions(button);
        dialog.show();
    }
    @FXML
    public void handleCommitComment(){

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

    public void initPingJia(){
        pingjia.setStyle("-fx-font-size:17px;");
    }
}
