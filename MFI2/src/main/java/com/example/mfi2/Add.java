package com.example.mfi2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Add {
    @FXML
    private Button otmena;
    @FXML
    protected void clickAdd(){
        Stage stage = (Stage) otmena.getScene().getWindow();
        stage.close();
    }
}
