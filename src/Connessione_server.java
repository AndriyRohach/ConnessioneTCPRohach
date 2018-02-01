/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrea
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Connessione_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        
        GregorianCalendar gc = new GregorianCalendar(); //calendario 
        
        while(true){
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                DataInputStream in= null;
                DataOutputStream out= null;
                in = new DataInputStream(connection.getInputStream());
                out = new DataOutputStream(connection.getOutputStream());
    
                System.out.println("Client:" + in.readUTF());
                out.writeUTF("Ecco la data:" + gc.get(Calendar.DAY_OF_MONTH) +"/" + (gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.YEAR) + "");
                out.flush(); //mando il messaggio svuotando il buffer
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
      }
}
