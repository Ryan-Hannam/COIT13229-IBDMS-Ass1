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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
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
        catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
		} 
        catch(ClassNotFoundException e){
					 e.printStackTrace();
		}
        finally{
            try {
                clientSocket.close();
            }
            catch (IOException e){
                System.out.println("readline: "+e.getMessage());
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
