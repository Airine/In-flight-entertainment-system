package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

public class ThemeController {

    @FXML
    private Label title;

    @FXML
    private Label upBar;

    @FXML
    private Label leftBar;

    @FXML
    private Label leftBarUp;

    @FXML
    private JFXButton button;

    @FXML
    private AnchorPane themeuppane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXColorPicker leftbar;

    @FXML
    private JFXColorPicker leftbarUp;

    @FXML
    private JFXColorPicker upbar;

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public JFXColorPicker getLeftbar(){return  leftbar;}
    public JFXColorPicker getUpbar(){return  upbar;}
    public JFXColorPicker getLeftbarUp() {return leftbarUp; }

    public AnchorPane getThemeuppane() {
        return themeuppane;
    }

    public void handleOk() {
        if (!rootLayoutController.getMainApp().huiyuan) {
            stackpane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("你太穷了"));
            content.setBody(new Text("这个问题你充钱就能解决"));
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("我知道了");
            button.setOnAction(event -> {
                stackpane.setVisible(false);
                dialog.close();
            });
            content.setActions(button);
            dialog.show();
        } else {
            String topBarValue=upbar.getValue().toString().substring(2,8);
            rootLayoutController.getMainBar().setStyle("-fx-background-color:"+"#"+topBarValue);
            String LeftValue=leftbar.getValue().toString().substring(2,8);
            rootLayoutController.changeLeftColor(LeftValue);

            String LeftUpValue=leftbarUp.getValue().toString().substring(2,8);
            rootLayoutController.getDrawerContentController().getUserBackUpPane().setStyle("-fx-background-color:"+"#"+LeftUpValue);
        }
    }

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"theme");
        try {
            assert jsonObject != null;
            title.setText(jsonObject.getString("title"));
            upBar.setText(jsonObject.getString("upBar"));
            leftBar.setText(jsonObject.getString("leftBarUp"));
            leftBarUp.setText(jsonObject.getString("leftBarUp"));
            button.setText(jsonObject.getString("button"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
