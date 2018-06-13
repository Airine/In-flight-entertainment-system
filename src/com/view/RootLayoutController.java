package com.view;

import com.MainApp;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.util.VirtualKeyboard;
import com.view.settingpage.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.IOException;

/* * this is a controller for the root, that initialize and contain all other controller.
 * @author PennaLia
 * @date 2018/6/12 20:33
 * @version Player Version 1.0
 */
public class RootLayoutController {
    //main pane. the bigger pane.
    @FXML
    private AnchorPane mainPane;
    //the bar
    @FXML
    private AnchorPane mainBar;
    //search box
    @FXML
    private HBox searchbox;
    //search text field
    @FXML
    private JFXTextField searchfeild;
    @FXML
    private StackPane stackpane;//dialog pane.

    //three line to indicate the current page.
    @FXML
    private Line homeline;

    @FXML
    private Line playline;

    @FXML
    private Line musicline;

    //homepage for place movies
    @FXML
    private AnchorPane homepage;
    //button to open or close drawer
    @FXML
    private JFXHamburger hamburger;
    //drawer
    @FXML
    private JFXDrawer drawer;
    //setting pane
    @FXML
    private AnchorPane settingPane;
    //search pane
    @FXML
    private AnchorPane searchpane;
    //key board.
    @FXML
    private AnchorPane keyboard;
    // the mainapp
    private MainApp mainApp;
    // hamburger transition
    private HamburgerNextArrowBasicTransition transition;

    //box used to place drawer
    private VBox box;
    //three main page
    public AnchorPane homeP, musicP, playerP;
    //three main page controller
    public MusicPageController musicPageController;
    public HomePageController homePageController;
    public PlayerPageController playerPageController;

    //setting pane
    public AnchorPane setting, aboutus, theme, money, timing, edit;
    public FlowPane collection;//collection pane.
    //setting pane controller
    SettingPageController settingPageController;
    AboutUsController aboutUsController;
    ThemeController themeController;
    MoneyController moneyController;
    TimingController timingController;
    CollectionController collectionController;
    EditController editController;
    DrawerContentController drawerContentController;
    //search ontroler
    MovieTableViewController movieTableViewController;


    //information about waring text.
    String waringTitle = "程序即将自动关闭";
    String waringMessage = "程序会在10s内自动关闭";
    String waringButton = "我不想要关闭";

    /* *  this is used to load different language.
     * @author PennaLia
     * @date 2018/6/12 18:53
     * @param  language  the language that will want to change.
     * @return
     */
    public void loadLanguage(String language) {
        settingPageController.loadLanguage(language);
        aboutUsController.loadLanguage(language);
        themeController.loadLanguage(language);
        moneyController.loadLanguage(language);
        timingController.loadLanguage(language);
        editController.loadLanguage(language);
        drawerContentController.loadLanguage(language);
        musicPageController.loadLanguage(language);
        setWaringText(language);

    }


