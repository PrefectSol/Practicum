package com.mpm.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class HelloController
{
    public VBox VboxContainer;
    public ToggleGroup AngleFunctions;
    public RadioButton cosRB;
    public RadioButton sinRB;
    public RadioButton thRB;
    public RadioButton cthRB;
    public Label angleValue;
    public TextField angleInput;

    enum Func
    {
        sin,
        cos,
        tg,
        ctg
    };

    HelloController.Func activeFunction = HelloController.Func.cos;

    @FXML
    protected void onFunctionClick()
    {
        try
        {
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
        String style = VboxContainer.getStyle();

        if (style.equals("-fx-background-color: #696969;"))
        {
            VboxContainer.setStyle("-fx-background-color: #FFFFFF;");
        }
        else
        {
            VboxContainer.setStyle("-fx-background-color: #696969;");
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

    @FXML
    protected void onGetAngleClick()
    {
        Boolean isEmptyString = angleInput.getText().isEmpty();
        if (isEmptyString)
        {
            angleValue.setText("Error");
            return;
        }

        double angleInputValue;
        try
        {
            angleInputValue = Double.parseDouble(angleInput.getText());
        }
        catch (Exception e)
        {
            angleValue.setText("Error");
            return;
        }

        angleInputValue *= Math.PI / 180.0;

        AngleFunctions.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null)
            {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                switch (selectedRadioButton.getId()) {
                    case "cosRB":
                        activeFunction = Func.cos;
                        break;
                    case "sinRB":
                        activeFunction = Func.sin;
                        break;
                    case "thRB":
                        activeFunction = Func.tg;
                        break;
                    case "cthRB":
                        activeFunction = Func.ctg;
                        break;
                }
            }
        });

        String result = "Error";

        if (activeFunction == Func.cos)
        {
            result = Double.toString(Math.cos(angleInputValue));
        }
        else if (activeFunction == Func.sin)
        {
            result = Double.toString(Math.sin(angleInputValue));
        }
        else if (activeFunction == Func.tg)
        {
            result = Double.toString(Math.tan(angleInputValue));
        }
        else
        {
            result = Double.toString(1.0 / Math.tan(angleInputValue));
        }

        angleValue.setText(result);
    }
}