package Domain;

import java.rmi.*;
import java.io.*;

public class Fire implements Serializable{
    private int fireID;
    private double fireXPos;
    private double fireYPos;
    private static long serialVersionUID = 789123L;

    public Fire(){

    }

    public Fire(int fireID, double fireXPos, double fireYPos){
        this.fireID = fireID;
        this.fireXPos = fireXPos;
        this.fireYPos = fireYPos;
    }

    public Fire(Fire another){
        this(another.getfireID(),another.getfireXPos(),another.getfireYPos());
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

    @Override
    public String toString(){
        return String.format("Fire ID: %d \n x Position: %f y Position %f\n", this.fireID,this.fireXPos,this.fireYPos);
    }
}