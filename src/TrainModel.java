import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Spring;
import javax.swing.Timer;


public class TrainModel {

	String announcement;
	Double trainMass;
	Double passengerMass;
	int passengerNumber;
	Boolean lightStatus = true;	//true means on and false mean close
	Boolean doorStatus = true;	//true means open and false mean close
	Double temperature;
	String failure;
	Double breakLevel;
	Boolean emergencyBreak = false;
	String currentStation;
	String nextStation;
	Double trainLength;
	
	private double maxPower;			//The max power (watt) increase in a time step		
	//private double lastPower;			//The power (watt) input in a previous time step
	private boolean engineFailure;		//Holds the engine status (functional = false, broken = true)
	private boolean brakeFailure;		//Holds the brake status (functional = false, broken = true)
	//private boolean eBrake;				//Holds the emergency brake status (not pulled = false, pulled = true)
	private double suggestedSpeed;			
	private double suggestedAuthority;
	private double commandedSpeed;
	private double commandedAuthority;
	private double deltaT=1;				//The time step (seconds)
	private double speedLimit;			//Holds the current train number
	private int trainNum;				//Holds the current train number
	public double mass = 100;
        public double currentPower;
	public double currentForce;
	public double currentAcceleration;
	public double currentSpeed;
	public double currentPosition;		//Holds the current train position
	public double initSpeed = .001;
	public double currentTime = 0;
	
	


	public static void main(String[] args) {

	}

	public void calculateForce(){
		if (currentSpeed==0)
			currentSpeed = .001;
		
		currentForce = currentPower / currentSpeed;
	}
	
	public void calculateAcceleration(){
		currentAcceleration = currentForce / mass;
	}
	
	public void calculateSpeed(){
		currentSpeed = currentSpeed + (currentAcceleration * deltaT);
		
		//if(currentSpeed < 70)
		//	currentSpeed = 70;
	}
	
	public void calculatePosition(){
		
		currentPosition = currentPosition + deltaT*currentSpeed + 0.5*currentAcceleration*deltaT*deltaT;
               // currentPosition = Math.abs(currentPosition);
	}
	
	public void calculateDeltaT(int newTime){
		deltaT = newTime - currentTime;
		currentTime = newTime;
	}
        
        public double getSpeed(){
            return currentSpeed;
        }
        
        public double getPosition(){
            return currentPosition;
        }
        
        public void setPower(double pow){
            this.currentPower = pow;
        }
        
}
