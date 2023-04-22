package Client;

import Domain.Fire;
import Domain.Drone;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client { //unsure why this throws error - should match java naem now ...
    
    private static LinkedList<Drone> droneList = new LinkedList<>();
    
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    private static int serverPort = 8888;
    private static String hostName = "localhost";

    private static Scanner scanner = new Scanner(System.in);

    private static Drone drone;

    public static void main (String args[]) throws InterruptedException {

        Socket clientSocket = null;
        String message = "Drone connected";

        int droneID = 0;
        String droneName = "";
        double droneXPos = 0;
        double droneYPos = 0;

        //ask for drone name and store it
        System.out.println("Please enter drone's name");
        droneName = scanner.nextLine();

        //ask for drone id and store it
        System.out.println("Please enter drone's ID");
        droneID = scanner.nextInt();

        System.out.println("Please enter drone's x coord:");
        droneXPos = scanner.nextDouble();

        System.out.println("Please enter drone's y coord:");
        droneYPos = scanner.nextDouble();

        if((droneXPos <= 400 && droneXPos >= 0) && (droneYPos <= 400 && droneYPos >= 0)){
            //create a new drone for instance of client using the provided info
            drone = new Drone(droneID, droneName, droneXPos, droneYPos);
        }
        else{
            System.out.println("Please enter an x and y coord between 0 and 100");
            System.exit(0);
        }

        

        try {

            clientSocket = new Socket (hostName, serverPort);

            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            

            // System.out.println(droneName);
            // System.out.println(droneID);

            //send drone to server
            objectOutputStream.writeObject(drone);
            System.out.println("Sent drone data to server");

            while (true) {

                try {

                    String serverOption = (String) objectInputStream.readObject();

                    if (serverOption.equalsIgnoreCase("DroneReturnToBase")) {

                        acknowldegeRecall();

                    }

                    if (serverOption.equalsIgnoreCase("Shutdown")){

                        System.out.println("Received Shutdown from Server");
                        acknowldegeRecall();
                        System.exit(0);

                    }

                    // System.out.println(objectInputStream.readObject());
                    // System.out.println();

                    // String data = objectInputStream.readUTF();
                    // System.out.println("Received message from: " + data);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // objectOutputStream.writeUTF(message);

            }

            

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
            if(clientSocket!=null)
            try{
                clientSocket.close();
            }
            catch (IOException e){
                System.out.println("close: "+e.getMessage());
            }
        }
        
        //timer to update drone pos every 10000 milliseconds - called after drone registration
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                dronePosUpdate();
            }
        };
        timer.schedule(task, 0, 10000);
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

    public void fireDetection(int fireID, double fireXPos, double fireYPos, int fireDroneID, int fireSeverity){
        System.out.println("Fire detected, sending to server");
        Fire newFire = new Fire(fireID, fireXPos, fireYPos, fireDroneID, fireSeverity);
        String response = sendDataToServer();
    }

    private static void acknowldegeRecall(){
        System.out.println("Drone RTB");
        //System.exit(0);
        //tood: update drone X and Y to 0,0
    }

    public String sendDataToServer(){
        if (droneList.size() >= 0) {
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