package login_register_app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class LoginApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);

            String css = Objects.requireNonNull(this.getClass().getResource("LoginStyles.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.initStyle(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}