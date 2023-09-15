package com.gabri3445.dentist;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class DentistApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    private static final int FRAME_COUNT = 8;
    private static final int FRAME_DURATION_MS = 66;
    private static final String IMAGE_BASE_NAME = "frame_";

    private static final boolean GamingMode = false;

    private int currentFrameIndex = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("globalFont.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png"))));
        if (GamingMode) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(FRAME_DURATION_MS), event -> {
                        // Update the current frame index
                        currentFrameIndex = (currentFrameIndex + 1) % FRAME_COUNT;

                        // Load the new icon frame
                        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_BASE_NAME + currentFrameIndex + ".png")));

                        // Set the new icon
                        stage.getIcons().clear();
                        stage.getIcons().add(icon);
                    })
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        stage.setResizable(false);
        stage.setTitle("Dentist Office");
        stage.setScene(scene);
        stage.show();
    }
}