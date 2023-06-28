package com.example.mfi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    private String type = "";
    @FXML
    private AnchorPane background;
    @FXML
    private SplitPane sPane;
    @FXML
    private AnchorPane addForm;
    @FXML
    private TableView<Person> table;
    @FXML
    private TextField value1;
    @FXML
    private TextField value2;
    @FXML
    protected void clickAdd(){
        table.setVisible(false);
        addForm.setVisible(true);
    }
    @FXML
    public void otmena() {
        table.setVisible(true);
        addForm.setVisible(false);
    }
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
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> tableFio;
    @FXML
    private TableColumn<Person, String> tablePhone;
    @FXML
    private void initialize() {
        tableFio.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));

    }
    @FXML
    private void add(){
        personData.add(new Person(value1.getText(),value2.getText()));
        table.setItems(personData);
        otmena();
    }
    @FXML
    private void delete(){
        //personData.remove(1);
    }
}