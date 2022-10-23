package gestion.inscription;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("connexion"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        /** 
        Rectangle2D screen=Screen.getPrimary().getBounds();
        scene.getWindow().setX(2);
        scene.getWindow().setY(2);
        scene.getWindow().setWidth(screen.getWidth());
        scene.getWindow().setHeight(screen.getHeight());
        */
        scene.getWindow().setWidth(800);
        scene.getWindow().setHeight(800);
       scene.setRoot(loadFXML(fxml));

   

    }

    //LOADFxml est une fonction qui convertie les vues fxml en objet javaFx
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    

    public static void main(String[] args) {
        launch();
    }

}