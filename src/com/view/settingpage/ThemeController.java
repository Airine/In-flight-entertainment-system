package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ThemeController {

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
        if (rootLayoutController.getMainApp().huiyuan == false) {
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

}
