package Ventana2;


import Ventana3.Ventana3Controller;
import WindowsClass.WindowsClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ventana2Controller extends WindowsClass implements Initializable {

    @FXML
    public Button butP2;

    public Ventana2Controller(String FXMLdirectory) {
        super(FXMLdirectory);
    }

    public Ventana2Controller() {
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
