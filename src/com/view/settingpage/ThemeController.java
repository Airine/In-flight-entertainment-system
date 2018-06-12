package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

import javax.jws.soap.SOAPBinding;
import java.time.Duration;

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
    public JFXColorPicker getLeftbar(){
        return  leftbar;
    }
    public JFXColorPicker getUpbar(){
        return  upbar;
    }
    public JFXColorPicker getLeftbarUp(){
        return leftbarUp;
    }

    public AnchorPane getThemeuppane() {
        return themeuppane;
    }
    String WaringTitle="你没有开通这个功能";
    String WaringMessage="这个问题你充钱就能解决";
    String WaringButton="行";
    /* *  this is used to set waring text for different language.
     * @author PennaLia
     * @date 2018/6/12 10:27
     * @param
     * @return
     */
    public void setWaringText(String language){
        if (language.equals("cn")){
            WaringTitle="你没有开通这个功能";
            WaringMessage="这个问题你充钱就能解决";
            WaringButton="行";
        }else if(language.equals("en")){
            WaringTitle="You don't have the authority.";
            WaringMessage="You need to pay for VIP";
            WaringButton="OK, I konw";
        }else if(language.equals("fr")){
            WaringTitle="Vous n'avez pas ouvert cette fonction";
            WaringMessage="Vous devriez acheter vip pour résoudre ce problème";
            WaringButton="Bon";
        }
    }
    /* *   initialize everything.
     * @author PennaLia
     * @date 2018/6/12 10:31
     * @param
     * @return
     */
    @FXML
    private void initialize(){

    }

    /* *  this is used for changing the theme to default.
     * @author PennaLia
     * @date 2018/6/12 10:29
     * @param
     * @return
     */
    @FXML
    private void handlrToDefault(){
        rootLayoutController.ToDefault();
    }
    /* *  this is used for changing the theme to theme1.
     * @author PennaLia
     * @date 2018/6/12 10:30
     * @param
     * @return
     */
    @FXML
    private void handleTheme1(){
        rootLayoutController.ToTheme1();
    }
    /* *  this is used for changing the theme to theme2.
     * @author PennaLia
     * @date 2018/6/12 10:30
     * @param
     * @return
     */
    @FXML
    private void handleTheme2(){
        rootLayoutController.ToTheme2();
    }
    /* *  this is used for changing the theme to theme3.
     * @author PennaLia
     * @date 2018/6/12 10:30
     * @param
     * @return
     */
    @FXML
    private void handleTheme3(){
        rootLayoutController.ToTheme3();
    }

    /* *  after you click that, it will change the theme color.
     * @author PennaLia
     * @date 2018/6/12 10:28
     * @param
     * @return
     */
    public void handleOk() {
        if (!rootLayoutController.getMainApp().huiyuan) {
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
    /* *  whenever the pane of we want to change changing its color, the color picker will change it text value.
     * @author PennaLia
     * @date 2018/6/12 10:28
     * @param
     * @return
     */
    public void bindColorPicker() {
        Pane UserBackPane=rootLayoutController.getDrawerContentController().getUserBackPane();
       UserBackPane.backgroundProperty().addListener((observable, oldValue, newValue) -> {
            if(UserBackPane.getBackground()==null){
                return;
            }
                Color color=(Color) UserBackPane.getBackground().getFills().get(0).getFill();
                leftbar.setValue(color);
       });
       Pane UserBackUpPane=rootLayoutController.getDrawerContentController().getUserBackUpPane();
        UserBackUpPane.backgroundProperty().addListener((observable, oldValue, newValue) -> {
            if(UserBackUpPane.getBackground()==null){
                return;
            }
            Color color=(Color) UserBackUpPane.getBackground().getFills().get(0).getFill();
            leftbarUp.setValue(color);
        });
        AnchorPane mainBar= rootLayoutController.getMainBar();
        mainBar.backgroundProperty().addListener((observable, oldValue, newValue) -> {
            if(mainBar.getBackground()==null){
                return;
            }
            Color color=(Color) mainBar.getBackground().getFills().get(0).getFill();
            upbar.setValue(color);
        });
    }

}
