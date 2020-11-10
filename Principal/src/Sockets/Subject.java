package Sockets;
import Estructuras.ListaSimple;
import GameObjects.PlayingCard;
import JSON.Decoder;
import JSON.Encoder;
import JSON.Message;

import JSON.gameMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

//informacion a recibir (bool-)

public class Subject {

    private boolean palying = false;
    private RecieverSocket listener;
    private SendingSocket deliver;
    private boolean connected = false;
    public boolean actionControl = false;// false recibir - true enviar
    public int life = 0;
    public int mana = 0;
    public boolean isDeck;
    public PlayingCard[] hand = new PlayingCard[10];
    public PlayingCard topDeckCard;
    public Boolean inGame =  false;
    public PlayingCard cardPlayed;
    public Boolean end;

    public int ServersConnectionPort= 2080;

    public String ServersConnectionIP;

    private String subjectPort;
    private String subjectIP;

    public Subject() throws IOException {
        initializer();
        AdjustInitInfo();
        startGame();

        //ActionPerformer();
        /*if(actionControl){
            int port = PortToContact();
            String message = MessageCreator();
            dealer = new SendingSocket("",port,message);
        }
        else {
            int port = PortSelector();
            listener = new RecieverSocket(port);
        }*/
    }

    private void AdjustInitInfo() throws IOException {
        LISTEN(Integer.parseInt(this.subjectPort)); // Espera para recibir info inicial

    }

    private void startGame() throws IOException {
        System.out.println("Game starts now - Client");
        while(inGame){
            LISTEN(Integer.parseInt(this.subjectPort)); // Espera hasta que se le de el permiso de jugar
            //communicationsLoop(false);
            //gameStarted = !gameStarted;
        }
    }



    //Primero se debe crear el listener para poder pasar el puerto como parametro

    private void initializer() throws UnknownHostException, JsonProcessingException {
        HostSubject();
        String message = MessageCreator();
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(message,connectToPort);
    }

    // 0 para recibir mensaje - 1 para enviar mensaje
    /*private void ActionSelector(){
        System.out.println("Select an action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        if(action == 1) ActionChanger();
    }

    private void ActionPerformer(){
        if(this.getClass().getCanonicalName() == "Subject"){
            LISTEN(Integer.parseInt(this.subjectPort));
        }
    }*/


    //Se cambiará por un set a la variable
    private int PortToContact(int port){
        return port;
    }

    //Codificará el mensaje en un JSON
    private String MessageCreator() throws JsonProcessingException {
        Message message = new Message(".newPlayer", this.subjectPort);
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        return strMessage;
    }

    private Message MessageDecoder(String message) throws JsonProcessingException {
        Decoder decoder = new Decoder();
        Message startInfo = decoder.Decode(message);
        return startInfo;
    }

    private void SEND(String message, int portToConnect) {
        deliver = new SendingSocket(this.ServersConnectionIP, portToConnect, message);//deliver = new SendingSocket("", portToConnect, message);
        System.out.println("Message Sent successfully");
    }

    private void LISTEN(int port) throws IOException {
        listener = new RecieverSocket(port);
        String message = listener.getInfo();
        Message messageObject = MessageDecoder(message);
        InfoUpdate(messageObject);
        //System.out.println("Info recieved from server: " + message);
    }

    private void HostSubject() throws UnknownHostException {
        getIp();
        getPort();
    }

    private void SetInitialInfo(Message initialInfo){
        this.hand = initialInfo.firstHand;
        this.topDeckCard = initialInfo.topDeckCard;
        this.mana = 50;
        this.life = 100;
        this.isDeck = true;
        this.inGame = true;
    }

    private void InfoUpdate(Message infoFromServer){ //message structure "50,80" - "Life-Mana" (order)
        if(!inGame){  // Acomoda la info inicial
            SetInitialInfo(infoFromServer);
        }else{
            if(infoFromServer.permission.equals(true)){ // El usuario puede jugar
                waitForAction();
            }
        }
    }

    private void waitForAction() throws JsonProcessingException {

        // Si el boton de saltar es presionado
        gameMessage message = new gameMessage("jumpTurn", false);
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(strMessage,connectToPort);

        // Si el boton de deck es presionado
        gameMessage message = new gameMessage("grabCard", false);
        this.hand[5] = this.topDeckCard;
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(strMessage,connectToPort);

        // Si el boton de una carta es presionado
        gameMessage message = new gameMessage("playCard", this.cardPlayed,false);
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(strMessage,connectToPort);


    }

    private void getIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        String strIp = ip.toString();
        String[] splitStrIp = strIp.split("/");
        String ipNumber = splitStrIp[1];
        System.out.println("Current IP: "+ ipNumber);
        this.subjectIP = ipNumber;
    }

    private void getPort(){
        Random randomInstance = new Random();
        Integer randomPort = randomInstance.nextInt((4000 - 2000) + 1) + 2000;
        String strPort = randomPort.toString();
        System.out.println("Current Port: "+ strPort);
        this.subjectPort = strPort;
    }

    private void ActionChanger (){
        this.actionControl = !actionControl;
    }

    public static void main(String args[]) throws IOException {
        Subject talker = new Subject();
    }


}
