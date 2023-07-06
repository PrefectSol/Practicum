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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.geom.Area;
import java.io.IOException;
public class HelloController {
    public VBox VboxContainer;
    public ToggleGroup AngleFunctions;
    public RadioButton cosRB;
    public RadioButton sinRB;
    public RadioButton thRB;
    public RadioButton cthRB;
    public Label angleValue;
    public TextField angleInput;
    private static String M_Style;
    public TextField sideA;
    public TextField sideB;
    public Label resultLabel;
    public RadioButton RBtrapezoid;
    public RadioButton RBrectangle;
    public TextField heightField;

    private IntegralMatcher matcher;

    private Function functionContr;

    private ToggleGroup toggleGroup;


    enum Func {
        sin,
        cos,
        tg,
        ctg,

        areaR,
        areaT
    }

    ;

    HelloController.Func activeFunction = HelloController.Func.cos;

    @FXML
    protected void onFunctionClick() {
        if (functionContr != null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Function.fxml"));

            Scene scene = new Scene(loader.load());
            functionContr = loader.getController();

            final int w = 344;
            final int h = 370;

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Function");
            stage.setMinWidth(w);
            stage.setMinHeight(h);
            stage.setWidth(w);
            stage.setHeight(h);
            stage.setMaxWidth(w);
            stage.setMaxHeight(h);
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);

            stage.show();
            stage.setOnCloseRequest((WindowEvent event) ->
            {
                functionContr = null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSwitchTheme() {
        String style = VboxContainer.getStyle();

        if (style.equals("-fx-background-color: #D9D9D9;")) {
            M_Style = "-fx-background-color: white;";
            VboxContainer.setStyle(M_Style);
        } else {
            M_Style = "-fx-background-color: #D9D9D9;";
            VboxContainer.setStyle(M_Style);
        }

        if (matcher != null) {
            matcher.initialize();
        }
        if (functionContr != null)
            functionContr.initialize();

    }

    @FXML
    protected void onIntegralClick() {
        if (matcher != null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IntegralMatcher.fxml"));

            Scene scene = new Scene(loader.load());
            matcher = loader.getController();

            Stage stage = new Stage();

            final int w = 600;
            final int h = 420;

            stage.setTitle("IntegralMatcher");
            stage.setMinWidth(w);
            stage.setMinHeight(h);
            stage.setWidth(w);
            stage.setHeight(h);
            stage.setMaxWidth(w);
            stage.setMaxHeight(h);
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest((WindowEvent event) ->
            {
                matcher = null;
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getStyle() {
        return M_Style;
    }

    @FXML
    protected void onGetAngleClick() {
        Boolean isEmptyString = angleInput.getText().isEmpty();
        if (isEmptyString) {
            angleValue.setText("Значение: Error");
            return;
        }

        double angleInputValue;
        try {
            angleInputValue = Double.parseDouble(angleInput.getText());
        } catch (Exception e) {
            angleValue.setText("Значение: Error");
            return;
        }

        angleInputValue *= Math.PI / 180.0;

        AngleFunctions.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null) {
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

        if (activeFunction == Func.cos) {
            result = Double.toString(Math.cos(angleInputValue));
        } else if (activeFunction == Func.sin) {
            result = Double.toString(Math.sin(angleInputValue));
        } else if (activeFunction == Func.tg) {
            result = Double.toString(Math.tan(angleInputValue));
        } else {
            result = Double.toString(1.0 / Math.tan(angleInputValue));
        }

        angleValue.setText("Значение: " + result);
    }

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();
        RBrectangle.setToggleGroup(toggleGroup);
        RBtrapezoid.setToggleGroup(toggleGroup);
        RBrectangle.setSelected(true);
    }

    @FXML
    private void onCalculateRectangleArea() {
        try {
            double length = Double.parseDouble(sideA.getText());
            double width = Double.parseDouble(sideB.getText());

            if (RBrectangle.isSelected()) {
                double area = length * width;
                resultLabel.setText("Площадь прямоугольника: " + area);
            } else if (RBtrapezoid.isSelected()) {
                double height = Double.parseDouble(heightField.getText());
                double area = (length + width) * height / 2;
                resultLabel.setText("Площадь трапеции: " + area);
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Error");
        }
    }
}