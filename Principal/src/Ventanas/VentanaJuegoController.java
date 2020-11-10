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
    public Button c1;
    public Button c2;
    public Button c3;
    public Button c4;
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
    public void c1funtion(ActionEvent e) throws IOException {
        System.out.println("Jauandivbfdbvjbsdjf");
        if(subject.isTurn()){
            subject.send_c1();
        }

    }


    @FXML
    public void c2funtion(ActionEvent e) throws IOException {
        System.out.println("Jauandivbfdbvjbsdjf");
        if(subject.isTurn()){
            subject.send_c2();
        }

    }

    @FXML
    public void c3funtion(ActionEvent e) throws IOException {
        System.out.println("Jauandivbfdbvjbsdjf");
        if(subject.isTurn()){
            subject.send_c3();
        }

    }

    @FXML
    public void c4funtion(ActionEvent e) throws IOException {
        System.out.println("Jauandivbfdbvjbsdjf");
        if(subject.isTurn()){
            subject.send_c4();
        }

    }


    @FXML
    public void nextfuntion(){
        this.subject.Next();

    }

    @FXML
    public void previusfuntion(){
        this.subject.Previus();
    }

    public void actualizarCs(){
        this.c1.setText(this.subject.c1.getData().type+"\n"+this.subject.c1.getData().attribute+"\n"+this.subject.c1.getData().type);
        this.c2.setText(this.subject.c2.getData().type+"\n"+this.subject.c1.getData().attribute+"\n"+this.subject.c1.getData().type);
        this.c3.setText(this.subject.c3.getData().type+"\n"+this.subject.c1.getData().attribute+"\n"+this.subject.c1.getData().type);
        this.c4.setText(this.subject.c4.getData().type+"\n"+this.subject.c1.getData().attribute+"\n"+this.subject.c1.getData().type);
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

    public void actualizarEnemi(int playerLife, int playerMana) {
        this.CVI.setText(String.valueOf(playerLife));
        this.CMA.setText(String.valueOf(playerMana));
    }
}
