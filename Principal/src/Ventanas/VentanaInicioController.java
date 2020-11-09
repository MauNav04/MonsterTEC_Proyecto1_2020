package Ventanas;

import Sockets.Server;
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
    public Server server;
    @FXML
    public Button butP;
    public TextField ipEntry;
    public TextField portEntry;


    public VentanaInicioController(){}


    @FXML
    public void Unirse(){


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
