package Server;

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
    }
}

class Connection extends Thread {

	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;

	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream( clientSocket.getInputStream());
			out =new ObjectOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}

	public void run(){
		try {
            //read from .csv here??
		}
        catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
		} 
        catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
		} 
        catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}
        finally{
            try {
                clientSocket.close();
            }
            catch (IOException e)
        }
	}
}

//server core
//todo: load data from fires.csv
//todo: load saved drone binary
//todo: receive drone reg
//todo: save drone details
//todo: display position (print until GUI)
//todo: send instructions to any drone

//admin
//todo: delete fire
//todo: recall all
//todo: move drone
//todo: shut down server 

//gui
//todo: GUI