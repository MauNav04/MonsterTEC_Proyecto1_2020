import Structures.LinkedLists;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

//informacion a recibir (bool-)

public class Subject {
    //private boolean palying = false;
    private RecieverSocket listener;
    private SendingSocket deliver;
    private boolean connected = false;
    public boolean actionControl = false;// false recibir - true enviar
    public int life = 0;
    public int mana = 0;
    public LinkedLists hand;
    public LinkedLists Deck;
    public int ServersConnectionPort=2637;
    private String subjectPort;
    private String subjectIP;

    public Subject() throws UnknownHostException {
        initializer();

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


    //Primero se debe crear el listener para poder pasar el puerto como parametro

    private void initializer() throws UnknownHostException {
        HostSubject();
        String message = MessageCreator(".newPlayer-"+this.subjectPort);
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
    private String MessageCreator(String message){
        return message;
    }

    private void SEND(String message, int portToConnect) {
        deliver = new SendingSocket("", portToConnect, message);
        System.out.println("Message Sent successfully");
    }

    private void LISTEN(int port){
        listener = new RecieverSocket(port);
        String message = listener.getInfo();
        //InfoProcessor(message);
    }

    private void HostSubject() throws UnknownHostException {
        getIp();
        getPort();
    }

    private void InfoUpdate(String message){ //message structure "50,80" - "Life-Mana" (order)
        String[] splitted = message.split(",");
        int getLife = Integer.parseInt(splitted[0]);
        int getMana = Integer.parseInt(splitted[1]);
        this.life = getLife;
        this.mana = getMana;
        System.out.println(life);
        System.out.println(mana);
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

    public static void main(String args[]) throws UnknownHostException {
        Subject talker = new Subject();
    }


}
