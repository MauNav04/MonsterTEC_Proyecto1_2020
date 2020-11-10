package Ventanas;


import Sockets.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController extends WindowsClass implements Initializable {
    public Server server;
    @FXML
    public Button butP2;



    public VentanaJuegoController() {
    }

    @FXML
    public void prueba2 (ActionEvent e) throws IOException {
        System.out.println("Hello");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaInfoServerFXML.fxml"));
        Parent root = loader.load();
        VentanaInfoServerController ventanaDeck = loader.getController();
        ventanaDeck.build(root);


    }



    public void setServer(Server server) {
        this.server = server;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
