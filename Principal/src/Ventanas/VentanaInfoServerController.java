package Ventanas;

import Sockets.Server;
import Sockets.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaInfoServerController extends WindowsClass implements Initializable {
    private Server server;
    public Thread serverThread ;

    @FXML
    public Button butP3;
    public Label labelIP;
    public Label labelPort;



    public VentanaInfoServerController() {
    }
    ///_________________________________________________________________________________________________________________

    public void setServerInfo() throws IOException {
        this.server = Server.mainServer(new String[0]);
        System.out.println(server.getServerPort());
        this.labelIP.setText(server.getServerIp());
        this.labelPort.setText(server.getServerPort());
    }



    @FXML
    public void Jugar(ActionEvent e) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("VentanaJuegoFXML.fxml"));
        Parent root = loader.load();
        VentanaJuegoController ventanajuego = loader.getController();
        Subject subject = Subject.mainSubject(new String[0]);
        ventanajuego.setSubject(subject);
        ventanajuego.build(root);
        this.close();

        System.out.println("Prueba Herencia");
    }




    @FXML
    public void Cancel(ActionEvent e) throws IOException {
        this.close();
        this.previus.show();



    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
