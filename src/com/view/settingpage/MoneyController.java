package com.view.settingpage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.util.JsonLoader;
import com.view.RootLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.JSONException;
import org.json.JSONObject;

/* *  this class is used for money page controller .
 * @author PennaLia
 * @date 2018/6/12 21:59
 * @version Player Version 1.0
 */
public class MoneyController {

    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public Label month;
    @FXML
    private JFXButton pay;
    @FXML
    private AnchorPane moneyuppane;

    @FXML
    private StackPane stackpane;
    @FXML
    private JFXRadioButton wechatpay;

    @FXML
    private JFXRadioButton alipay;

    @FXML
    private JFXRadioButton visapay;

    @FXML
    private Label vip;
    //the warning information.
    String WaringTitle = "祝贺";
    String WaringMessage = "恭喜你喜提会员";
    String WaringButton = "知道了";
    private RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public AnchorPane getMoneyuppane() {
        return moneyuppane;
    }

    /* *  this used to handle be hui yuan ,that you are be a huiyuan of our product if you pay money,
     * @author PennaLia
     * @date 2018/6/12 22:00
     * @param
     * @return
     */
    @FXML
    public void handleBeHuiYuan() {
        rootLayoutController.getDrawerContentController().beHuiyuan();
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
        pay.setStyle("-fx-background-color: #A9A9A9;" +
                "    -fx-font-color: #000000;");

    }

    /* *  this is used for load language
     * @author PennaLia
     * @date 2018/6/12 22:01
     * @param  language means different language .
     * @return
     */
    public void loadLanguage(String language) {
        JSONObject jsonObject = JsonLoader.getJsonValue(language, "money");
        try {
            assert jsonObject != null;
            label1.setText(jsonObject.getString("label1"));
            label2.setText(jsonObject.getString("label2"));
            label3.setText(jsonObject.getString("label3"));
            label4.setText(jsonObject.getString("label4"));
            label5.setText(jsonObject.getString("label5"));
            pay.setText(jsonObject.getString("pay"));
            month.setText(jsonObject.getString("month"));
            vip.setText(jsonObject.getString("vip1"));
            setWaringText(language);
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* *  this used for set warning text .
     * @author PennaLia
     * @date 2018/6/12 22:01
     * @param
     * @return
     */
    public void setWaringText(String language) {
        if (language.equals("cn")) {
            WaringTitle = "祝贺";
            WaringMessage = "恭喜你喜提会员";
            WaringButton = "知道了";
        } else if (language.equals("en")) {
            WaringTitle = "Congratulations";
            WaringMessage = "You are VIP now";
            WaringButton = "OK, I konw";
        } else if (language.equals("fr")) {
            WaringTitle = "Félicitations";
            WaringMessage = "Vous êtes maintenant notre membre";
            WaringButton = "Je sais";
        }
    }


}
