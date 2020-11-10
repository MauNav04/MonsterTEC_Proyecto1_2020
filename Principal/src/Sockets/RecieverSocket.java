package Sockets;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RecieverSocket {
    private Socket socket   = null;
    private ServerSocket receiverSocket   = null;
    private DataInputStream streamIn =  null;
    String message = null;
    String clientsPort = null;

    /**
     * Reciever Socket: Es el constructor de un socket que se conecta a un puerto preestablecido, escucha en este
     * puerto y cuando alguien se conecta, obtiene la info del mismo.
     * @param port
     * @throws IOException
     */
    public RecieverSocket(int port) throws IOException {
        boolean done = false;
        try
            {   //while(done == false) {
                System.out.println("Binding to port " + port + ", please wait  ...");
                receiverSocket = new ServerSocket(port);
                System.out.println("Listener started: " + receiverSocket);
                System.out.println("Listening on port ... " + port);
                socket = receiverSocket.accept();
                System.out.println("Client accepted: " + socket);
                open();
                //boolean done = false;
                //while (!done)
                String line = streamIn.readUTF();
                int clientsPortInt = socket.getLocalPort();
                clientsPort = Integer.toString(clientsPortInt);
                System.out.println("Message from " + clientsPort + ": " + line);
                /*if(line == ".newPlayer"){
                    int newPort = socket.getPort();
                    System.out.println("Clients Port: " + newPort);

                }*/
                message = line;
                done = line.equals(".bye");
                reboot();
            //}
            System.out.println("Info received...");
        } catch(IOException ioe)
            {  System.out.println(ioe);
            }

        close();
    }

    /**
     * Método publico que obtiene la información recibida al Socket y la envia a otras clases
     * @return String message
     */
    public String getInfo (){
        return message;
    }

    /**
     * Empieza a escuchar lo que la información del Socket luego de una aceptar una conexion
     * @throws IOException
     */
    public void open() throws IOException
    {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * Termina de escuchar y reinicia los valores del socket y el inputStream
     * @throws IOException
     */
    public void reboot() throws IOException
    {   socket.close();
        streamIn.close();
        receiverSocket.close();
        System.out.println("Cleaning ...");
    }

    /**
     * Termina de escuchar y cierra el server
     * @throws IOException
     */
    public void close() throws IOException
    {   socket.close();
        streamIn.close();
        receiverSocket.close();
        System.out.println("Closing server...");
    }


    public static void main(String args[]) throws IOException {
        RecieverSocket server = null;
        server = new RecieverSocket(2453);
    }
}

