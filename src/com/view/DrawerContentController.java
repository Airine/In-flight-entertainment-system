package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.transitions.JFXFillTransition;
import com.model.Movie;
import com.util.JsonLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;

/* *  this is the drawer contreoller.
 * @author PennaLia
 * @date 2018/6/12 23:45
 * @version Player Version 1.0
 */
public class DrawerContentController {
    public JFXButton vip;
    public JFXButton star;
    public JFXButton timing;
    @FXML
    private VBox drawerBox;

    @FXML
    private Pane UserBackPane;

    @FXML
    private Pane UserBackUpPane;

    @FXML
    private ImageView userImage;

    @FXML
    private ImageView huiyuan;

    @FXML
    private JFXButton theme;

    @FXML
    private JFXButton user_icon;

    @FXML
    private JFXToggleButton night;

    @FXML
    private Label usersign;

    @FXML
    private Label username;

    @FXML
    private Label about;

    @FXML
    private Label nightLabel;

    private boolean clicked = false;
    private boolean Tonight = false;
    private RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public String getUserName() {
        return username.getText();
    }

    public String getUserSign() {
        return usersign.getText();
    }


    public Pane getUserBackPane() {
        return UserBackPane;
    }

    public Pane getUserBackUpPane() {
        return UserBackUpPane;
    }

