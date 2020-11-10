package Sockets;
import Estructuras.ListaCircularDoble;
import Estructuras.ListaSimple;
import Estructuras.NodoListadoble;
import GameObjects.PlayingCard;
import JSON.Decoder;
import JSON.Encoder;
import JSON.Message;

import JSON.gameMessage;
import Ventanas.VentanaJuegoController;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

//informacion a recibir (bool-)

public class Subject implements Runnable  {

    public NodoListadoble<PlayingCard> c1;
    public NodoListadoble<PlayingCard> c2;
    public NodoListadoble<PlayingCard> c3;
    public NodoListadoble<PlayingCard> c4;

    private boolean palying = false;
    private RecieverSocket listener;
    private SendingSocket deliver;
    private boolean connected = false;
    public boolean actionControl = false;// false recibir - true enviar
    public int life = 0;
    public int mana = 0;
    public boolean isDeck;
    public PlayingCard[] hand = new PlayingCard[10];
    public ListaCircularDoble<PlayingCard>Hand;
    public PlayingCard topDeckCard;
    public Boolean inGame =  false;
    public PlayingCard cardPlayed;

    public Boolean end;
    public Boolean turn;


    public int ServersConnectionPort= 2080;

    public String ServersConnectionIP = "10.0.0.3";

    private String subjectPort;
    private String subjectIP;
    private VentanaJuegoController windows;

    public Subject() throws IOException {
      //  initializer();
      //  AdjustInitInfo();
      //  startGame();

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
        this.convertArray(hand);
        this.topDeckCard = initialInfo.topDeckCard;
        this.mana = 50;
        this.life = 100;
        this.isDeck = true;
        this.inGame = true;
    }

    private void convertArray(PlayingCard[] hand){
        ListaCircularDoble<PlayingCard> listaH = new ListaCircularDoble<>();
        for(int i = 0;i<4; i++){
            listaH.addFirst(hand[i]);

        }
        this.Hand= listaH;
        this.c1 = this.Hand.getNodo(0);
        this.c2= this.Hand.getNodo(1);
        this.c3= this.Hand.getNodo(2);
        this.c4= this.Hand.getNodo(3);

    }

    public void Next(){
        this.c1 = this.c1.getNext();
        this.c2= this.c2.getNext();
        this.c3= this.c3.getNext();
        this.c4= this.c4.getNext();
    }
    public void  Previus(){
        this.c1 = this.c1.getPrevius();
        this.c2= this.c2.getPrevius();
        this.c3= this.c3.getPrevius();
        this.c4= this.c4.getPrevius();
    }

    public void send_c1()throws IOException{
        sentCart(c1.getData());
    }
    public void send_c2()throws IOException{
        sentCart(c2.getData());
    }
    public void send_c3() throws IOException {
        sentCart(c3.getData());
    }
    public void send_c4()throws IOException{
        sentCart(c4.getData());
    }

    private void InfoUpdate(Message infoFromServer) throws JsonProcessingException { //message structure "50,80" - "Life-Mana" (order)
        if(!inGame){  // Acomoda la info inicial
            SetInitialInfo(infoFromServer);
        }else{
            if(infoFromServer.permission.equals(true)){ // El usuario puede jugar
<<<<<<< Updated upstream
                
                this.windows.actualizar(infoFromServer.playerLife,infoFromServer.playerMana);
                this.turn = true;//no tocar
                this.inGame = false;//no tocar

            }
=======
                //waitForAction();
            }/*
            else {
                this.end = true;
                if (infoFromServer.action.equals("manaUpdate")) {
                    this.mana = infoFromServer.playerMana;
                }else{
                    if(infoFromServer.equals("topDeckCardUpdate")){

                    }
                }else{
                    if ()
                }
            }*/
>>>>>>> Stashed changes
        }
    }

    private void waitForAction() throws JsonProcessingException {
        /*
<<<<<<< Updated upstream
        if(true) {
            // Si el boton de saltar es presionado
            gameMessage message = new gameMessage("jumpTurn", false);
            Encoder encoder = new Encoder();
            String strMessage = encoder.encodeMessage(message);
            int connectToPort = PortToContact(this.ServersConnectionPort);
            SEND(strMessage, connectToPort);
        }
        if(true) {
            // Si el boton de deck es presionado
            gameMessage message = new gameMessage("grabCard", false);
            this.hand[5] = this.topDeckCard;
            Encoder encoder = new Encoder();
            String strMessage = encoder.encodeMessage(message);
            int connectToPort = PortToContact(this.ServersConnectionPort);
            SEND(strMessage, connectToPort);
        }
*/
        if(true) {
            // Si el boton de una carta es presionado
            gameMessage message = new gameMessage("playCard", this.cardPlayed, false);
            Encoder encoder = new Encoder();
            String strMessage = encoder.encodeMessage(message);
            int connectToPort = PortToContact(this.ServersConnectionPort);
            SEND(strMessage, connectToPort);
        }

    }

    public Boolean isTurn(){
        return  turn;
    }
    public void sentCart(PlayingCard card) throws IOException {
        if(turn==true){
            gameMessage message = new gameMessage("playCard", card, false);
            Encoder encoder = new Encoder();
            String strMessage = encoder.encodeMessage(message);
            int connectToPort = PortToContact(this.ServersConnectionPort);
            SEND(strMessage, connectToPort);
=======
        // Si el boton de saltar es presionado
        gameMessage message = new gameMessage("jumpTurn", false);
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(strMessage,connectToPort);
>>>>>>> Stashed changes


<<<<<<< Updated upstream

=======
        // Si el boton de una carta es presionado
        gameMessage message = new gameMessage("playCard", this.cardPlayed,false);
        Encoder encoder = new Encoder();
        String strMessage = encoder.encodeMessage(message);
        int connectToPort = PortToContact(this.ServersConnectionPort);
        SEND(strMessage,connectToPort);
        */
>>>>>>> Stashed changes

            this.turn=false;
            this.inGame = true;
            this.startGame();
        }

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
        Thread thread = new Thread(talker);
        thread.start();
    }
    public static Subject mainSubject(String[] strings) throws IOException {
        Subject talker = new Subject();
        Thread thread = new Thread(talker);
        thread.start();
        return talker;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            initializer();
            AdjustInitInfo();
            startGame();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setWindows(VentanaJuegoController ventanaJuegoController) {
        this.windows = ventanaJuegoController;
    }
}
