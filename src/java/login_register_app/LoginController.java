package login_register_app;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
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



    private void loadNewScene (Parent newRoot, BorderPane baseBorderPane, AnchorPane baseContainer) {
        newRoot.opacityProperty().set(0);
        baseBorderPane.getChildren().add(newRoot);

        KeyValue newRootContainerOpacityKV =
                new KeyValue(newRoot.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyFrame newRootContainerOpacityKF = new KeyFrame(Duration.seconds(0.5), newRootContainerOpacityKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                newRootContainerOpacityKF
        );

        timeline.setOnFinished(event -> {
            baseBorderPane.getChildren().remove(baseContainer);
        });

        this.root = newRoot;
        timeline.play();
    }


    private void switchToNewScene
            (Parent newRoot, BorderPane baseBorderPane, AnchorPane baseContainer,
             Pane baseContent, double baseContainerSlideValue) {

        // Create KeyValue
        KeyValue baseContainerTranslateXKV =
                new KeyValue(baseContainer.translateXProperty(), baseContainerSlideValue, Interpolator.EASE_BOTH);
        KeyValue baseContentOpacityKV =
                new KeyValue(baseContent.opacityProperty(), 0, Interpolator.EASE_BOTH);

        // Create KeyFrames
        KeyFrame baseContainerTranslateXKF = new KeyFrame(Duration.seconds(0.5), baseContainerTranslateXKV);
        KeyFrame baseContentOpacityKF = new KeyFrame(Duration.seconds(0.4), baseContentOpacityKV);

        // Set and play Timeline
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                baseContainerTranslateXKF,
                baseContentOpacityKF
        );

        // Switch when Finished
        timeline.setOnFinished(event -> {
            try {
                this.loadNewScene(newRoot, baseBorderPane, baseContainer);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        timeline.play();
    }
    
    public void switchToSignUp() throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Signup.fxml")));
        switchToNewScene(newRoot, loginBorderPane, loginContainer, loginContent, 400);
    }

    public void switchToLogin() throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Login.fxml")));
        switchToNewScene(newRoot, signupBorderPane, signupContainer, signupContent, -400);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}