package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.model.Movie;
import com.model.MovieType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.MainApp.mainMovieTypes;
import static com.MainApp.mainMovies;

public class MovieTableViewController {
    @FXML
    private JFXTreeTableView<treeMovie> treetable;

    private RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }
    public JFXTreeTableView getTreeTabla(){
        return  treetable;
    }

    private JFXTreeTableColumn<treeMovie, String> initTreeColum(String tableName, int width){
        JFXTreeTableColumn<treeMovie,String> tableColumn =new JFXTreeTableColumn<>(tableName);
        tableColumn.setPrefWidth(width);
        return tableColumn;
    }

    @FXML
    private void initialize(){

        // Title Column
        JFXTreeTableColumn<treeMovie,String> title = initTreeColum("Title",200);
        title.setCellValueFactory(param -> param.getValue().getValue().title);

        // Movie Type Column
        JFXTreeTableColumn<treeMovie,String> type = initTreeColum("Type", 100);
        type.setCellValueFactory(param ->  param.getValue().getValue().type);

        // Movie Type Column
        JFXTreeTableColumn<treeMovie,String> language = initTreeColum("Language", 140);
        language.setCellValueFactory(param -> param.getValue().getValue().language);

        // Year Column
        JFXTreeTableColumn<treeMovie,String> year = initTreeColum("Year", 121);
        year.setCellValueFactory(param -> param.getValue().getValue().year);

        //被搜索的对象
        ObservableList<treeMovie> treeMovies = FXCollections.observableArrayList();
//        treeMovies.add(new treeMovie("penna",18));
//        treeMovies.add(new treeMovie("Araon",19));
        for (Movie m:mainMovies) {
            treeMovies.add(new treeMovie(m));
        }
        // TODO: 2018/6/11  批量添加 movie 到TreeView
        TreeItem<treeMovie> root =new RecursiveTreeItem<treeMovie>(treeMovies,RecursiveTreeObject::getChildren);
        treetable.getColumns().addAll(title, type, language, year);
        treetable.setRoot(root);
        treetable.setShowRoot(false);

        //鼠标双击事件
        treetable.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                TreeItem<treeMovie> item=treetable.getSelectionModel().getSelectedItem();
                if (item!=null) {
                    Movie tempt = item.getValue().movie;
                    System.out.println(tempt);
                    try {
                        MainApp.player.mediaPlayer.stop();
                        MainApp.player.getRootLayoutController().seePlaypage();
                        MainApp.player.getSpinner().setVisible(true);
                        MainApp.player.setPlayerWithBar(new Media(tempt.getHref()));
                        MainApp.player.mediaPlayer.setAutoPlay(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                rootLayoutController.searchPaneVisible(false);
            }
        });

    }

    @FXML
    public void handleBack(){
        rootLayoutController.searchPaneVisible(false);
    }

    public void loadLanguage(String language) {
    }

    class treeMovie extends RecursiveTreeObject<treeMovie> {
        StringProperty title;
        StringProperty type;
        StringProperty language;
        StringProperty year;
        Movie movie;
        private treeMovie(Movie movie){
            this.movie = movie;
            this.title = new SimpleStringProperty(movie.getTitle_cn()+"\n"+movie.getTitle_en());
            MovieType type = mainMovieTypes.stream()
                    .filter(movieType -> movieType.getType_id()==movie.getType())
                    .collect(Collectors.toList())
                    .get(0);
            String tab = "            ";
            this.type = new SimpleStringProperty(type.getType_cn()+"\n"+type.getType_en());
            String tempt = tab + movie.getLanguage();
            this.language = new SimpleStringProperty(tempt);
            this.year = new SimpleStringProperty(movie.getYear());
        }
    }

    public void addTextListen(){
        rootLayoutController.getSearchFeild().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treetable.setPredicate(userTreeItem -> {
                    if(newValue.length()>0)//这个别删，是检测文本大于0自动打开搜索框，不点回车了
                        rootLayoutController.searchPaneVisible(true);
                    else
                        rootLayoutController.searchPaneVisible(false);
                    //这个是监听如果值里面有这个，就搜索返回
                    return (userTreeItem.getValue().title.getValue().contains(newValue)
                            || userTreeItem.getValue().type.getValue().contains(newValue)
                            || userTreeItem.getValue().year.getValue().contains(newValue)
                            || userTreeItem.getValue().language.getValue().contains(newValue));
                });
            }
        });
    }
}
