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
        guiFrame = new GUI();

        //need to populate arrays from text files
        fireList = dataStorage.readFiresFromFile(droneFileName);

    }

    public static void main (String args[]) {

        //setup GUI
        guiFrame.setVisible(true);

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

//GUI code to be relocated here. Pasted As-Is for now
// package Server;

// import javax.swing.*;
// import java.awt.*;

// public class GUI extends JFrame { //unsure why this throws error
//     private JButton bDeleteFire;
//     private JButton bMoveDrone;
//     private JButton bRecallAll;
//     private JButton bShutdown;
//     private JLabel lFunctions;
//     private JLabel lInformation;
//     private JPanel pFunctions;
//     private JPanel pInformation;
//     private JPanel pMap;
//     private JPanel pWestPane;
//     public JTextField tInformation;

//     public GUI() {
//         initComponents();
//         try {
//             for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                 if ("Nimbus".equals(info.getName())) {
//                     UIManager.setLookAndFeel(info.getClassName());
//                     break;
//                 }
//             }
//         } 
//         catch (ClassNotFoundException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (InstantiationException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (IllegalAccessException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (UnsupportedLookAndFeelException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         }

//         // create and display form
//         EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 new GUI().setVisible(true);
//             }
//         });
//     }
                         
//     public void initComponents() {
//         //create components
//         pWestPane = new JPanel();
//         pFunctions = new JPanel();
//         lFunctions = new JLabel();
//         bDeleteFire = new JButton();
//         bMoveDrone = new JButton();
//         bRecallAll = new JButton();
//         bShutdown = new JButton();
//         pInformation = new JPanel();
//         lInformation = new JLabel();
//         tInformation = new JTextField();
//         pMap = new JPanel();

//         //set the x button to close the applications
//         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //change this to call the shutdown() function?

//         //set text for labels and buttons
//         lInformation.setText("Info and Messages");
//         lFunctions.setText("Functional Controls");
//         bDeleteFire.setText("Delete Fire");
//         bMoveDrone.setText("Move Drone");
//         bRecallAll.setText("Recall All");
//         bShutdown.setText("Shutdown");

//         //set borders for the panes
//         pMap.setBorder(BorderFactory.createLineBorder(null));
//         pWestPane.setBorder(BorderFactory.createLineBorder(null));

//         //layout setters and component adding for each pane
//         GroupLayout pFunctionsLayout = new GroupLayout(pFunctions);
//         pFunctions.setLayout(pFunctionsLayout);
//         pFunctionsLayout.setHorizontalGroup(
//             pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(pFunctionsLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                     .addComponent(lFunctions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                     .addGroup(pFunctionsLayout.createSequentialGroup()
//                         .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//                             .addComponent(bDeleteFire, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                             .addComponent(bMoveDrone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                         .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                             .addGroup(pFunctionsLayout.createSequentialGroup()
//                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                 .addComponent(bShutdown))
//                             .addGroup(pFunctionsLayout.createSequentialGroup()
//                                 .addGap(4, 4, 4)
//                                 .addComponent(bRecallAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
//                 .addContainerGap())
//         );

//         pFunctionsLayout.setVerticalGroup(
//             pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(pFunctionsLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addComponent(lFunctions)
//                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                 .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                     .addComponent(bDeleteFire)
//                     .addComponent(bRecallAll))
//                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                 .addGroup(pFunctionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                     .addComponent(bMoveDrone)
//                     .addComponent(bShutdown))
//                 .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//         );

//         GroupLayout pInformationLayout = new GroupLayout(pInformation);
//         pInformation.setLayout(pInformationLayout);
//         pInformationLayout.setHorizontalGroup(
//             pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(pInformationLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addGroup(pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                     .addComponent(tInformation)
//                     .addGroup(pInformationLayout.createSequentialGroup()
//                         .addComponent(lInformation)
//                         .addGap(0, 0, Short.MAX_VALUE)))
//                 .addContainerGap())
//         );
//         pInformationLayout.setVerticalGroup(
//             pInformationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(pInformationLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addComponent(lInformation)
//                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                 .addComponent(tInformation, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
//                 .addContainerGap())
//         );

//         GroupLayout pWestPaneLayout = new GroupLayout(pWestPane);
//         pWestPane.setLayout(pWestPaneLayout);
//         pWestPaneLayout.setHorizontalGroup(
//             pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(GroupLayout.Alignment.TRAILING, pWestPaneLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addGroup(pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                     .addComponent(pInformation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                     .addComponent(pFunctions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                 .addContainerGap())
//         );
//         pWestPaneLayout.setVerticalGroup(
//             pWestPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(pWestPaneLayout.createSequentialGroup()
//                 .addContainerGap()
//                 .addComponent(pFunctions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                 .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//                 .addComponent(pInformation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                 .addContainerGap())
//         );

//         GroupLayout pMapLayout = new GroupLayout(pMap);
//         pMap.setLayout(pMapLayout);
//         pMapLayout.setHorizontalGroup(
//             pMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGap(0, 541, Short.MAX_VALUE)
//         );
//         pMapLayout.setVerticalGroup(
//             pMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGap(0, 0, Short.MAX_VALUE)
//         );

//         GroupLayout layout = new GroupLayout(getContentPane());
//         getContentPane().setLayout(layout);
//         layout.setHorizontalGroup(
//             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(layout.createSequentialGroup()
//                 .addContainerGap()
//                 .addComponent(pWestPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                 .addComponent(pMap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                 .addContainerGap())
//         );
//         layout.setVerticalGroup(
//             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                 .addContainerGap()
//                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                     .addComponent(pMap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                     .addComponent(pWestPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                 .addContainerGap())
//         );
//         //end of layout setting and component adding

//         //button action listeners
//         bDeleteFire.addActionListener(new java.awt.event.ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent evt) {
//                 bDeleteFireActionPerformed(evt);
//             }
//         });
        
//         bRecallAll.addActionListener(new java.awt.event.ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent evt) {
//                 bRecallAllActionPerformed(evt);
//             }
//         });

//         bMoveDrone.addActionListener(new java.awt.event.ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent evt) {
//                 bMoveDroneActionPerformed(evt);
//             }
//         });
        
//         bShutdown.addActionListener(new java.awt.event.ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent evt) {
//                 bShutdownActionPerformed(evt);
//             }
//         });
    
//         pack();
//     }                      

//     private void bDeleteFireActionPerformed(java.awt.event.ActionEvent evt) {                                            
//         //server.deleteFire()
//     }                                           

//     private void bMoveDroneActionPerformed(java.awt.event.ActionEvent evt) {                                             
//         //server.moveDrone()
//     } 

//     private void bRecallAllActionPerformed(java.awt.event.ActionEvent evt) {                                             
//         //server.recallAll()
//     }                                            

//     private void bShutdownActionPerformed(java.awt.event.ActionEvent evt) {                                          
//         Server.shutdown();
//     }                                         

//     //main() code for debugging/running GUI
//     public static void main(String args[]) {
//         try {
//             for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                 if ("Nimbus".equals(info.getName())) {
//                     UIManager.setLookAndFeel(info.getClassName());
//                     break;
//                 }
//             }
//         } 
//         catch (ClassNotFoundException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (InstantiationException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (IllegalAccessException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } 
//         catch (javax.swing.UnsupportedLookAndFeelException ex) {
//             java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         }

//         // create and display form
//         EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 new GUI().setVisible(true);
//             }
//         });
//     }  
// }
