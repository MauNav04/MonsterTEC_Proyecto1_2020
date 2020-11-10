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


















    public VentanaJuegoController() {
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
