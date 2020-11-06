package Ventanas;

import javafx.application.Application;
import javafx.stage.Stage;

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
