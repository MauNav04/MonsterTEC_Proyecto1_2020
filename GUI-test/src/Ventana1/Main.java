package Ventana1;

import Ventana1.Ventana1Controller;
import Ventana2.Ventana2Controller;
import Ventana3.Ventana3Controller;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    public static  void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Ventana1Controller ventana = new Ventana1Controller("Ventana1FXML.fxml");
        ventana.getStage().setResizable(false);
        ventana.show();


        System.out.println("Hello");

       // ventana.close();
    }
}
