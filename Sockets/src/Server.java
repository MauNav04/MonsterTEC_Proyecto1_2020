import GameObjects.PlayingCard;
import JSON.Decoder;
import JSON.Message;
import Structures.Pila_stack;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
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
        waitForUsers();
        startGame();

    }

    private void startGame() throws IOException {
        System.out.println("Game starts NOW!!");
        startProtocol();
        while(gameStarted){
            System.out.println("This gets to the while");
            gameStarted = !gameStarted;
        }
    }

    private void startProtocol() throws IOException {
        Decoder decoder = new Decoder();
        PlayingCard[] completeDeck = decoder.DecodeCardFile(); // Ya se tienen todas las cartas guardadas
        CardDealer(completeDeck, false);
        this.currentPlayer = InitialPLayer();
        if(this.currentPlayer == true){
        Message message = new Message("init",this.player1Hand,this.player1Deck[this.player1Deck]);
        }

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

    private void waitForUsers() throws JsonProcessingException {
        while(!gameStarted) {
            LISTEN(Integer.parseInt(this.serverPort));
        }
    }

    private void SEND(String message, int portToConnect) {
        deliver = new SendingSocket("", portToConnect, message);
        System.out.println("Message Sent successfully");
    }

    private void LISTEN(int port) throws JsonProcessingException {
        listener = new RecieverSocket(port);
        String message = listener.getInfo();
        Decoder decoder = new Decoder();
        Message extractedInfo = decoder.Decode(message);
        InfoProcessor(extractedInfo);
    }

    private void InfoProcessor(Message info){
        if(!gameStarted){
            AddUser(info);
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
        //server.startProtocol();
    }
}
