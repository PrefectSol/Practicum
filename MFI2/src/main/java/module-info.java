module com.example.mfi2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.mfi2 to javafx.fxml;
    exports com.example.mfi2;
}