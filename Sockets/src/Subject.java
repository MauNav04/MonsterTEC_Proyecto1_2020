import java.net.UnknownHostException;
import java.util.Scanner;

public class Subject {
    private RecieverSocket listener;
    private SendingSocket dealer;
    private boolean actionControl = false;// false recibir - true enviar

    private Subject() {
        ActionSelector();

        if(actionControl){
            int port = PortToContact();
            String message = MessageCreator();
            dealer = new SendingSocket("",port,message);
        }
        else {
            int port = PortSelector();
            listener = new RecieverSocket(port);
        }
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
    private void ActionSelector(){
        System.out.println("Select an action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        if(action == 1) ActionChanger();
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
