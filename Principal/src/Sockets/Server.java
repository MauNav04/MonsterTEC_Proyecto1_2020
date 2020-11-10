package Sockets;
import Estructuras.Pila_stack;
import GameObjects.Player;
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


public class Server implements Runnable{

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
    public Player player1;
    private PlayingCard[] player1Hand;
    private Pila_stack player1Deck;

    //Player 2 attributes
    private String player2Port;
    private int player2Life;
    private int player2Mana;
    public Player player2;
    private PlayingCard[] player2Hand;
    private Pila_stack player2Deck;

<<<<<<< Updated upstream






















=======
    /**
     * Sever is the main class
     * @throws IOException
     */
>>>>>>> Stashed changes
    public Server() throws IOException {

   //     runServer();

    }

    /**
     * Run server is the methos that waiths for users and start the game
     * @throws IOException
     */
    public void runServer() throws IOException {
    //    HostServer();
        waitForUsers();
        startGame();
    }

    /**
     * Start game
     * @throws IOException
     */
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

            if(this.currentPlayer == true){
                Message permission = new Message(true,player1.getVida(),player1.getMana());
                InfoProcessor(permission,player1Port); // OJO _____________________________________________________________
                this.currentPlayer=false;

            }else {
                Message permission = new Message(true,player2.getVida(),player2.getMana());
                InfoProcessor(permission,player2Port);
                this.currentPlayer=true;
            }
            LISTEN(Integer.parseInt(serverPort));

        }
    }

    public void PlayerAnswer(){

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
            System.out.println("Initial PLayer: PLayer 2");// OJO_________________________________________________________________________________________
        }

        this.player1Life= 1000;
        this.player1Mana = 200;
        this.player2Life = 1000;
        this.player2Mana = 200;



        /*if(this.currentPlayer == true){
            //Message message = new Message("init",this.player1Hand,this.player1Deck[this.player1Deck]) //Mensaje con las cartas
            System.out.println("Its player one turn");
        }
        else {
            System.out.println("Its player one turn");
        }*/

    }

    private Boolean InitialPLayer(){
        this.player1 = new Player ("1",  1000,200,true);
        this.player1 = new Player ("2",  1000,200,false);
        return true;
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
        this.listener = new RecieverSocket(port);
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
                if(info.action == "playCard") {
                    game(info.card);
                }else if(info.action == "deck"){
                    DECK();
                }
                if(info.action.equals("grabCard")){
                    if(this.currentPlayer = true){
                        this.player2Deck.pop();
                        PlayingCard topDeckCard = (PlayingCard) this.player2Deck.peek().getData();
                        
                    }
                    else{
                        this.player1Deck.pop();
                        PlayingCard topDeckCard = (PlayingCard) this.player1Deck.peek().getData();
                    }
                }
                if(info.action.equals("jumpTurn")){
                    if(this.currentPlayer = true){
                        this.player2Mana = this.player2Mana+25;

                    }
                    else{
                        this.player1Mana = this.player1Mana+25;
                    }
                }
            }
        }

    }

    private void DECK(){


    }

    private void game(PlayingCard card) {
        if(this.currentPlayer == true){

            action(player1,card); // OJO _____________________________________________________________
            cons(player2,card);
            System.out.println("The client answered: " + card);
        }else {
            action(player2,card);
            cons(player1,card);
            System.out.println("The client answered: " + card);

        }

    }

    private void action(Player player,PlayingCard card){
        if (card.type == "A"){
            player.min_Vida(Integer.parseInt(card.attribute));
        }
    }

    private void cons(Player player,PlayingCard card){
        if (card.type == "A"){
            player.min_Mana(card.mana);
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
        getPort2();
    }

    private void getIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        String strIp = ip.toString();
        String[] splitStrIp = strIp.split("/");
        String ipNumber = splitStrIp[1];
        System.out.println("Current IP: "+ ipNumber);
        this.serverIp = ipNumber;
    }
    private void getPort2(){

        this.serverPort = "2080";


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
        server.runServer();
    }

    public static Server mainServer(String[] args) throws IOException{
        Server server = new Server();
        server.HostServer();
        Thread thread = new Thread(server);

        thread.start();
        return server;
    }


    public String getServerIp() {
        return serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void closeServer() throws IOException {
        this.listener.close();
        this.listening = false;
        this.serverPort = null;
    }

    public void setGameStarted() {
        this.gameStarted = true;
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
            runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
