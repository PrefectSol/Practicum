package com.mpm.demo;


import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class Function {

    public ToggleGroup AngleFunctions;
    public RadioButton cosRB;
    public RadioButton sinRB;
    public RadioButton thRB;
    public RadioButton cthRB;
    public Label angleValue;
    public TextArea angleInput;
    public RadioButton RBx3;

    public RadioButton RBx2;
    public AnchorPane backgr;

    enum Func
    {
        sin,
        cos,
        tg,
        ctg,
        x2,
        x3
    };

   Function.Func activeFunction = Function.Func.cos;
   @FXML
    public void initialize()
    {
        backgr.setStyle(HelloController.getStyle());
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



        AngleFunctions.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null)
            {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                switch (selectedRadioButton.getId()) {
                    case "cosRB":
                        activeFunction = Function.Func.cos;
                        break;
                    case "sinRB":
                        activeFunction = Function.Func.sin;
                        break;
                    case "thRB":
                        activeFunction = Function.Func.tg;
                        break;
                    case "cthRB":
                        activeFunction = Function.Func.ctg;
                        break;
                    case "RBx3":
                        activeFunction = Function.Func.x3;
                        break;case "RBx2":
                        activeFunction = Function.Func.x2;
                        break;
                }
            }
        });

        String result = "Error";

        if (activeFunction == Function.Func.cos)
        {
            result = Double.toString(Math.cos(angleInputValue *= Math.PI / 180.0));
        }
        else if (activeFunction == Function.Func.sin)
        {
            result = Double.toString(Math.sin(angleInputValue *= Math.PI / 180.0));
        }
        else if (activeFunction == Function.Func.tg)
        {
            result = Double.toString(Math.tan(angleInputValue *= Math.PI / 180.0));
        }
        else if (activeFunction == Function.Func.x3)
        {
            result = Double.toString(Math.pow(angleInputValue,3));
        } else if (activeFunction == Function.Func.x2)
        {
            result = Double.toString(Math.pow(angleInputValue,2));
        }
        else
        {
            result = Double.toString(1.0 / Math.tan(angleInputValue));
        }

        angleValue.setText(result);
    }
}