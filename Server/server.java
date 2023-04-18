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
    private DataStorage dataStorage;
    private static GUI guiFrame;

    private static LinkedList<Drone> droneList;
    private static LinkedList<Fire> fireList;

    private static final String droneFileName = "drone.ser";
    private static final String fireFileName = "fires.csv";
    private static final int serverPort = 7896;

    //constructor
    Server() {

        dataStorage = new DataStorage();

        //need to populate arrays from text files
        fireList = dataStorage.readFiresFromFile(fireFileName);

    }

    public static void main (String args[]) {

        //setup GUI
        GUI guiFrame = new GUI();
        guiFrame.setVisible(true);

        //Test populating the console log panel
        guiFrame.addMessageToConsole("test");

        //start listening for incoming connection
        try {
            
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true){
                Socket clientSocket=listenSocket.accept();
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

    //admin
    //todo: delete fire
    // public static void deleteFire(){
    //    delete fire from linked list - have commented code for testing .csv deletion?
    // }

    //todo: recall all
    // public static void recallAll(){
    //     foreach?
    //     sendrecall()
    // }

    //todo: move drone
    // public static void moveDrone(){
    //     setDroneXPos
    //     setDroneYPos
    //     sendtoDrone
    // }
}

//server core
//todo: load data from fires.csv
//todo: load saved drone binary
//todo: receive drone reg
//todo: save drone details
//todo: display position (print into GUI)
//todo: send instructions to any drone