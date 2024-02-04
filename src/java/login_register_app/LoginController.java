package login_register_app;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * SWITCHING TO LOGIN/SIGNUP SCENE
     * Handles the login/signup button on the left side of the scenes.
     * On click, it switches to the other scene.
     */
    @FXML private Button switchToSignUp;
    @FXML private Button switchToLogin;

    @FXML private BorderPane signupBorderPane;
    @FXML private BorderPane loginBorderPane;

    @FXML private AnchorPane loginContainer;
    @FXML private Pane loginContent;

    @FXML private AnchorPane signupContainer;
    @FXML private Pane signupContent;

    @FXML private AnchorPane switchToSignupContainer;
    @FXML private AnchorPane switchToLoginContainer;


    private void loadSignUpScene(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Signup.fxml")));

        newRoot.opacityProperty().set(0);
        loginBorderPane.getChildren().add(newRoot);

        KeyValue newRootContainerOpacityKV =
                new KeyValue(newRoot.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyFrame newRootContainerOpacityKF = new KeyFrame(Duration.seconds(0.5), newRootContainerOpacityKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                newRootContainerOpacityKF
        );

        timeline.setOnFinished(event -> {
            loginBorderPane.getChildren().remove(loginContainer);
        });

        timeline.play();
    }
    
    public void switchToSignUp(ActionEvent actionEvent) throws IOException {
        loginContainer.translateXProperty().set(0);

        // Create KeyValue
        KeyValue loginContainerTranslateXKV =
                new KeyValue(loginContainer.translateXProperty(), 400, Interpolator.EASE_BOTH);
        KeyValue loginContentOpacityKV =
                new KeyValue(loginContent.opacityProperty(), 0, Interpolator.EASE_BOTH);

        // Create KeyFrames
        KeyFrame loginContainerTranslateXKF = new KeyFrame(Duration.seconds(0.5), loginContainerTranslateXKV);
        KeyFrame loginContentOpacityKF = new KeyFrame(Duration.seconds(0.5), loginContentOpacityKV);

        // Set and play Timeline
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                loginContainerTranslateXKF,
                loginContentOpacityKF
        );

        // Switch when Finished
        timeline.setOnFinished(event -> {
            try {
                this.loadSignUpScene(actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        timeline.play();


    }

    public void switchToLogin(ActionEvent actionEvent) throws IOException {

        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Login.fxml")));
        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}