package com.example.mfi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
        int j = personData.size();
        Label text1 = new Label(value1.getText());
        Label text2 = new Label(value2.getText());
        Button button = new Button("Delete");
        gp.add(text1,0,j);
        gp.add(text2,1,j);
        button.setId(String.valueOf(personData.size()-1));
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                personData.remove(Integer.parseInt(String.valueOf(button.getId())));
                gp.getChildren().remove(button);
                gp.getChildren().remove(text1);
                gp.getChildren().remove(text2);
            }
        });
        gp.add(button,2,j);
    }
    @FXML
    private void delete(){
        unVisible();
        edit.setVisible(true);
    }
}