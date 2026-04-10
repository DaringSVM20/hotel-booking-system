package com.hotelbooking;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseHelper.initializeDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(
            App.class.getResource("/com/hotelbooking/fxml/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 680);
            stage.setMinWidth(800);
            stage.setMinHeight(600);
        // Load CSS globally
        scene.getStylesheets().add(
            getClass().getResource("/com/hotelbooking/css/styles.css").toExternalForm());
        stage.setTitle("Hotel Booking System");
        stage.setScene(scene);
        // stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
