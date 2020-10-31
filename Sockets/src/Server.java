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

    public Server() throws UnknownHostException {
        HostServer();
        waitForUsers();
        startGame();

    }

    private void startGame(){
        System.out.println("Game starts NOW!!");
    }

    private void waitForUsers(){
        while(!gameStarted) {
            LISTEN(Integer.parseInt(this.serverPort));
        }
    }

    private void LISTEN(int port){
        listener = new RecieverSocket(port);
        String message = listener.getInfo();
        InfoProcessor(message);
    }

    private void InfoProcessor(String info){
        String[] splittedInfo = info.split("-");

        if(!gameStarted){
            AddUser(splittedInfo);
        }

    }

    private void AddUser(String[] splittedInfo){
        if(splittedInfo[0].equals(".newPlayer")){
            if(this.player1Port == null){
                this.player1Port = splittedInfo[1];
                System.out.println("Player ADDED SUCCESFULLY");
            }
            else {
                this.player2Port = splittedInfo[1];
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

    public static void main(String[] args) throws UnknownHostException {
        Server server= new Server();
    }
}
