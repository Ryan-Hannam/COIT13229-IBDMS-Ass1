package Server;


//import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

//todo: multithread
public class server {
    public static void main (String args[]){
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true){
                Socket clientSocket=listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        }
        catch(IOException e){
            System.out.println("Listen socket: "+e.getMessage());
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }

    //shutdown the server & recall all drones
    public static void shutdown(){
        // recallAll();
        System.exit(0);
    }

    //todo: read from .csv
    // private void readFile(){
    //     List<List<String>> fires = new ArrayList<>();
    //         try(Scanner s = new Scanner(new File("fires.csv"));) {
    //             while(s.hasNextLine()){
    //                 fires.add(getRecordFromLine(s.nextLine()));
    //             }
    //         }
    // }

    // private List<String> getRecordFromLine(String line){
    //     List<String> values = new ArrayList<String>();
    //     try(Scanner r = new Scanner(line)){
    //         while r.hasNext()){
    //             values.add(r.next());
    //         }
    //     }
    //     return values;
    //  }

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

class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    private LinkedList<drone> droneLinkedList;
    public Connection (Socket aClientSocket, LinkedList<drone> drones){
        droneLinkedList = new LinkedList<>();
        droneLinkedList = drones;
        try{
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.start();
            System.out.println("thread started "+in.getClass()); //todo: display in text box
        }
        catch(IOException e){
            System.out.println("Connection:"+e.getMessage()); //todo: display in text box
        }
    }

    public LinkedList<drone> getDroneList(){
        return this.droneLinkedList;
    }

    @Override
    public void run(){
        drone data;
        List <drone> list = new LinkedList<>();
        String finish = "";
        try{
            String option = (String) in.readObject();
            //todo: flesh out
        }
        catch(EOFException e){
            System.out.println("EOF:":+e.getMessage());
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("IO:"+e.getMessage());
        }
    }
}

//server core
//todo: load data from fires.csv
//todo: load saved drone binary
//todo: receive drone reg
//todo: save drone details
//todo: display position (print into GUI)
//todo: send instructions to any drone
