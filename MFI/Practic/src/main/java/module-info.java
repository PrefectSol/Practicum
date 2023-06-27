module com.mfi.practic {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.mfi.practic to javafx.fxml;
    exports com.mfi.practic;
}