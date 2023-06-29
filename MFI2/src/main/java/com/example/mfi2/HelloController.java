package com.example.mfi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

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
    protected void unVisible(){
        table.setVisible(false);
        addForm.setVisible(false);
        edit.setVisible(false);
    }
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> tableFio;
    @FXML
    private TableColumn<Person, String> tablePhone;
    @FXML
    private void initialize() {
        unVisible();
        table.setVisible(true);
        tableFio.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("fio"));
    }
    @FXML
    private void add(){
        Person user = new Person(value1.getText(),value2.getText());
        personData.add(user);
        table.setItems(personData);
        otmena();
        addDelForm();
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