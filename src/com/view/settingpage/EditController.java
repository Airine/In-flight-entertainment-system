package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;


public class EditController {
    @FXML
    private JFXButton choosefile;
    @FXML
    private Label name;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private Label modify;
    @FXML
    private Label ps;
    @FXML
    private JFXTextField textname;
    @FXML
    private AnchorPane edituppane;
    @FXML
    private JFXTextField textsign;
    @FXML
    private StackPane stackpane;

    String imageURL;

    public JFXTextField getTextname(){return  textname;}
    public JFXTextField getTextsign(){return  textsign;}
    public AnchorPane getEdituppane(){
        return edituppane;
    }
    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    @FXML
    public void handleEdit(){
        rootLayoutController.getDrawerContentController().changNameAndSign(
                textname.getText(),textsign.getText()
        );
        if(imageURL!=null)
            rootLayoutController.getDrawerContentController().changeUserImage(imageURL);
    }
    @FXML
    public void handleChooseFile() throws MalformedURLException {
        FileChooser fc = new FileChooser();
        configureFileChooser(fc);
        File seletedFile =fc.showOpenDialog(null);
        if(seletedFile!=null){
            imageURL=seletedFile.toURI().toURL().toExternalForm();//加载出文件的路径
        }else {
            stackpane.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Wrong"));
            content.setBody(new Text("You did not choose any file"));
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("I know");
            button.setOnAction(event -> {
                stackpane.setVisible(false);
                dialog.close();
            });
            content.setActions(button);
            dialog.show();
            imageURL = null;
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/resources/user_icon"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"edit");
        try {
            assert jsonObject != null;
            name.setText(jsonObject.getString("name"));
            ps.setText(jsonObject.getString("ps"));
            confirmButton.setText(jsonObject.getString("confirm"));
            modify.setText(jsonObject.getString("modify1"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
