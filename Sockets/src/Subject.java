import javax.swing.*;
import java.net.UnknownHostException;
import java.util.Scanner;

//informacion a recibir (bool-)

public class Subject {
    //private boolean palying = false;
    private RecieverSocket listener;
    private SendingSocket dealer;
    public boolean actionControl = false;// false recibir - true enviar
    public int life = 0;
    public int mana = 0;
    public LinkedLists hand;
    public LinkedLists Deck;

    public Subject() {
        ActionPerformer();

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



    private int PortToContact(){
        System.out.println("Select a port to send: ");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        return port;
    }

    private String MessageCreator(){
        System.out.println("Type a message: ");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        return message;
    }

    // 0 para recibir mensaje - 1 para enviar mensaje
    /*private void ActionSelector(){
        System.out.println("Select an action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        if(action == 1) ActionChanger();
    }*/

    private void ActionPerformer(){
        if(this.getClass().getCanonicalName() == "Subject"){
            listenAction();
        }
    }

    private void listenAction (){
        int port = PortSelector();
        listener = new RecieverSocket(port);
        InfoUpdate(listener.getInfo());
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

    private int PortSelector(){
        System.out.println("Insert a port to host: ");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        return port;
    }

    private void ActionChanger (){
        this.actionControl = !actionControl;
    }

    public static void main(String args[]){
        Subject talker = new Subject();
    }


}
