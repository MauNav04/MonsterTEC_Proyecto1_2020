package Ventanas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.ResourceBundle;

public class Ventana3Controller extends WindowsClass implements Initializable {
    @FXML
    public Button butP3;

    public Ventana3Controller(String FXMLdirectory) {
        super(FXMLdirectory);
    }

    public Ventana3Controller() {
    }

    @FXML
    public void prueba3(ActionEvent e){
        System.out.println("Prueba Herencia");
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
