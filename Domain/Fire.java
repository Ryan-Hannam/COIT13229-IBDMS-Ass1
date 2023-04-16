package Domain;

import java.io.*;

public class Fire implements Serializable{
    private int fireID;
    private double fireXPos;
    private double fireYPos;
    private int fireDroneID;
    private int fireSeverity;
    private static long serialVersionUID = 789123L;

    public Fire(){

    }

    public Fire(int fireID, double fireXPos, double fireYPos, int fireDroneID, int fireSeverity){
        this.fireID = fireID;
        this.fireXPos = fireXPos;
        this.fireYPos = fireYPos;
        this.fireDroneID = fireDroneID;
        this.fireSeverity = fireSeverity;
    }

    public Fire(Fire another){
        this(another.getfireID(),another.getfireXPos(),another.getfireYPos(),another.getFireDroneID(),another.getFireSeverity());
    }

    public int getfireID(){
        return fireID;
    }

    public void setfireID(int fireID){
        this.fireID = fireID;
    }

    public double getfireXPos(){
        return fireXPos;
    }

    public void setfireXPos(double fireXPos){
        this.fireXPos = fireXPos;
    }

    public double getfireYPos(){
        return fireYPos;
    }

    public void setfireYPos(double fireYPos){
        this.fireYPos = fireYPos;
    }
    
    public int getFireDroneID(){
        return fireDroneID;
    }

    public void setFireDroneID(int fireDroneID){
        this.fireDroneID = fireDroneID;
    }
    
    public int getFireSeverity(){
        return fireSeverity;
    }

    public void setFireSeverity(int fireSeverity){
        this.fireSeverity = fireSeverity;
    }

    @Override
    public String toString(){
        return String.format("Fire ID: %d \n x Position: %f y Position %f\n Respoding Drone: %d Severity: %d", this.fireID,this.fireXPos,this.fireYPos,this.fireDroneID,this.fireSeverity);
    }
}