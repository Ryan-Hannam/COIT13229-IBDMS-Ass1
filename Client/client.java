package Client;

import java.rmi.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
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
        catch (java.net.UnknownHostException e){
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

    public String registerDrone(Drone drone){    
        Scanner sa = new Scanner(System.in);
        String response = "Problem with Server Connection";
        System.out.println("Enter Drone ID");
        String droneID = sa.nextLine();
        
        return response;
    }

    public void dronePosUpdate(){
        //private drone xPOS = drone.getDroneXPos();
        //private drone yPOS = drone.getDroneYPos();
        //send drone XPos,YPos to server
    }

    public void fireDetection(){
        System.out.println("Fire detected, sending to server");
        //send fireID,posX,posY to server
    }

    public void acknowldegeRecall(){
        //if condition to check for received message?
        System.out.println("Drone RTB");
        System.exit(0);
    }
}

//todo: register drone
//todo: drone position update
//todo: fire detection message
//todo: achknowledge recall