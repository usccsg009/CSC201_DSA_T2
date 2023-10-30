module com.example.csc201_dsa_t2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc201_dsa_t2 to javafx.fxml;
    exports com.example.csc201_dsa_t2;
}