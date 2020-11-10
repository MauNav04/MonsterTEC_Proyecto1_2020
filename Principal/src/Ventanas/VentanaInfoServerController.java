package Ventanas;

import Sockets.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
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

        String[] args = new String[0];
        this.server = Server.mainServer(args);
        this.serverThread  = new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    server.runServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.serverThread.start();


        System.out.println(server.getServerPort());
        this.labelIP.setText(server.getServerIp());
        this.labelPort.setText(server.getServerPort());



    }



    @FXML
    public void Jugar(ActionEvent e) throws IOException {

        FXMLLoader loader =  new FXMLLoader(getClass().getResource("VentanaJuegoFXML.fxml"));
        Parent root = loader.load();
        VentanaJuegoController ventanajuego = loader.getController();
        ventanajuego.setServer(this.server);
        ventanajuego.build(root);
        System.out.println("Prueba Herencia");
    }




    @FXML
    public void Cancel(ActionEvent e) throws IOException {
        this.serverThread.stop();
        this.close();
        this.previus.show();



    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
