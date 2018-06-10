package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;


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

    public void handleEdit(){
        rootLayoutController.getDrawerContentController().changNameAndSign(
                textname.getText(),textsign.getText()
        );
    }
    @FXML
    public void handleChooseFile(){
        FileChooser fc = new FileChooser();
        configureFileChooser(fc);
        File seletedFile =fc.showOpenDialog(null);
        if(seletedFile!=null){
            imageURL=seletedFile.toURI().toString().substring(5);//加载出文件的路径，绝对路径
            System.out.print(imageURL);
            rootLayoutController.getDrawerContentController().changeUserImage(imageURL);//但是这个加载好像重复了就不行"resources/shiyuan.png"
        }else {
            ButtonType close = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You didn't choose any file",close);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setX(580);
            alert.setY(280);
            DialogPane pane = alert.getDialogPane();
            pane.setGraphic(null);
            pane.setPrefSize(200,80);
            alert.showAndWait();
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/resources/"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    @FXML
    private void handleConfirm(){


    }

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"edit");
        try {
            assert jsonObject != null;
            name.setText(jsonObject.getString("name"));
            ps.setText(jsonObject.getString("ps"));
            confirmButton.setText(jsonObject.getString("confirm"));
            modify.setText(jsonObject.getString("modify"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