    public void setMainApp(MainApp app) {
        mainApp = app;
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setVBox(VBox box) {
        this.box = box;
    }

    public JFXTextField getSearchFeild() {
        return searchfeild;
    }

    public MovieTableViewController getMovieTableViewController() {
        return movieTableViewController;
    }


    public JFXDrawer getDrawer() {
        return drawer;
    }

    public DrawerContentController getDrawerContentController() {
        return drawerContentController;
    }

    /* *  用来替换主界面，当想切换哪个界面的时候就把界面赋值给homepage
     * @author PennaLia
     * @date 2018/6/5 14:18
     * @param
     * @return
     */
    public void setHomePage(AnchorPane page) {
        homepage.getChildren().setAll(page);
    }

    /* *  用来替换设置界面
     * @author PennaLia
     * @date 2018/6/5 14:19
     * @param
     * @return
     */
    public void setSettingPane(AnchorPane pane) {
        settingPane.getChildren().setAll(pane);
    }

    public void setSettingVisible() {
        getSettingPane().setVisible(true);
    }

    public void setKeyboardVisible(boolean b) {
        keyboard.setVisible(b);
    }

    public void homePageNotSee() {
        homepage.setVisible(false);
    }

    public void homePageSee() {
        homepage.setVisible(true);
    }

    public AnchorPane getHomepage() {
        return homepage;
    }

    public AnchorPane getSettingPane() {
        return settingPane;
    }

    public AnchorPane getMainBar() {
        return mainBar;
    }

    public void searchPaneVisible(boolean a) {
        searchpane.setVisible(a);
    }


    /* *  initi all
     * @author PennaLia
     * @date 2018/6/5 14:20
     * @param
     * @return
     */
    @FXML
    private void initialize() {
        try {
            transition = new HamburgerNextArrowBasicTransition(hamburger);
            transition.setRate(-1);

            //加载
            initDrawerContent();
            initHomePage();
            //只是预加载
            initMusicPage();
            initPlayerPage();
            //设置界面的预加载
            initSettingPage();
            initAboutUs();
            initTheme();
            initMoney();
            initTiming();
            initEdit();
            initSearchTree();
            initCollection();
            initKeyboard();
            now = defaultCSS;

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void initKeyboard() {
        VirtualKeyboard board = new VirtualKeyboard();
        keyboard.getChildren().add(board.view());

    }

    /* *  init draer.
     * @author PennaLia
     * @date 2018/5/29 1:08
     * @param
     * @return
     */
    public void initDrawerContent() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/DrawerContent.fxml"));
            this.setVBox(loader.load());
            drawerContentController = loader.getController();
            drawerContentController.setRootLayoutController(this);
            drawer.setSidePane(box);//把box设置给抽屉
            drawer.setVisible(false);//一开始抽屉栏不可见，否则会遮挡
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init homepage.
     * @author PennaLia
     * @date 2018/5/29 1:18
     * @param
     * @return
     */
    public void initHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/HomePage.fxml"));
            homeP = loader.load();
            homePageController = loader.getController();
            homePageController.setRootLayoutController(this);
            setHomePage(homeP);
            homeline.setVisible(true);//一开始就是可见的
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* * initi music page .
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initMusicPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MusicPage.fxml"));
            musicP = loader.load();
            musicPageController = loader.getController();
            musicPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init player page .
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initPlayerPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PlayerPage.fxml"));
            playerP = loader.load();
            playerPageController = loader.getController();
            MainApp.setPlayer(playerPageController);
            playerPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init setting page.
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initSettingPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/SettingPage.fxml"));
            setting = loader.load();
            settingPageController = loader.getController();
            settingPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init aboutus page.
     * @author PennaLia
     * @date 2018/6/10 17:59
     * @param
     * @return
     */
    public void initAboutUs() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/AboutUs.fxml"));
            aboutus = loader.load();
            aboutUsController = loader.getController();
            aboutUsController.setRootLayoutController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init theme page.
     * @author PennaLia
     * @date 2018/6/10 17:59
     * @param
     * @return
     */
    public void initTheme() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Theme.fxml"));
            theme = loader.load();
            themeController = loader.getController();
            themeController.setRootLayoutController(this);
            //这边要想办法得到部件的颜色信息
            themeController.getLeftbarUp().setValue(Color.web("#F08080"));
            themeController.getLeftbar().setValue(Color.web("#ee5253"));
            themeController.getUpbar().setValue(Color.web("C62F2F"));
            themeController.bindColorPicker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init money page.
     * @author PennaLia
     * @date 2018/6/10 17:59
     * @param
     * @return
     */
    public void initMoney() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Money.fxml"));
            money = loader.load();
            moneyController = loader.getController();
            moneyController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init timing close page.
     * @author PennaLia
     * @date 2018/6/10 17:58
     * @param
     * @return
     */
    public void initTiming() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Timing.fxml"));
            timing = loader.load();
            timingController = loader.getController();
            timingController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  initial collection page.
     * @author PennaLia
     * @date 2018/6/10 17:58
     * @param
     * @return
     */
    public void initCollection() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Collection.fxml"));
            collection = loader.load();
            collectionController = loader.getController();
            collectionController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  initial edit page.
     * @author PennaLia
     * @date 2018/6/10 17:57
     * @param
     * @return
     */
    public void initEdit() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Edit.fxml"));
            edit = loader.load();
            editController = loader.getController();
            editController.setRootLayoutController(this);
            //界面初始化用户信息
            editController.getTextname().setText(this.getDrawerContentController().getUserName());
            editController.getTextsign().setText(this.getDrawerContentController().getUserSign());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  init the search tree used for search.
     * @author PennaLia
     * @date 2018/6/12 18:43
     * @param
     * @return
     */
    public void initSearchTree() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MovieTableView.fxml"));
            AnchorPane pane = loader.load();
            searchpane.getChildren().addAll(pane);
            movieTableViewController = loader.getController();
            movieTableViewController.setRootLayoutController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  this is use to open drawer.
     * @author PennaLia
     * @date 2018/6/5 14:24
     * @param
     * @return
     */
    @FXML
    private void handlehamburger() {
        transition.setRate(transition.getRate() * -1);
        transition.play();
        if (drawer.isOpened()) {
            drawer.close();
            homePageSee();
            drawer.setVisible(false);
            getSettingPane().setVisible(false);
        } else {
            drawer.setVisible(true);
            drawer.open();
        }
    }

    /* * see the line in homepage..
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    public void seeHomepage() {
        setHomePage(homeP);
        musicline.setVisible(false);
        playline.setVisible(false);
        homeline.setVisible(true);
    }

    /* *  see the line in playpage..
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    public void seePlaypage() {
        setHomePage(playerP);
        musicline.setVisible(false);
        playline.setVisible(true);
        homeline.setVisible(false);

    }

    /* *  see the line in musicpage.
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    public void seeMusicpage() {
        setHomePage(musicP);
        musicline.setVisible(true);
        playline.setVisible(false);
        homeline.setVisible(false);
    }

    /* * close the windows
     * @author PennaLia
     * @date 2018/6/5 14:26
     * @param
     * @return
     */
    @FXML
    private void handleClose() {
        mainApp.closeWindows();
    }

    /* *  when you click enter it will search.
     * @author PennaLia
     * @date 2018/6/12 18:44
     * @param
     * @return
     */
    @FXML
    private void handleSearch(KeyEvent event) {
        KeyCode kc = event.getCode();
        if (kc == (KeyCode.ENTER)) {
            searchPaneVisible(true);
        }
    }

    /* *  this is used for hanlde search
     * @author PennaLia
     * @date 2018/6/10 17:54
     * @param
     * @return
     */
    @FXML
    private void handleSearchClick() {
        searchPaneVisible(true);
        keyboard.setVisible(true);
    }


    public String DarkCSS = this.getClass().getResource("DarkTheme.css").toExternalForm(),
            defaultCSS = this.getClass().getResource("DefaultTheme.css").toExternalForm(),
            Theme1 = this.getClass().getResource("Theme1.css").toExternalForm(),
            Theme2 = this.getClass().getResource("Theme2.css").toExternalForm(),
            Theme3 = this.getClass().getResource("Theme3.css").toExternalForm();
    public String now;

    /* * this set the theme to night
     * @author PennaLia
     * @date 2018/6/10 17:48
     * @param
     * @return
     */
    public void toNight() {
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.clear();
        if (!styleSheets.contains(DarkCSS)) {
            styleSheets.add(DarkCSS);
        }
    }

    /* *  this set theme to default.
     * @author PennaLia
     * @date 2018/6/10 17:48
     * @param
     * @return
     */
    public void toDefault() {
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.clear();
        if (!styleSheets.contains(defaultCSS)) {
            styleSheets.add(defaultCSS);
            now = defaultCSS;
        }
    }

    /* *  this will change theme to theme1.
     * @author PennaLia
     * @date 2018/6/12 18:44
     * @param
     * @return
     */
    public void toTheme1() {
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.clear();
        if (!styleSheets.contains(Theme1)) {
            styleSheets.add(Theme1);
            now = Theme1;
        }
    }

    /* *  this will change theme to theme2.
     * @author PennaLia
     * @date 2018/6/12 18:45
     * @param
     * @return
     */
    public void toTheme2() {
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.clear();
        if (!styleSheets.contains(Theme2)) {
            styleSheets.add(Theme2);
            now = Theme2;
        }
    }

    /* *  this will change theme to theme3
     * @author PennaLia
     * @date 2018/6/12 18:45
     * @param
     * @return
     */
    public void toTheme3() {
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.clear();
        if (!styleSheets.contains(Theme3)) {
            styleSheets.add(Theme3);
            now = Theme3;
        }
    }

    /* *  this change the left bar value
     * @author PennaLia
     * @date 2018/6/10 17:49
     * @param
     * @return
     */
    public void changeLeftColor(String color) {
        drawerContentController.getUserBackPane().setStyle("-fx-background-color:" + "#" + color);
        aboutUsController.getAboutusuppane().setStyle("-fx-background-color:" + "#" + color);
        editController.getEdituppane().setStyle("-fx-background-color:" + "#" + color);
        moneyController.getMoneyuppane().setStyle("-fx-background-color:" + "#" + color);
        settingPageController.getSettinguppane().setStyle("-fx-background-color:" + "#" + color);
        themeController.getThemeuppane().setStyle("-fx-background-color:" + "#" + color);
        timingController.getTiminguppane().setStyle("-fx-background-color:" + "#" + color);
        musicPageController.getMusicPane().setStyle("-fx-background-color:" + "#" + color);
    }

    /* *  this is use for close app automatically
     * @author PennaLia
     * @date 2018/6/10 17:49
     * @param
     * @return
     */
    public void timerOutClose() {
        stackpane.setVisible(true);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(waringTitle));
        content.setBody(new Text(waringMessage));
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton(waringButton);
        button.setOnAction(event -> {
            stackpane.setVisible(false);
            timingController.timer.stop();
            timingController.waitForClose = false;
            dialog.close();
        });
        content.setActions(button);
        dialog.show();
    }

    /* *  set warning message for diffenet language.
     * @author PennaLia
     * @date 2018/6/12 18:45
     * @param
     * @return
     */
    private void setWaringText(String language) {
        if (language.equals("cn")) {
            waringTitle = "程序即将自动关闭";
            waringMessage = "程序会在10s内自动关闭";
            waringButton = "我不想要关闭";
        } else if (language.equals("en")) {
            waringTitle = "It will automatically close soon";
            waringMessage = "The program will automatically shut down within 10s";
            waringButton = "I don't want to close it";
        } else if (language.equals("fr")) {
            waringTitle = "Le programme s'éteindra automatiquement";
            waringMessage = "Le programme s'éteindra automatiquement dans les 10s";
            waringButton = "Je ne veux pas fermer";
        }
    }


}
