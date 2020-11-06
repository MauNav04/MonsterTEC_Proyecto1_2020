package Sockets;
import GameObjects.PlayingCard;
import JSON.Decoder;
import JSON.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    /* player1Hand
       player1Deck
     */
    //Player 2 attributes
    private String player2Port;
    private int player2Life;
    private int player2Mana;
    /* player2Hand
       player2Deck
     */

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
        //System.out.println("completeDeck:" + completeDeck);
        System.out.println(completeDeck[0]);
    }

    private void waitForUsers() throws JsonProcessingException {
        while(!gameStarted) {
            LISTEN(Integer.parseInt(this.serverPort));
        }
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
    }
}
