package com.view.settingpage;

import com.jfoenix.controls.*;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

/* *   this is the about us controller calss to control everything in about us
 * @author PennaLia
 * @date 2018/6/12 20:43
 * @version Player Version 1.0
 */
public class AboutUsController {
    //the pane for a new dialog
    @FXML
    private StackPane stackpane;
    // the component
    public Label title;
    public JFXButton comment;
    public Label commentTo;
    public JFXButton checkVersion;
    public Label version;
    @FXML
    private Label version1;
    @FXML
    private AnchorPane aboutusuppane;
    @FXML
    private JFXChipView<?> pingjia;
    @FXML
    private Label workshop;
    @FXML
    private Label workplace;

    //waring message
    String WaringTitle = "当前版本";
    String WaringMessage = "当前已经是最新版本";
    String WaringButton = "我知道了";

    //the rootlayoutcontroller used to connect other pane.
    private RootLayoutController rootLayoutController;

    //get and set method
    public AnchorPane getAboutusuppane() {
        return aboutusuppane;
    }

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    /* *  used to initialize something in the beginning.
     * @author PennaLia
     * @date 2018/6/12 20:47
     * @param
     * @return
     */
    @FXML
    private void initialize() {
        initPingJia();
    }

    /* *  set different language for waringtext message.
     * @author PennaLia
     * @date 2018/6/12 20:48
     * @param
     * @return
     */
    public void setWaringText(String language) {
        if (language.equals("cn")) {
            WaringTitle = "当前版本";
            WaringMessage = "当前已经是最新版本";
            WaringButton = "我知道了";
        } else if (language.equals("en")) {
            WaringTitle = "Current version";
            WaringMessage = "Currently is the latest version";
            WaringButton = "OK, I konw";
        } else if (language.equals("fr")) {
            WaringTitle = "Version actuelle";
            WaringMessage = "Actuellement la dernière version";
            WaringButton = "Je sais";
        }
    }

    /* *  when you  click update, it will check the application version .
     * @author PennaLia
     * @date 2018/6/12 20:48
     * @param
     * @return
     */
    @FXML
    public void handleCheckUpdata() {
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
    }

    /* *  connect the comment to sql.
     * @author PennaLia
     * @date 2018/6/12 20:49
     * @param
     * @return
     */
    @FXML
    public void handleCommitComment() {

    }

    /* *  change the language.
     * @author PennaLia
     * @date 2018/6/12 20:49
     * @param  language indicate which language we want to use.
     * @return
     */
    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "about");
        try {
            assert jsonObject != null;
            title.setText(jsonObject.getString("title"));
            comment.setText(jsonObject.getString("comment"));
            commentTo.setText(jsonObject.getString("commentTo"));
            checkVersion.setText(jsonObject.getString("checkVersion"));
            version.setText(jsonObject.getString("version"));
            version1.setText(jsonObject.getString("version1"));
            workshop.setText(jsonObject.getString("workshop"));
            workplace.setText(jsonObject.getString("workplace"));
            setWaringText(language);
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* *  change the button text size.
     * @author PennaLia
     * @date 2018/6/12 20:50
     * @param
     * @return
     */
    public void initPingJia() {
        pingjia.setStyle("-fx-font-size:17px;");
    }
}
