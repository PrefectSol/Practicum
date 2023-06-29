module com.mpm.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.mpm.demo to javafx.fxml;
    exports com.mpm.demo;
}