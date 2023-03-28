package Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class client {
    public static void main (String args[]){
        Socket s = null;
        String hostName = "localhost";
        String message = "Drone connected";
        try{
            int serverPort = 7896;
            s = new Socket (hostName, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message);
            String data = in.readUTF();
            System.out.println("Received message from: " + data);
        }
        catch (UnknownHostException e){
            System.out.println("Sock: "+e.getMessage());
        }
        catch (EOFException e){
            System.out.println("EOF "+e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO: "+e.getMessage());
        }
        finally {
            if(s!=null)
            try{
                s.close();
            }
            catch (IOException e){
                System.out.println("close: "+e.getMessage());
            }
        }
    }
}

//todo: registar drone
//todo: drone position update
//todo: fire detection message
//todo: achknowledge recall