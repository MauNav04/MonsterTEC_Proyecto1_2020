package Ventanas;

import Sockets.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    public VentanaInfoServerController(String FXMLdirectory) {
        super(FXMLdirectory);
        this.labelIP = new Label();
        this.labelPort = new Label();

    }
    public VentanaInfoServerController() { }
    ///_________________________________________________________________________________________________________________


    @FXML
    public void Jugar(ActionEvent e){
        System.out.println("Prueba Herencia");
    }


    public void setServerInfo() throws IOException {

        this.server = new Server();

        this.labelIP.setText("Hello");
        this.labelPort.setText("Am fine");

        this.serverThread = new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    server.waitForUsers();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //serverThread.start();


    }
    @FXML
    public void Cancel(ActionEvent e){
        this.server.setGameStarted();
        this.server = null;
        this.serverThread.interrupt();
        this.serverThread = null;
        this.close();


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
