package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.User;
import com.util.DataUpdater;
import com.util.JsonLoader;
import javafx.fxml.FXML;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import static com.MainApp.mainUser;
import static com.util.DataLoader.getUser;
import static com.util.DataLoader.loadStarRelation;

/* *  this use to control login in .
 * @author PennaLia
 * @date 2018/6/12 23:42
 * @version Player Version 1.0
 */
public class LoginController {
    public JFXTextField user_name;
    public JFXPasswordField user_pw;

    public JFXButton login;
    public JFXButton sign;
    public Text user_name_warning;
    public Text user_pw_warning;
    private Stage dialogStage;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    @FXML
    private JFXButton close;

    @FXML
    private void initialize() {
    }

    /* *  load language file from json.
     * @author PennaLia
     * @date 2018/6/13 10:18
     * @param
     * @return
     */
    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "loginDialog");
        try {
            assert jsonObject != null;
            login.setText(jsonObject.getString("login"));
            sign.setText(jsonObject.getString("sign"));
            user_name.setPromptText(jsonObject.getString("username"));
            user_pw.setPromptText(jsonObject.getString("password"));
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* *  这个方法用来监听关闭，以便于可以再次代开login.
     * @author PennaLia
     * @date 2018/5/29 0:48
     * @param
     * @return
     */
    public void setCloseAction() {
        dialogStage.setOnCloseRequest(windowEvent -> {
            mainApp.setOpenLogin(false);
            mainApp.setLogin(false);
        });
    }

    /* *  for close the login page .
     * @author PennaLia
     * @date 2018/6/13 10:18
     * @param
     * @return
     */
    @FXML
    public void handleClose() {
        dialogStage.close();
        mainApp.setOpenLogin(false);
        mainApp.setLogin(false);
    }

    public void pressLoginButton(MouseEvent mouseEvent) {
        login.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void releaseLoginButton(MouseEvent mouseEvent) {
        login.setButtonType(JFXButton.ButtonType.RAISED);
    }

    /* *  this use to verify if  username and password are matched.
     * @author PennaLia
     * @date 2018/6/12 23:42
     * @param
     * @return
     */
    public void clickLoginButton(MouseEvent mouseEvent) {
        String user_nameText = user_name.getText();
        String user_pwText = user_pw.getText();
        User user = getUser(user_nameText);
        if (user == null) {
            user_name_warning.setText("Username not exists");
        } else {
            if (!user.verifyPassword(user_pwText)) {
                user_pw_warning.setText("Wrong password");
            } else {
                dialogStage.close();
                mainUser = user;
                loadStarRelation();
                if (mainUser.getIfVIP() == 1) {
                    mainApp.getRootLayoutController().getDrawerContentController().beHuiyuan();
                }
                if (mainUser.getIfAdmin() == 1) {
                    MainApp.admin = true;
                    mainApp.getRootLayoutController().settingPageController.getWebScrapingButton().setDisable(false);
                }
            }
        }
    }

    /* *  when you click the button, it will be flat.
     * @author PennaLia
     * @date 2018/6/13 10:18
     * @param
     * @return
     */
    public void pressSignButton(MouseEvent mouseEvent) {
        sign.setButtonType(JFXButton.ButtonType.FLAT);
    }

    /* *  when you release button , it will be raised.
     * @author PennaLia
     * @date 2018/6/13 10:19
     * @param
     * @return
     */
    public void releaseSignButton(MouseEvent mouseEvent) {
        sign.setButtonType(JFXButton.ButtonType.RAISED);
    }

    public void getUsernameChanged(InputMethodEvent inputMethodEvent) {
        String input = user_name.getText();

    }

    public void getPwChanged(InputMethodEvent inputMethodEvent) {
        user_pw_warning.setText("");
    }

    /* *  this used to listen the username text field .
     * @author PennaLia
     * @date 2018/6/12 23:43
     * @param
     * @return
     */
    public void getUsernameTyped(KeyEvent keyEvent) {
        String input = user_name.getText() + keyEvent.getCharacter();
//        System.err.println(input);
        if (getUser(input) != null) {
            sign.setButtonType(JFXButton.ButtonType.FLAT);
            sign.setDisable(true);
//            user_name_warning.setText("Username exists");
        } else {
            sign.setButtonType(JFXButton.ButtonType.RAISED);
            sign.setDisable(false);
            user_name_warning.setText("");
        }
    }

    public void getPwTyped(KeyEvent keyEvent) {
        user_pw_warning.setText("");
    }

    /* *  this used to sign a new account.
     * @author PennaLia
     * @date 2018/6/12 23:43
     * @param
     * @return
     */
    public void clickSignButton(MouseEvent mouseEvent) {
        String user_nameText = user_name.getText();
        String user_pwText = user_pw.getText();
        mainUser = DataUpdater.insertUser(user_nameText, user_pwText);
        dialogStage.close();
    }
}
