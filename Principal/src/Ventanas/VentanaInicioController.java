package Ventanas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaInicioController extends WindowsClass implements Initializable{
    @FXML
    public Button butP;


    public VentanaInicioController(String FXMLdirectory) {
        super(FXMLdirectory);
    }

    public VentanaInicioController(){}



    @FXML
    public void abrirDeck (ActionEvent e) throws IOException {

        VentanaJuegoController ventanaDeck = new VentanaJuegoController("VentanaJuegoFXML.fxml");
        ventanaDeck.setEverPriority(true);
        ventanaDeck.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
