package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.util.R;

import java.io.IOException;

public class ConcesionarioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Concesionario!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}