    /* *  initialize user default icon , and every  component.
     * @author PennaLia
     * @date 2018/6/13 10:22
     * @param
     * @return
     */
    @FXML
    public void initialize() {
        String url = "resources/user_icon/shiyuan.png";
        user_icon.setStyle("-fx-background-image: url(\"" + url + "\");" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;");
        initToggle();//初始化他的监听拖动
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            rootLayoutController.editController.getTextname().setText(newValue);
        });
        usersign.textProperty().addListener((observable, oldValue, newValue) -> {
            rootLayoutController.editController.getTextsign().setText(newValue);
        });
    }

    /* *  used to set user.
     * @author PennaLia
     * @date 2018/6/13 10:23
     * @param
     * @return
     */
    public void setUser() {
        changNameAndSign(MainApp.mainUser.getNickName(), MainApp.mainUser.getStatus());
        changeUserImage(MainApp.mainUser.getIconUrl());
    }

    /* *  change user name and sign.
     * @author PennaLia
     * @date 2018/6/13 10:23
     * @param  name is the user name , sign is the user sign.
     * @return
     */
    public void changNameAndSign(String name, String sign) {
        username.setText(name);
        usersign.setText(sign);
    }

    /* * set main user name and sign.
     * @author PennaLia
     * @date 2018/6/13 10:24
     * @param
     * @return
     */
    public void setNameAndSign(String name, String sign) {
        MainApp.mainUser.setNickName(name);
        MainApp.mainUser.setStatus(sign);
    }

    /* *  change the user image .
     * @author PennaLia
     * @date 2018/6/13 10:24
     * @param
     * @return
     */
    public void changeUserImage(String url) {
        user_icon.setStyle("-fx-background-image: url(\"" + url + "\");" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;");
    }

    /* *  be huiyuan .
     * @author PennaLia
     * @date 2018/6/12 23:47
     * @param
     * @return
     */
    @FXML
    public void beHuiyuan() {
        if (MainApp.mainUser != null)
            MainApp.mainUser.setIfVIP(1);
        huiyuan.setImage(new Image("resources/icon/truehuiyuan.png"));
        MainApp.huiyuan = true;
    }

    /* *  show setting  page.
     * @author PennaLia
     * @date 2018/6/13 10:25
     * @param
     * @return
     */
    @FXML
    private void handleSetting() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.setting);
    }

    /* *  show about us page ,
     * @author PennaLia
     * @date 2018/6/13 10:25
     * @param
     * @return
     */
    @FXML
    private void handleAboutus() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.aboutus);
    }

    /* *  show theme page.
     * @author PennaLia
     * @date 2018/6/13 10:25
     * @param
     * @return
     */
    @FXML
    private void handleTheme() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.theme);
    }

    /* *  show moeny page .
     * @author PennaLia
     * @date 2018/6/13 10:26
     * @param
     * @return
     */
    @FXML
    private void handleMoney() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.money);
    }

    /* *  show timing close page.
     * @author PennaLia
     * @date 2018/6/13 10:26
     * @param
     * @return
     */
    @FXML
    private void handleTiming() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.timing);
    }

    /* *  collection your movies.
     * @author PennaLia
     * @date 2018/6/12 23:45
     * @param
     * @return
     */
    @FXML
    private void handleCollection() {
        rootLayoutController.getDrawer().close();
        rootLayoutController.getDrawer().setVisible(false);
        rootLayoutController.seeHomepage();
        rootLayoutController.homePageSee();
        rootLayoutController.getSettingPane().setVisible(false);
        FlowPane flowPane;
        flowPane = new FlowPane();
        for (Movie movie : MainApp.starMovies) {
            try {
//                StackPane movieItem = movie.getMi_cn();
                movie.initMI();
                StackPane movieItem = movie.getMi_cn();
//                StackPane movieItem = (language.equals("cn"))? movie.getMi_cn() : movie.getMi_en();
                flowPane.getChildren().add(movieItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        rootLayoutController.homePageController.changeFlowContent(flowPane);
        rootLayoutController.homePageController.back.setVisible(true);

    }

    /* * to show the edit page .
     * @author PennaLia
     * @date 2018/6/13 10:24
     * @param
     * @return
     */
    @FXML
    private void handleEdit() {
        rootLayoutController.setSettingVisible();//设置界面可见
        rootLayoutController.homePageNotSee();//主界面不可见
        rootLayoutController.setSettingPane(rootLayoutController.edit);
    }

    /* *  to show the login windows.
     * @author PennaLia
     * @date 2018/6/13 10:25
     * @param
     * @return
     */
    @FXML
    private void handleLogin() {
        rootLayoutController.getMainApp().login();
    }

    /* *  dadd a listener of toggle .
     * @author PennaLia
     * @date 2018/6/12 23:46
     * @param
     * @return
     */
    private void initToggle() {
        night.selectedProperty().addListener((observable, oldValue, newValue) -> {

            if (night.isSelected()) {
                rootLayoutController.toNight();
            } else {
                if (rootLayoutController.now.equals(rootLayoutController.defaultCSS)) {
                    rootLayoutController.toDefault();
                } else if (rootLayoutController.now.equals(rootLayoutController.Theme1)) {
                    rootLayoutController.toTheme1();
                } else if (rootLayoutController.now.equals(rootLayoutController.Theme2)) {
                    rootLayoutController.toTheme2();
                } else if (rootLayoutController.now.equals(rootLayoutController.Theme3)) {
                    rootLayoutController.toTheme3();
                }

            }
        });
    }


/*    @FXML
    private void handlebutton1() {
        AnchorPane homepage = rootLayoutController.getHomepage();
        JFXFillTransition transition = new JFXFillTransition();
        transition.setDuration(Duration.millis(5000));
        transition.setRegion(homepage);
        if (!clicked) {
            transition.setFromValue(Color.WHITE);
            transition.setToValue(Color.BLACK);
            transition.play();
            clicked = true;
        } else {
            transition.setFromValue(Color.PINK);
            transition.setToValue(Color.WHITE);
            transition.play();
            clicked = false;
        }
    }*/

    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "drawer");
        try {
            assert jsonObject != null;
            vip.setText(jsonObject.getString("vip"));
            star.setText(jsonObject.getString("star"));
            timing.setText(jsonObject.getString("timing"));
            theme.setText(jsonObject.getString("theme"));
            nightLabel.setText(jsonObject.getString("night"));
            about.setText(jsonObject.getString("about"));

        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
