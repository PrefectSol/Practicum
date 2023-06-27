package com.mpm.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;

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

    enum Func
    {
        sin,
        cos,
        tg,
        ctg
    };

    Func activeFunction = Func.cos;

    @FXML
    protected void onCalculateClick()
    {

        Boolean isEmptyString = lowerLimit.getText().isEmpty() || upperLimit.getText().isEmpty() || partsCount.getText().isEmpty();
        if (isEmptyString)
        {
            valueLayout.setText("Error");
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
            valueLayout.setText("Error");
            return;
        }

        Boolean isInvalidValues = llValue > ulValue || count <= 0;
        if (isInvalidValues)
        {
            valueLayout.setText("Error");
            return;
        }

        Functions.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
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
                }
            }
        });

        valueLayout.setText(Double.toString(TrapezoidIntegral(llValue, ulValue, count)));
    }

    private double TrapezoidIntegral(double a, double b, int n)
    {
        double segment = (b - a) / n;
        double x = a; // to radians
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
            else
            {
                sideA = Math.abs(1.0 / Math.tan(x));
                sideB = Math.abs(1.0 / Math.tan(x + segment));
            }

            S += (sideA + sideB) * segment / 2.0;
            x += segment;
        }

        return S;
    }
}
