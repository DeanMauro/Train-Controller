/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wisdominator
 */
public class EngineModel {
    
    public static final double MAX_SPEED = 19.4444;
    public static final double MEDIUM_ACCELERATION = 0.5;
    public static final double TOTAL_MAX_POWER = 12000;
    public static final double MEDIUM_DECELERATION = 1.2;
    public static final double EMERGENCY_DECELERATION = 4.095;
    public static final double GRAVITY = 9.8067;
    public static final double FRICTION = 0.07433;
    
    
    private double maxPower;			//The max power (watt) increase in a time step		
    //private double lastPower;			//The power (watt) input in a previous time step
    private double currentSpeed;		//The current velocity (m/s) the train is traveling
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
    private double currentPosition;		//Holds the current train position
    public double currentTime = 0;
    public double mass = 100;
    public double currentForce;
    public double initSpeed = .001;
    public double currentAcceleration;
    
    //this constructor creates an instance of the train engine
    public EngineModel(double time, int id){
        
    }
    
    public double currentSpeed(double power, double mass, double time){
        currentTime = 0;
        currentSpeed = 0;
        while(time > currentTime){
                double currentAcceleration = power/(currentSpeed*mass);
                if(currentAcceleration>2.5){
                    currentAcceleration = 2.5;
                }
                currentSpeed = currentSpeed + currentAcceleration*deltaT;
                currentTime = currentTime + deltaT;
            
            System.out.println(currentSpeed);
            
            }
            
            
            return currentSpeed;
        
    }
    
    public double currentPosition(double power, double mass, double time){
        currentTime = 0;
        currentSpeed = 0;
        currentPosition = 0;
        while(time > currentTime){
                double currentAcceleration = power/(currentSpeed*mass);
                if(currentAcceleration>2.5){
                    currentAcceleration = 2.5;
                }
                currentSpeed = currentSpeed + currentAcceleration*deltaT;
                currentPosition = currentPosition + deltaT*currentSpeed;
                currentTime = currentTime + deltaT;
            System.out.println(currentPosition);
            try {
                    Thread.sleep(1000);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            
            }
            
            
            return currentPosition;
        
    }

}
