package com.example.mfi2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    private String type = "";
    @FXML
    protected void clickAdd(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private AnchorPane background;
    @FXML
    private SplitPane sPane;
    @FXML
    private TableView table ;
    @FXML
    public void switchTheme() {
        if(type.equals("DarkGray")){
            type = "white";
        }else{
            type = "DarkGray";
        }
        background.setStyle("-fx-background-color:" + type);
        sPane.setStyle("-fx-background-color:" + type);
        table.setStyle("-fx-background-color:" + type);
    }
}