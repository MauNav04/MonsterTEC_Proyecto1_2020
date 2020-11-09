package Ventanas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application{
    public static  void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaInicioFXML.fxml"));
        Parent root =  loader.load();
        VentanaInicioController ventana = loader.getController();
        ventana.build(root);






        System.out.println("Hello");

    }
}
