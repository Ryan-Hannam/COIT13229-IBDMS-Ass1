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

public class Server {

    //properties
    private static DataStorage dataStorage;
    private static GUI guiFrame;

    private static LinkedList<Drone> droneList;
    private static LinkedList<Fire> fireList;

    private static final String droneFileName = "drone.ser";
    private static final String fireFileName = "fires.csv";
    private static final int serverPort = 7896;

    //constructor
    Server() { }

    public static void main (String args[]) {

        droneList = new LinkedList<Drone>();
        fireList = new LinkedList<Fire>();

        dataStorage = new DataStorage();

        //need to populate arrays from text files
        fireList = dataStorage.readFiresFromFile(fireFileName);
        // droneList = dataStorage.readDroneFromFile(droneFileName);

        //setup GUI
        guiFrame = new GUI();
        guiFrame.setVisible(true);
        
        //show file loading messagesß
        guiFrame.addMessageToConsole("Loaded fires from file - " + fireList.size() + " total");
        guiFrame.addMessageToConsole("Loaded drones from file - " + droneList.size() + " total");

        //start listening for incoming connection
        try {
            
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true){

                Socket clientSocket = listenSocket.accept();

                System.out.println("socket connected");

                Connection connection = new Connection(clientSocket, droneList);
            }
        }
        catch(IOException e){
            System.out.println("Listen socket: "+e.getMessage());
        }
        
    }

    //shutdown the server & recall all drones
    public static void shutdown(){

        Client.acknowldegeRecall(); //if in production - should be an rmi function
        System.exit(0);
        

    }

    //admin controls
    
    //todo: delete fire
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

    // fireList.remove(delFireID); //just removes fire at the position specified for now
    // System.out.println("Fire with ID: " + delFireID + "Deleted"); //needs to print to GUI
    }

    //todo: recall all
    public static void recallAll(){
        
        Client.acknowldegeRecall();
        System.out.println("All Drones RTB");

    }

    //todo: move drone
    public static void moveDrone(){
        int droneID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Drone ID for the drone you want to move."));
        int droneXPos = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new x Coord."));
        int droneYPos = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new y Coord."));
        if(droneXPos <= 100 && droneYPos <= 100){
            //Drone.setDroneXPos(droneXPos);
            //Drone.setDroneYPos(droneYPos);
            JOptionPane.showMessageDialog(null, "Drone " + droneID + "is moved to (x,y): " + droneXPos + ", " + droneYPos);
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter an x and y value below 100");
        }
    }

    public static void addMessageToConsoleInGUI(String message) {
        guiFrame.addMessageToConsole(message);
    }
}

//server core
//todo: load data from fires.csv
//todo: load saved drone binary
//todo: receive drone reg
//todo: save drone details
//todo: display position (print into GUI)
//todo: send instructions to any drone