package Client;

import Domain.Drone;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client { //unsure why this throws error - should match java naem now ...
    private LinkedList<Drone> droneList = new LinkedList<>();
    
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private static int serverPort = 7896;
    private static String hostName = "localhost";

    private static Scanner scanner = new Scanner(System.in);

    public static void main (String args[]) throws InterruptedException {

        Socket s = null;
        String message = "Drone connected";

        int droneID = 0;
        String droneName = "";
        int droneXPos = 0;
        int droneYPos = 0;

        try {

            //ask for drone name and store it
            System.out.println("Please enter drone's name");
            droneName = scanner.nextLine();

            //ask for drone id and store it
            System.out.println("Please enter drone's ID");
            droneID = scanner.nextInt();

            System.out.println(droneName);
            System.out.println(droneID);

            //create a new drone for instance of client using the provided info
            Drone newdrone = new Drone(droneID, droneName, droneXPos, droneYPos);
            
            s = new Socket (hostName, serverPort);

            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            //send drone to server
            out.writeObject(newdrone);

            try {

                System.out.println(in.readObject());
                System.out.println();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // out.writeUTF(message);

            // String data = in.readUTF();
            // System.out.println("Received message from: " + data);

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
        
        //timer to update drone pos every 1000 milliseconds - called after drone registration
        // Timer timer = new Timer();
        // TimerTask task = new TimerTask(){
        //     public void run(){
        //         dronePosUpdate();
        //     }
        // };
        // timer.schedule(task, 0,1000);
    }

    public String registerDrone(Drone drone){    
        String response = "";
        droneList.add(drone);
        response = sendDataToServer();
        return response;
    }

    public static void dronePosUpdate(){       
        //double droneXPos = Drone.getDroneXPos(); <-- need to somehow call this as a non-static function ...
        //double droneYPos = Drone.getDroneYPos();
        //send drone XPos,YPos to server
        System.out.println("Drone Position (x,y):"+"xPos"+","+"yPos"+" Sent to Server");
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