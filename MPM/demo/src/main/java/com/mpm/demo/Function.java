package com.mpm.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Function {
//радиобатн
        public void start(Stage primaryStage) {
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

// Создаем элменты управления
            Label xLabel = new Label("X: ");
            TextField xField = new TextField();
            Label yLabel = new Label("Y: ");
            TextField yField = new TextField();
            Button calculateButton = new Button("Calculate");
            Label resultLabel = new Label();

// Добавляем элементы на панель
            gridPane.add(xLabel, 0, 0);
            gridPane.add(xField, 1, 0);
            gridPane.add(yLabel, 0, 1);
            gridPane.add(yField, 1, 1);
            gridPane.add(calculateButton, 0, 2);
            HBox hbox = new HBox(resultLabel);
            gridPane.add(hbox, 0, 3, 2, 1);

// Обработчик кнопки расчета
            calculateButton.setOnAction(event -> {
                try {
                    double x = Double.parseDouble(xField.getText());
                    double y = Double.parseDouble(yField.getText());
                    double result = function(x, y); // Здесь вызываем функцию для расчета результата
                    resultLabel.setText("Result: " + result);
                } catch (NumberFormatException e) {
                    resultLabel.setText("Invalid input");
                }
            });

            Scene scene = new Scene(gridPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        // Здесь определяем функцию, которую нужно расчитать в точке (x, y)
        private double function(double x, double y) {
            return x * y + Math.sin(x) + Math.cos(y);
        }

        public static void main(String[] args) {
            launch(args);
        }
    }