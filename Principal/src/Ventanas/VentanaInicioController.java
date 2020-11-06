package Ventanas;

import Sockets.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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



    public VentanaInicioController(String FXMLdirectory) {
        super(FXMLdirectory);

    }

    public VentanaInicioController(){}





    @FXML
    public void CrearServer (ActionEvent e) throws IOException {
        server = new Server();
        this.ipEntry.setText(server.getServerIp());
        this.portEntry.setText(server.getServerPort());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    server.runServer();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();

        System.out.println("Prueba");



      //  VentanaInfoServerController ventanaServer = new VentanaInfoServerController("VentanaInfoServerFXML.fxml");
     //   ventanaServer.setEverPriority(true);


       // ventanaServer.show();




       // ventanaServer.setPreviusWin(this);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
