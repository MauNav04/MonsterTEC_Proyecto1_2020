package Ventana1;

import Ventana2.Ventana2Controller;
import Ventana3.Ventana3Controller;
import WindowsClass.WindowsClass;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ventana1Controller extends WindowsClass implements Initializable{
    @FXML
    public Button butP;


    public Ventana1Controller(String FXMLdirectory) {
        super(FXMLdirectory);
    }

    public Ventana1Controller(){}



    @FXML
    public void abrirDeck (ActionEvent e) throws IOException {

        Ventana2Controller ventanaDeck = new Ventana2Controller("Ventana2FXML.fxml");
        ventanaDeck.setEverPriority(true);
        ventanaDeck.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
