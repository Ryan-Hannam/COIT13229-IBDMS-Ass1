package Client;

import Domain.Drone;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client { //unsure why this throws error - should match java naem now ...
    private LinkedList<Drone> droneList = new LinkedList<>();
    
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
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
        // Scanner sa = new Scanner(System.in);
        // System.out.println("Please register drone.");
        // System.out.println("Enter Drone ID:");
        // int droneID = Integer.parseInt(sa.nextLine());
        // System.out.println("Enter Drone Name:");
        // String droneName = sa.nextLine();
        // System.out.println("Enter Drone xPos:");
        // double droneXPos = Double.parseDouble(sa.nextLine());
        // System.out.println("Enter Drone yPos:");
        // double droneYPos = Double.parseDouble(sa.nextLine());
        //while (true) fire report system (input x,y then report?)
    }

    public String registerDrone(Drone drone){    
        String response = "";
        droneList.add(drone);
        response = sendDataToServer();
        return response;
    }

    public void dronePosUpdate(){
        //private double droneXPos = Drone.getDroneXPos();
        //private double droneYPos = Drone.getDroneYPos();
        //send drone XPos,YPos to server
    }

    public void fireDetection(){
        System.out.println("Fire detected, sending to server");
        //send fireID,posX,posY to server
    }

    public static void acknowldegeRecall(){
        System.out.println("Drone RTB");
        System.exit(0);
    }

    public String sendDataToServer(){
        if (droneList.size() > 0) {
            try {
                objectOutputStream.writeObject("register");
                objectOutputStream.writeObject(droneList);
                droneList = new LinkedList<>();
                String response = (String) objectInputStream.readObject();
                return response;
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
        return "Failed to Send Data to Server";
    }

    protected void finalise(){
        sendDataToServer();
    }
}