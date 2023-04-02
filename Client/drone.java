package Client;

import java.io.*;

public class drone implements Serializable{
    private int droneID;
    private String droneName;
    private double droneXPos;
    private double droneYPos;

    public drone(){

    }

    public drone(int droneID, String droneName, double droneXPos, double droneYPos){
        this.droneID = droneID;
        this.droneName = droneName;
        this.droneXPos = droneXPos;
        this.droneYPos = droneYPos;
    }

    public int getDroneID(){
        return droneID;
    }

    public void setDroneID(int droneID){
        this.droneID = droneID;
    }

    public String getDroneName(){
        return droneName;
    }

    public void setDroneName(String droneName){
        this.droneName = droneName;
    }

    public double getDroneXPos(){
        return droneXPos;
    }

    public void setDroneXPos(double droneXPos){
        this.droneXPos = droneXPos;
    }

    public double getDroneYPos(){
        return droneYPos;
    }

    public void setDroneYPos(double droneYPos){
        this.droneYPos = droneYPos;
    }
}





//todo flesh out drone data