package Ventanas;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController extends WindowsClass implements Initializable {

    @FXML
    public Button butP2;

    public VentanaJuegoController(String FXMLdirectory) {
        super(FXMLdirectory);
    }

    public VentanaJuegoController() {
    }

    @FXML
    public void prueba2 (ActionEvent e) throws IOException {
        System.out.println("Hello");
        Ventana3Controller ventanaDeck = new Ventana3Controller("Ventana3FXML.fxml");
       // ventanaDeck.setEverPriority(true);
        ventanaDeck.show();


    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}