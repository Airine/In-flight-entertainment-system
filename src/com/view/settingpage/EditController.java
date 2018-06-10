package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

import java.io.File;

public class EditController {
    @FXML
    private JFXButton choosefile;

    @FXML
    private JFXTextField textname;

    @FXML
    private AnchorPane edituppane;

    @FXML
    private JFXTextField textsign;

    @FXML
    private JFXButton confirmButton;

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
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/resources/avatar"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    @FXML
    private void handleConfirm(){
        
        
    }

}
