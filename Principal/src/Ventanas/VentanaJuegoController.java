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
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController extends WindowsClass implements Initializable {
    public Server server;
    public Subject subject;
    @FXML
    public Button DECK;
    public ProgressBar CV;
    public ProgressBar CM;
    public ProgressBar YoV;
    public ProgressBar YoM;

    public Label YoVi;
    public Label YoMa;
    public Label CVI;
    public Label CMA;


    public VentanaJuegoController() {
    }


    @FXML
    public void DECKfuntion(ActionEvent e) throws IOException {
        System.out.println("Jauandivbfdbvjbsdjf");
        if(subject.isTurn()){
            subject.send_c1();
        }

    }










    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        this.subject.setWindows(this);
    }

    public void actualizar(int playerLife, int playerMana) {
        this.YoVi.setText(String.valueOf(playerLife));
        this.YoMa.setText(String.valueOf(playerMana));
    }
}
