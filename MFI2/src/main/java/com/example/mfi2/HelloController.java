package com.example.mfi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    private String type = "";
    private String bordertype = "";
    @FXML
    private AnchorPane background;
    @FXML
    private SplitPane sPane;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane addForm;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> tableFio;
    @FXML
    private TableColumn<Person, String> tablePhone;
    @FXML
    private TextField value1;
    @FXML
    private TextField value2;
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane edit;
    @FXML
    protected void clickAdd(){
        unVisible();
        addForm.setVisible(true);
    }
    @FXML
    public void otmena() {
        unVisible();
        table.setVisible(true);
    }
    private boolean isLightMode = true;
  @FXML
    public void switchTheme(ActionEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode){
            setLightMode();
        }
        else {
            setDarkMode();
        }
       if(type.equals("White")){
            type = "Red";
        }else{
            type = "White";
        }
         table.setStyle("-fx-background-color:" + type);
        tableFio.setStyle("-fx-background-color:" + type);
        tablePhone.setStyle("-fx-background-color:" + type);
        background.setStyle("-fx-background-color:" + type);
        sPane.setStyle("-fx-background-color:" + type);
    }
    private void setLightMode(){
        sPane.getStylesheets().remove((Objects.requireNonNull(getClass().getResource("styles/DarkMode.css"))).toExternalForm());
        sPane.getStylesheets().add((Objects.requireNonNull(getClass().getResource("styles/LightMode.css"))).toExternalForm());
    }
    private void setDarkMode(){
        sPane.getStylesheets().remove((Objects.requireNonNull(getClass().getResource("styles/LightMode.css"))).toExternalForm());
        sPane.getStylesheets().add((Objects.requireNonNull(getClass().getResource("styles/DarkMode.css"))).toExternalForm());
    }
    protected void unVisible(){
        table.setVisible(false);
        addForm.setVisible(false);
        edit.setVisible(false);
    }
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        unVisible();
        table.setVisible(true);
        tableFio.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("fio"));
        search();
    }
    private void search(){
        FilteredList<Person> filteredData = new FilteredList<>(personData, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (Person.getFio().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(Person.getPhone()).contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        }));
        SortedList<Person> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    @FXML
    private void add() {
        String a = value2.getText();
        String b = value1.getText();
        try {
            if (a.matches("\\d{11}") && b.matches("[A-Za-z]+")) {
                Person user = new Person(a, b);
                personData.add(user);
                table.setItems(personData);
                otmena();
                addDelForm();
                search();
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
        }
    }
    private void addDelForm(){
        gp.getChildren().clear();
        for(int i = 0,d, j = 0; i < personData.size(); i++){
            d = 0;
            Label text = new Label(personData.get(i).getFio());
            gp.add(text,d,j);
            d++;
            text = new Label(personData.get(i).getPhone());
            gp.add(text,d,j);
            d++;
            Button button = new Button("Delete");
            button.setId(String.valueOf(j));
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    personData.remove(Integer.parseInt(String.valueOf(button.getId())));
                    addDelForm();
                }
            });
            gp.add(button,d,j);
            j++;
        }
    }
    @FXML
    private void delete(){
        unVisible();
        edit.setVisible(true);
    }
}
