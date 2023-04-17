package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import Domain.Drone;

class Connection extends Thread {

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

        Drone data;
        LinkedList<Drone> list = new LinkedList<Drone>();
        String finish = "";

        try {

            String option = (String) in.readObject();
            //todo: flesh out
        }

        catch(EOFException e){
            System.out.println("EOF:"+e.getMessage());
        }
        
        catch(IOException | ClassNotFoundException e){
            System.out.println("IO:"+e.getMessage());
        }
    }
}
