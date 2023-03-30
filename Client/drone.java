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
}

//todo flesh out drone data