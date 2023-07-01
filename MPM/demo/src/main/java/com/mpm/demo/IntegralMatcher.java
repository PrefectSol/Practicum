package com.mpm.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class IntegralMatcher
{
    public TextField lowerLimit;
    public TextField upperLimit;
    public ToggleGroup Functions;
    public RadioButton cosRB;
    public RadioButton sinRB;
    public RadioButton tgRB;
    public RadioButton ctgRB;
    public TextField partsCount;
    public Label valueLayout;
    public AnchorPane backgr;
    public RadioButton xRB;
    public RadioButton x2RB;
    public RadioButton x3RB;
    public RadioButton x4RB;

    enum Func
    {
        sin,
        cos,
        tg,
        ctg,
        x,
        x2,
        x3,
        x4
    };

    Func activeFunction = Func.cos;

    @FXML
    public void initialize()
    {
        backgr.setStyle(HelloController.getStyle());
    }

    @FXML
    protected void onCalculateClick()
    {

        Boolean isEmptyString = lowerLimit.getText().isEmpty() || upperLimit.getText().isEmpty() || partsCount.getText().isEmpty();
        if (isEmptyString)
        {
            valueLayout.setText("Значение: Error");
            return;
        }

        double llValue, ulValue;
        int count;

        try
        {
            llValue = Double.parseDouble(lowerLimit.getText());
            ulValue = Double.parseDouble(upperLimit.getText());
            count = Integer.parseInt(partsCount.getText());

        }
        catch (Exception e)
        {
            valueLayout.setText("Значение: Error");
            return;
        }

        Boolean isInvalidValues = llValue > ulValue || count <= 0;
        if (isInvalidValues)
        {
            valueLayout.setText("Значение: Error");
            return;
        }

        Functions.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
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
                    case "tgRB":
                        activeFunction = Func.tg;
                        break;
                    case "ctgRB":
                        activeFunction = Func.ctg;
                        break;
                    case "xRB":
                        activeFunction = Func.x;
                        break;
                    case "x2RB":
                        activeFunction = Func.x2;
                        break;
                    case "x3RB":
                        activeFunction = Func.x3;
                        break;
                    case "x4RB":
                        activeFunction = Func.x4;
                        break;
                }
            }
        });

        valueLayout.setText("Значение: " + Double.toString(TrapezoidIntegral(llValue, ulValue, count)));
    }

    private double TrapezoidIntegral(double a, double b, int n)
    {
        double segment = (b - a) / n;
        double x = a;
        double S = 0;

        for(int i = 0; i <= n; i++)
        {
            double sideA, sideB;
            if (activeFunction == Func.cos)
            {
                sideA = Math.abs(Math.cos(x));
                sideB = Math.abs(Math.cos(x + segment));
            }
            else if (activeFunction == Func.sin)
            {
                sideA = Math.abs(Math.sin(x));
                sideB = Math.abs(Math.sin(x + segment));
            }
            else if (activeFunction == Func.tg)
            {
                sideA = Math.abs(Math.tan(x));
                sideB = Math.abs(Math.tan(x + segment));
            }
            else if (activeFunction == Func.ctg)
            {
                sideA = Math.abs(1.0 / Math.tan(x));
                sideB = Math.abs(1.0 / Math.tan(x + segment));
            }
            else if (activeFunction == Func.x)
            {
                sideA = Math.abs(x);
                sideB = Math.abs(x + segment);
            }
            else if (activeFunction == Func.x2)
            {
                sideA = Math.abs(Math.pow(x, 2));
                sideB = Math.abs(Math.pow(x + segment, 2));
            }
            else if (activeFunction == Func.x3)
            {
                sideA = Math.abs(Math.pow(x, 3));
                sideB = Math.abs(Math.pow(x + segment, 3));
            }
            else
            {
                sideA = Math.abs(Math.pow(x, 4));
                sideB = Math.abs(Math.pow(x + segment, 4));
            }

            S += (sideA + sideB) * segment / 2.0;
            x += segment;
        }

        return S;
    }
}
