module com.example.project_03login_register_app {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens login_register_app to javafx.fxml;
    exports login_register_app;
}