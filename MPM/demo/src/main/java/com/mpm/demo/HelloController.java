package com.mpm.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
public class HelloController {
    private Boolean blackTheme = false;


    @FXML
    protected void onFunctionClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Function.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Function");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSwitchTheme()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            String style;

            if (blackTheme)
            {
                style = "-fx-background-color: #696969;";
            }
            else
            {
                style = "-fx-background-color: #FFFFFF;";
            }

            blackTheme = !blackTheme;

            scene.getRoot().setStyle("-fx-background-color: #696969;");

            stage.setTitle("IntegralMatcher");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onIntegralClick()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IntegralMatcher.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            stage.setTitle("IntegralMatcher");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}