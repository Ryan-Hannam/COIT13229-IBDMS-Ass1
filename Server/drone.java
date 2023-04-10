package Server;

import java.io.*;

public class drone implements Serializable{
    private int droneID;
    private String droneName;
    private double droneXPos;
    private double droneYPos;
    private static long serialVersionUID = 123789L;

    public drone(){

    }

    public drone(int droneID, String droneName, double droneXPos, double droneYPos){
        this.droneID = droneID;
        this.droneName = droneName;
        this.droneXPos = droneXPos;
        this.droneYPos = droneYPos;
    }

    public drone(drone another){
        this(another.getDroneID(),another.getDroneName(),another.getDroneXPos(),another.getDroneYPos());
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

    @Override
    public String toString(){
        return String.format("Drone ID: %d Drone Name: %s\n x Position: %f y Position %f\n", this.droneID, this.droneName,this.droneXPos,this.droneYPos);
    }
}

//todo flesh out drone data