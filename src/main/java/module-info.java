module com.practic.practicum {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.practic.practicum to javafx.fxml;
    exports com.practic.practicum;
}