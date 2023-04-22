package Server;

import Domain.Drone;
import Domain.Fire;
import java.rmi.*;
import javax.swing.*;

import Client.Client; //test import instead of broadcast/rmi for nows

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.Timer;

public class Server {

    //properties
    private static DataStorage dataStorage;
    private static GUI guiFrame;

    private static LinkedList<Drone> droneList;
    private static LinkedList<Fire> fireList;

    private static ArrayList<Connection> connectedClients;

    private static final String droneFileName = "drone.ser";
    private static final String fireFileName = "fires.csv";
    private static final int serverPort = 8888;

    private static final int timerInterval = 10*1000;

    //constructor
    Server() { }

    public static void main (String args[]) {

        droneList = new LinkedList<Drone>();
        fireList = new LinkedList<Fire>();
        connectedClients = new ArrayList<Connection>();

        dataStorage = new DataStorage();

        //need to populate arrays from text files
        fireList = dataStorage.readFiresFromFile(fireFileName);
        droneList = dataStorage.readDroneFromFile(droneFileName);

        //setup GUI
        guiFrame = new GUI();
        guiFrame.setVisible(true);

        //schedule time to redraw map every 10 seconds
        Timer mapUpdateTimer = new Timer();
        mapUpdateTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                
                guiFrame.redrawMap();

            }
            
        }, timerInterval, timerInterval);
        
        //show file loading messagesß
        guiFrame.addMessageToConsole("Loaded fires from file - " + fireList.size() + " total");
        guiFrame.addMessageToConsole("Loaded drones from file - " + droneList.size() + " total");

        //start listening for incoming connection
        try {
            
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true){

                //accept the connection
                Socket clientSocket = listenSocket.accept();

                System.out.println("socket connected");

                //create connection instance to handle client communication
                Connection connection = new Connection(clientSocket, droneList);
                
                //store reference to connection for later
                connectedClients.add(connection);

            }
        }
        catch(IOException e){
            System.out.println("Listen socket: "+e.getMessage());
        }
        
    }

    //shutdown the server & recall all drones
    public static void shutdown(){

        //save fires to file
        dataStorage.writeFiresToFile(fireFileName, fireList);

        //save drones to file
        dataStorage.writeDroneToFile(droneFileName, droneList);

        //System.out.println("All Drones RTB");

        guiFrame.addMessageToConsole("Drones recalled to base");

        for (Connection connection : connectedClients) {

            connection.recallDroneBackToBase();
            connection.shutdown();
        }

        guiFrame.addMessageToConsole("All drones back at base");
        
        guiFrame.addMessageToConsole("Shutting down ...");

        System.exit(0);
    }

    //admin controls
    
    public static void deleteFire(){

        //  delete fire from linked list - have commented code for testing .csv deletion?
        Integer delFireID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the FireID you wish to delete."));

        Fire toBeDeletedFire = fireList.stream()
            .filter(fire -> delFireID.equals(Integer.valueOf(fire.getfireID())))
            .findAny()
            .orElse(null);

        if (toBeDeletedFire == null) {
        
            //let user know that fire doesn't exist with ID
            System.out.println("No fire exists with ID: " + delFireID);
            guiFrame.addMessageToConsole("No fire exists with ID: " + delFireID);
        
        } else {

            //delete the fire that matches
            fireList.remove(toBeDeletedFire);

            //let user know that fire was deleted
            System.out.println("Fire deleted for ID: " + delFireID);
            guiFrame.addMessageToConsole("Fire deleted for ID: " + delFireID);

        }

        //get map to redraw
        guiFrame.redrawMap();

        // fireList.remove(delFireID); //just removes fire at the position specified for now
        // System.out.println("Fire with ID: " + delFireID + "Deleted"); //needs to print to GUI
    }

    //todo: recall all
    public static void recallAll(){
        
        //System.out.println("All Drones RTB");

        guiFrame.addMessageToConsole("Drones recalled to base");

        for (Connection connection : connectedClients) {

            connection.recallDroneBackToBase();

        }

        guiFrame.addMessageToConsole("All drones back at base");
        

    }

    //todo: move drone
    public static void moveDrone(){
        int droneID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Drone ID for the drone you want to move."));
        int droneXPos = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new x Coord."));
        int droneYPos = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new y Coord."));
        
        if((droneXPos <= 400 && droneXPos >= 0) && (droneYPos <= 400 && droneYPos >= 0)){
            //Drone.setDroneXPos(droneXPos); 
            //Drone.setDroneYPos(droneYPos);
            JOptionPane.showMessageDialog(null, "Drone " + droneID + "is moved to (x,y): " + droneXPos + ", " + droneYPos);
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter an x and y value between 0 and 400. Drone returning to previous location.");
        }

        //get map to redraw
        guiFrame.redrawMap();
    }

    public static void addMessageToConsoleInGUI(String message) {
        guiFrame.addMessageToConsole(message);
    }

    public static LinkedList<Fire> getFireList() {
        return Server.fireList;
    }

    public static LinkedList<Drone> getDroneList() {
        return Server.droneList;
    }
}
