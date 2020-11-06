package Ventanas;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static  void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VentanaInicioController ventana = new VentanaInicioController("VentanaInicioFXML.fxml");
        ventana.getStage().setResizable(false);
        ventana.show();


        System.out.println("Hello");

    }
}
