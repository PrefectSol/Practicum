package com.example.mfi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class HelloController {
    private String type = "";
    private int theme = 0;
    private String bordertype = "DarkGray";
    private String selection = "";
    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane sEdit;
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
    private GridPane gp1;
    @FXML
    private AnchorPane edit;
    @FXML
    private AnchorPane edit1;
    @FXML
    private ComboBox service;
    @FXML
    private ComboBox service1;
    @FXML
    private ComboBox service2;
    @FXML
    private Label nsmEd;
    @FXML
    private Label serviceEd;
    @FXML
    private Label telephoneEd;
    @FXML
    private Label priceEd;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected void clickAdd() {
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
        if (theme == 0) {
            type = "DimGray";
            selection = "DimGray";
            sPane.setStyle("-fx-background: DimGray");
            theme += 1;
        }
        else if(theme == 1) {
            type = "White";
            selection = "DeepSkyBlue";
            sPane.setStyle("-fx-background: White");
            theme -= 1;
        }
        table.setStyle("-fx-background-color:" + type + "; -fx-border-color:" + bordertype +
               "; -fx-selection-bar:" + selection + "; -fx-selection-bar-non-focused:" + selection);
        background.setStyle("-fx-background-color:" + type);
    }
    protected void unVisible() {
        table.setVisible(false);
        addForm.setVisible(false);
        edit.setVisible(false);
        edit1.setVisible(false);
    }

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        service.setValue("Окрашивание");
        service1.setValue("Нет услуги");
        service2.setValue("Нет услуги");
        service.getItems().addAll("Окрашивание", "Стрижка", "Макияж", "Инъекция", "Пилинг");
        service1.getItems().addAll("Нет услуги", "Окрашивание", "Стрижка", "Макияж", "Инъекция", "Пилинг");
        service2.getItems().addAll("Нет услуги", "Окрашивание", "Стрижка", "Макияж", "Инъекция", "Пилинг");
        File file = new File("items.txt");
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader("items.txt"));
                boolean what = true;
                while (what) {
                    String value1 = br.readLine();
                    if (value1 == null) {
                        what = false;
                    } else {
                        String value2 = br.readLine();
                        String value3 = br.readLine();
                        String value4 = br.readLine();
                        String value5 = br.readLine();
                        Person user = new Person(value2, value1, value3, value4, value5);
                        personData.add(user);
                        table.setItems(personData);
                    }
                }
                addDedForm();
                addDelForm();
                br.close();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        unVisible();
        table.setVisible(true);
        tableFio.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("fio"));
        search();
    }

    private void search() {
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
            if (a.matches("8\\d{10}") && b.matches("[A-Za-zА-Яа-я ]+") && !b.equals(" ") && !b.matches(" [A-Za-zА-Яа-я ]+") || !b.matches(" [A-Za-zА-Яа-я ]+") && !b.equals(" ") && a.matches("[+]7\\d{10}") && b.matches("[A-Za-zА-Яа-я]+")) {
                Person person = new Person(a, b, String.valueOf(service.getValue()), String.valueOf(service1.getValue()), String.valueOf(service2.getValue()));
                personData.add(person);
                table.setItems(personData);
                otmena();
                addDedForm();
                addDelForm();
                search();
                printToFile();
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
        }
    }

    private void printToFile() {
        try {
            PrintWriter pw = new PrintWriter("items.txt");
            for (int i = 0; i < personData.size(); i++) {
                pw.println(personData.get(i).getPhone());
                pw.println(personData.get(i).getFio());
                pw.println(personData.get(i).getService());
                pw.println(personData.get(i).getService1());
                pw.println(personData.get(i).getService2());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void addDelForm() {
        gp.getChildren().clear();
        for (int i = 0, d, j = 0; i < personData.size(); i++) {
            gp.getRowConstraints().add(new RowConstraints(30));
            d = 0;
            Label text = new Label(personData.get(i).getFio());
            gp.add(text, d, j);
            d++;
            text = new Label(personData.get(i).getPhone());
            gp.add(text, d, j);
            d++;
            Button button = new Button("Delete");
            button.setId(String.valueOf(j));
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    personData.remove(Integer.parseInt(String.valueOf(button.getId())));
                    addDelForm();
                    addDedForm();
                    printToFile();
                }
            });
            gp.add(button, d, j);
            j++;
        }
    }
    @FXML
    private void addDedForm() {
        gp1.getChildren().clear();
        for (int i = 0, d, j = 0; i < personData.size(); i++) {
            gp1.getRowConstraints().add(new RowConstraints(30));
            d = 0;
            Label text = new Label(personData.get(i).getFio());
            gp1.add(text, d, j);
            d++;
            text = new Label(personData.get(i).getPhone());
            gp1.add(text, d, j);
            d++;
            Button button = new Button("Details");
            button.setId(String.valueOf(j));
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    sEdit.setVisible(true);
                    sPane.setVisible(false);
                    String service = personData.get(Integer.parseInt(String.valueOf(button.getId()))).getService();
                    String service1 = personData.get(Integer.parseInt(String.valueOf(button.getId()))).getService1();
                    String service2=personData.get(Integer.parseInt(String.valueOf(button.getId()))).getService2();
                    if(service1.equals("Нет услуги")){
                        service1 = "";
                    }else {
                        service1 = "\n" + service1;
                    }
                    if (service2.equals("Нет услуги")){
                        service2 = "";
                    }else {
                        service2 = "\n" + service2;
                    }


                    telephoneEd.setText(personData.get(Integer.parseInt(String.valueOf(button.getId()))).getFio());
                    serviceEd.setText(service+service1+service2);
                    nsmEd.setText(personData.get(Integer.parseInt(String.valueOf(button.getId()))).getPhone());
                    priceEd.setText(String.valueOf(personData.get(Integer.parseInt(String.valueOf(button.getId()))).getSummPrice()));
                    addDedForm();
                    printToFile();
                }
            });
            gp1.add(button, d, j);
            j++;
        }
    }

    @FXML
    private void delete() {
        unVisible();
        edit.setVisible(true);
    }
    @FXML
    private void detailsClient(){
        unVisible();
        edit1.setVisible(true);
    }
    @FXML
    private void exit() {
        sEdit.setVisible(false);
        sPane.setVisible(true);
    }


    Stage stage = new Stage();

    @FXML
    private void viewMasters() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Schedule.fxml")));
        stage.setTitle("Расписание мастеров");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setX((Stage.getWindows().get(0).getX() + Stage.getWindows().get(0).getWidth()) - stage.getWidth());
        stage.setY(Stage.getWindows().get(0).getY() + 30);
        Stage.getWindows().get(0).setOnHidden(event -> stage.close());
        stage.setResizable(false);
    }
}
