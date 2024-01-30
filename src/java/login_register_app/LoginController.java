package login_register_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * SWITCHING TO LOGIN/SIGNUP SCENE
     * Handles the login/signup button on the left side of the scenes.
     * On click, it switches to the other scene.
     */
    @FXML Button switchToSignUp;
    @FXML Button switchToLogin;
    @FXML
    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Login.fxml")));
        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSignUp(ActionEvent actionEvent) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/SignUp.fxml")));
        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}