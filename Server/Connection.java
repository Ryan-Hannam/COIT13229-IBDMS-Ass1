package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import Domain.Drone;
import Server.GUI;

class Connection extends Thread {

    private static GUI guiFrame;

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    java.rmi.registry.Registry registry;
    private LinkedList<Drone> droneLinkedList;
    
    public Connection (Socket aClientSocket, LinkedList<Drone> drones){
        
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

    public LinkedList<Drone> getDroneList(){
        return this.droneLinkedList;
    }

    @Override
    public void run(){
        try {

            while(true) {

                Object object = in.readObject();

                if (object instanceof Drone) {

                    //typecase object to drone
                    Drone droneFromClient = (Drone) object;

                    Server.addMessageToConsoleInGUI("Drone connected: " + droneFromClient.toString() );

                    //check if drone array already contains a drone with the same id
                    if (droneLinkedList.contains(droneFromClient)) {

                        

                    } else {

                        droneLinkedList.add(droneFromClient);
                        System.out.println("Drone added");
                        guiFrame.addMessageToConsole(droneFromClient.toString());

                    }

                }

            }

        } 

        catch ( EOFException e) {
            System.out.println("EOF:"+e.getMessage());
            e.printStackTrace();
        }

        catch( IOException e) {
            System.out.println("readline:"+e.getMessage());
            e.printStackTrace();
        }

        catch( ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        finally
        { try {
                clientSocket.close();
            }
            
            catch ( IOException e)
            {/*close failed*/}
        }

        // Drone data;
        // LinkedList<Drone> list = new LinkedList<Drone>();
        // String finish = "";

        // try {

        //     String option = (String) in.readObject();
        //     //todo: flesh out
        // }

        // catch(EOFException e){
        //     System.out.println("EOF:"+e.getMessage());
        // }
        
        // catch(IOException | ClassNotFoundException e){
        //     System.out.println("IO:"+e.getMessage());
        // }
    }
}
