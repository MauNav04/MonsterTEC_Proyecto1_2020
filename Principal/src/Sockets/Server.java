package Sockets;
import Estructuras.Pila_stack;
import GameObjects.PlayingCard;
import JSON.Decoder;
import JSON.Encoder;
import JSON.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Server {

    //Server atributes
    private String serverIp;
    private String serverPort;
    private RecieverSocket listener;
    private SendingSocket delivery;
    private Boolean currentPlayer;
    private Boolean gameStarted = false;
    private Boolean listening = false;
    /* currentCardType
       currentDamage
       currentSpell
       currentSecret
     */
    //Player 1 attributes
    private String player1Port;
    //private PlayingCard[];
    private int player1Life;
    private int player1Mana;
    private PlayingCard[] player1Hand;
    private Pila_stack player1Deck;

    //Player 2 attributes
    private String player2Port;
    private int player2Life;
    private int player2Mana;
    private PlayingCard[] player2Hand;
    private Pila_stack player2Deck;

    public Server() throws IOException {
        HostServer();
        runServer();

    }

    public void runServer() throws IOException {
         waitForUsers();
         startGame();
    }

    private void startGame() throws IOException {
        System.out.println("Game starts NOW!! - Server");
        startProtocol();
        while(gameStarted){
            System.out.println("This gets to the while");
            communicationsLoop(false);
            //gameStarted = !gameStarted;
        }
    }

    private void communicationsLoop(Boolean end) throws IOException {
        while(!end){
            System.out.println("Permission Message fails");
            Message permission = new Message(true);
            if(this.currentPlayer == true){
                InfoProcessor(permission,player1Port);
            }else {
                InfoProcessor(permission,player2Port);
            }
            LISTEN(Integer.parseInt(serverPort));


        }
    }

    public void startProtocol() throws IOException {
        //Sacar cartas del archivo
        Decoder decoder = new Decoder();
        PlayingCard[] completeDeck = decoder.DecodeCardFile(); // Ya se tienen todas las cartas guardadas
        CardDealer(completeDeck, false);
        //Repartir cartas a cada jugador e información necesaria
        Message startingInfoP1 = new Message("init",this.player1Hand, (PlayingCard) this.player1Deck.peek().getData());
        InfoProcessor(startingInfoP1,this.player1Port);
        Message startingInfoP2 = new Message("init",this.player2Hand, (PlayingCard) this.player2Deck.peek().getData());
        InfoProcessor(startingInfoP2,this.player2Port);
        //Elegir cual jugador inicia
        this.currentPlayer = InitialPLayer();
        if(this.currentPlayer == true){
            System.out.println("Initial PLayer: PLayer 1");
        }else{
            System.out.println("Initial PLayer: PLayer 2");
        }

        /*if(this.currentPlayer == true){
            //Message message = new Message("init",this.player1Hand,this.player1Deck[this.player1Deck]) //Mensaje con las cartas
            System.out.println("Its player one turn");
        }
        else {
            System.out.println("Its player one turn");
        }*/

    }

    private Boolean InitialPLayer(){
        Random randomInstance = new Random();
        Integer randomPlayer = randomInstance.nextInt((2 - 1) + 1) + 1;
        String strPlayer = randomPlayer.toString();
        if(strPlayer.equals("1")){
            return true;
        }
        return false;
    }

    private void CardDealer(PlayingCard[] completeDeck, Boolean finishDist){
        int round = 1;
        while(!finishDist) {
            Integer[] pickedCards = CardPicker(); //posiciones que se escogen
            PlayingCard[] hand = new PlayingCard[10];
            Pila_stack deck = new Pila_stack();

            for (int i = 0; i < 4; i++) {
                PlayingCard selectedCard = completeDeck[pickedCards[i]];
                hand[i] = selectedCard;
            }

            for (int i = 4; i < pickedCards.length; i++) {
                PlayingCard selectedCard = completeDeck[pickedCards[i]];
                deck.push(selectedCard);
            }

            if (round == 1){
                this.player1Hand = hand;
                this.player1Deck = deck;
                round++;
            }else {
                this.player2Hand = hand;
                this.player2Deck = deck;
                finishDist = true;
            }

        }

    }


    private Integer[] CardPicker(){
        Integer[] cardPos = new Integer[20];

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for(int i=0; i<30; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for(int i=0; i<20; i++){
            int currentNumber = numbers.get(i);
            cardPos[i] = currentNumber;
        }

        return cardPos;
    }


    public void waitForUsers() throws IOException {
        while(!gameStarted) {
            LISTEN(Integer.parseInt(this.serverPort));
        }
    }

    private void SEND(String message, int portToConnect) {
        delivery = new SendingSocket("", portToConnect, message);
        System.out.println("Message Sent to Player successfully");
    }

    private void LISTEN(int port) throws IOException {
        this.listening = true;
        listener = new RecieverSocket(port);
        String message = listener.getInfo();
        Decoder decoder = new Decoder();
        Message extractedInfo = decoder.Decode(message);
        InfoProcessor(extractedInfo,null);
    }

    private void InfoProcessor(Message info, String port) throws JsonProcessingException {
        if(!gameStarted){
            AddUser(info);
        }else{
            if (this.listening == false) {
                Encoder encoder = new Encoder();
                String jsonString = encoder.encodeMessage(info);
                SEND(jsonString, Integer.parseInt(port));
            }
            else{
                this.listening = false;
                System.out.println("The client answered: " + info);
            }
        }

    }

    private void AddUser(Message info){
        if(info.action.equals(".newPlayer")){
            if(this.player1Port == null){
                this.player1Port = info.port;
                System.out.println("Player ADDED SUCCESFULLY");
            }
            else {
                this.player2Port = info.port;
                this.listening = false;
                gameStarted = true;
                System.out.println("Player ADDED SUCCESFULLY");
            }
        }
    }


    private void HostServer() throws UnknownHostException {
        getIp();
        getPort();
    }

    private void getIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        String strIp = ip.toString();
        String[] splitStrIp = strIp.split("/");
        String ipNumber = splitStrIp[1];
        System.out.println("Current IP: "+ ipNumber);
        this.serverIp = ipNumber;
    }

    private void getPort(){
        Random randomInstance = new Random();
        Integer randomPort = randomInstance.nextInt((4000 - 2000) + 1) + 2000;
        String strPort = randomPort.toString();
        System.out.println("Current Port: "+ strPort);
        this.serverPort = strPort;
    }

    public static void main(String[] args) throws IOException {
        Server server= new Server();
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setGameStarted() {
        this.gameStarted = true;
    }
}
