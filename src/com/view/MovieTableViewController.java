package com.view;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.model.Movie;
import com.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.util.function.Predicate;

public class MovieTableViewController {
    @FXML
    private JFXTreeTableView<User> treetable;

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public JFXTreeTableView getTreeTabla(){
        return  treetable;
    }
    @FXML
    private  void initialize(){
        JFXTreeTableColumn<User,String> name =new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return  param.getValue().getValue().name;
            }
        });
        JFXTreeTableColumn<User,Number> age =new JFXTreeTableColumn<>("Age");
        age.setPrefWidth(150);
        age.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<User, Number> param) {
                return  param.getValue().getValue().age;
            }
        });

        ObservableList<User>users =FXCollections.observableArrayList();
        users.add(new User("penna",18));
        users.add(new User("Araon",19));
        TreeItem<User> root =new RecursiveTreeItem<User>(users,RecursiveTreeObject::getChildren);
        
        treetable.getColumns().setAll(name,age);
        treetable.setRoot(root);
        treetable.setShowRoot(false);
    }

    @FXML
    public void handleBack(){
        rootLayoutController.searchPaneVisible(false);
    }

    public void loadLanguage(String language) {
    }

    class User extends RecursiveTreeObject<User> {
        StringProperty name ;
        IntegerProperty age ;
        public User( String name,int age){
            this.age=new SimpleIntegerProperty(age);
            this.name=new SimpleStringProperty(name);
        }
    }

    public void addTextListen(){
        rootLayoutController.getSearchFeild().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treetable.setPredicate(new Predicate<TreeItem<User>>() {
                    @Override
                    public boolean test(TreeItem<User> userTreeItem) {
                        Boolean flag=userTreeItem.getValue().name.getValue().contains(newValue)
                        || userTreeItem.getValue().age.getValue().toString().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }
}
