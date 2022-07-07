module com.example.deneme {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.deneme to javafx.fxml;
    exports com.example.deneme;
}