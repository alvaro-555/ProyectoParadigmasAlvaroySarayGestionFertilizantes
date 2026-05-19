package com.alvarosaray;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App - Gestor de Fertilizantes
 * @author Alvaro y Saray
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu1regisyiniciS"), 312, 386);
        stage.setTitle("Gestor de Fertilizantes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        resizeWindow(fxml);
    }

    private static void resizeWindow(String fxml) {
    Stage stage = (Stage) scene.getWindow();
    switch (fxml) {
        case "Menu1regisyiniciS":
            stage.setWidth(312); stage.setHeight(386); break;
        case "Inicio_sesion":
            stage.setWidth(250); stage.setHeight(546); break;
        case "Registro":
            stage.setWidth(442); stage.setHeight(560); break;
        case "inventario":
            stage.setWidth(414); stage.setHeight(460); break;
        case "Añadir_Fertilizante":
            stage.setWidth(400); stage.setHeight(415); break;
        case "Editar_Fertilizante":
            stage.setWidth(400); stage.setHeight(415); break;
    }
    stage.centerOnScreen();
}

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
