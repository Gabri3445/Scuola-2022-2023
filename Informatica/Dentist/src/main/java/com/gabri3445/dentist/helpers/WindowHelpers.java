package com.gabri3445.dentist.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class WindowHelpers {
    private WindowHelpers() {

    }

    /**
     * Creates a window with the given parameters
     *
     * @param fxmlPath    path to the fxml file
     * @param title       title of the window
     * @param width       width of the window
     * @param height      height of the window
     * @param showAndWait if true, the window will be shown and the method will return after the window is closed
     * @param clazz       main class
     * @param <T>         type of the main class
     * @throws IOException if the fxml file is not found
     */
    public static <T> void createWindow(String fxmlPath, String title, int width, int height, boolean showAndWait, @NotNull Class<T> clazz) throws IOException {
        FXMLLoader loader = new FXMLLoader(clazz.getResource(fxmlPath));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load(), width, height);
        scene.getStylesheets().add(Objects.requireNonNull(clazz.getResource("globalFont.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(clazz.getResourceAsStream("icon.png"))));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        if (showAndWait) {
            stage.showAndWait();
        } else {
            stage.show();
        }
    }


    /**
     * Creates an alert with the given parameters
     *
     * @param title   title of the alert
     * @param header  header of the alert
     * @param content content of the alert
     * @return true if the user choose OK, false otherwise
     */
    public static boolean createAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> choose = alert.showAndWait();
        return choose.isPresent() && choose.get() == ButtonType.OK;
    }
}
