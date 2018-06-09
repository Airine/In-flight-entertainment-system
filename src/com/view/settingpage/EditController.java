package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
       FileChooser fc=new FileChooser();
       File seletedFile =fc.showOpenDialog(null);
       if(seletedFile!=null){
          imageURL=seletedFile.toURI().toString().substring(5);//加载出文件的路径，绝对路径
          rootLayoutController.getDrawerContentController().changeUserImage("resources/shiyuan.png");//但是这个加载好像重复了就不行
       }else {
           System.out.println("你没有选择文件");
       }
   }


}
