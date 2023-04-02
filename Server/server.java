package Server;

import java.awt.*;
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
		} catch(IOException e){
            System.out.println("Connection:"+e.getMessage());
        }
	}

	public void run(){
		try {
            //read from .csv here??
            //GUI here?
		}
        catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
		} 
        catch(IOException i) {
            System.out.println("readline:"+i.getMessage());
		} 
        catch(ClassNotFoundException ex){
					 ex.printStackTrace();
		}
        finally{
            try {
                clientSocket.close();
            }
            catch (IOException j){
                System.out.println("readline: "+j.getMessage());
            }
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
// private void deleteFire(){
//    delete fire from .csv?
// }

//todo: recall all
// private void recallAll(){
//     foreach?
//     sendrecall()
// }

//todo: move drone
// private voice moveDrone(){
//     setDroneXPos
//     setDroneYPos
//     sendtoDrone
// }

//todo: shut down server 
// private void shutdown(){
//     recallAll();
//     System.exit(0);
// }

//gui
//todo: GUI
private void UI(){
    JFrame frame = new JFrame("IBDMS");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //todo: replace this with Recall message
    frame.setLayout(new BorderLayout());
    frame.add(new newPane());
}

public class newPane extends JPanel{
    public newPane(){
        //todo: construct internal layout
        //needs
            //"Action Pane" left side with buttons
            //functional buttons to left top
            //messages to left bottom
            //"Map Pane" remainder screen space
        System.out.println("Under construction");    
    }
}
