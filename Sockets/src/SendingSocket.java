import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendingSocket {
    private Socket socket              = null;
    private DataInputStream console   = null;
    private DataOutputStream streamOut = null;

    /**
     * Constructor de Sockets.ChatClient que crea conexión con el servidor y le envía un mensaje.
     * @param ip ip del host del servidor
     * @param serverPort en el que escucha el servidor
     * @param message mensaje que se queire enviar al servidor
     */
    public SendingSocket(String ip, int serverPort, String message)
    {  System.out.println("Establishing connection. Please wait ...");
        try //InetAddress serverName//client = new Sockets.ChatClient(InetAddress.getLocalHost(), i, message);
        {   if(ip == "") {
            InetAddress serverName = InetAddress.getLocalHost();
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        }else{
            socket = new Socket(ip, serverPort);
            System.out.println("Connected: " + socket);
            start();
        }
        }
        catch(UnknownHostException uhe)
        {  System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe)
        {  System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        try
        {   streamOut.writeUTF(message);
            streamOut.flush();
        }
        catch(IOException ioe)
        {  System.out.println("Sending error: " + ioe.getMessage());
        }

        System.out.println("message sent...connection finished");
        stop();
    }

    /**
     * Determina los valores delinput stream y output stream para enviar información al servidor.
     * @throws IOException
     */
    public void start() throws IOException
    {  console   = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

    public void stop()
    {  try
    {  if (console   != null)  console.close();
        if (streamOut != null)  streamOut.close();
        if (socket    != null)  socket.close();
    }
    catch(IOException ioe)
    {  System.out.println("Error closing ...");
    }
    }

    public static void main(String args[]) throws UnknownHostException {
        SendingSocket client = null;
        client = new SendingSocket("127.0.1.1", 2313, ".newPlayer-3010");
    }
}
