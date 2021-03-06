package Ventanas;

import Sockets.Server;
import Sockets.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaInicioController extends WindowsClass implements Initializable{
    @FXML
    public Button butP;
    public TextField ipEntry;
    public TextField portEntry;


    public VentanaInicioController(){}


    @FXML
    public void Unirse(ActionEvent e) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("VentanaJuegoFXML.fxml"));
        Parent root = loader.load();
        VentanaJuegoController ventanajuego = loader.getController();
        Subject subject = Subject.mainSubject(new String[0],this.ipEntry.getText(),Integer.parseInt(this.portEntry.getText()));
        ventanajuego.setSubject(subject);
        ventanajuego.build(root);
        this.close();


    }


    @FXML
    public void CrearServer (ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaInfoServerFXML.fxml"));
        Parent root = loader.load();
        VentanaInfoServerController ventana = loader.getController();
        ventana.build(root);
        ventana.setPrevius(this);
        ventana.setServerInfo();
        this.close();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
