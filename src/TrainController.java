
//unit assumptions
//velocity: meters/second
//distances: meters
//power: watts
//acceleration: meters/second^2


//signals to train
//power in watts
//door open boolean
//light on/off boolean
public class TrainController {
    
    public int trainID;
    public TrainModel train; 
    public TrainControllerUI tcUI;
    
    public double velocitySetpoint;
    public double vAct;
    public double authority;    
    public double speedLimit; 
    
    public boolean doorStatus;
    public boolean lightStatus;
    public boolean brakeStatus;  
    public boolean conductorLight;
    boolean eBrakeStatus;                 //true means engaged and false means disengaged
    boolean engineFailure;          //true means failed engine and false means okay
    boolean brakeFailure;           //true means failed brakes and false means okay
    boolean signalPickupFailure;    //true means failed antenna and false means okay
    String failureMessage;
    
    public String nextStop;    
    public double power;
    
    //variables for power calculation       
    private double maxTrainDeceleration;

    private double maxTrainPower;
    private final double KP = 8000;//proportional gain
    private final double KI = 300;//integral gain
    private double uk = 0; //integral error
    private double ek = 0;//proportional error
    private double T = 0.1;
   
    public double ctcSuggestedSpeed;
    public double ctcSuggestedAuthority;
    public double mboCommandedSpeed;
    public double mboCommandedAuthority;
    public double controllerSpeedSetpoint;
    public double controllerSpeedSetpointMPS;
    
    public TrainController(int ID, TrainModel newTrain)
    {
        trainID = ID;
        train = newTrain;         
               
        velocitySetpoint = 0; 
        speedLimit = 0;
        authority = 0;//m
        
        doorStatus = false;
        lightStatus = false;
        brakeStatus = false; 
        conductorLight = false;
        eBrakeStatus = false;                 //true means engaged and false means disengaged
             
        engineFailure = false;          //true means failed engine and false means okay
        brakeFailure = false;           //true means failed brakes and false means okay
        signalPickupFailure = false;    //true means failed antenna and false means okay
        failureMessage = "";
        nextStop = "";
        
        power = 0; //watts
        vAct = 0; //m/s
        
        ctcSuggestedSpeed=0;
        ctcSuggestedAuthority=0;
        mboCommandedSpeed=0;
        mboCommandedAuthority=0;
        controllerSpeedSetpoint = 0;        
              
        maxTrainDeceleration = 1.2;
        maxTrainPower = 120000;  
        
    }
    
    public int getID()
    {
        return trainID;        
    } 
    
    public void setControllerSpeedSetpoint(double speed)
    {
        //convert from m/h to m/s
        controllerSpeedSetpoint = speed; 
        controllerSpeedSetpointMPS = speed*0.44704;
        setPower();
    }
    
    public void setPower()
    {
        this.evaluateFailure();
        
        /*if(engineFailure ||  brakeFailure || signalPickupFailure)
        {
            train.setPower(0);
            
        }
        else
        {*/
            //actual train velcity
            vAct = train.getCurrentSpeed();   
            //call redundant method to check
            double powerCheck = setPowerRedundant(vAct,  authority, ctcSuggestedAuthority,mboCommandedAuthority, power, uk, ek);
            //use min authority
            authority = Math.min(ctcSuggestedAuthority,mboCommandedAuthority);       
            /*if(authority <= 0)
            {
                authority = 0.1;            
            }    */            

            //Need to calculate a velocity such that train stops moving 
            //(Vf=0) based on given authority (d) and trains max deceleration (a)
            //Vf^2 = Vi^2 +2ad
            //0 = Vi^2 +2ad
            //Vi^2 = -2ad
            //Vi = sqrt(-2ad) -> ommitting the - sign and using a positive value for deceleration(1.2)

            double vLimit = Math.sqrt(2*maxTrainDeceleration*authority);              

            //decide what speed setpoint to use
            velocitySetpoint = evaluateVelocity(vLimit);           

            //check that setpoint is not greater than track speed limit or velocity limit      
            if(power < maxTrainPower)//if pcm < pmax
            {
                 uk = uk + (T/2)*(ek + (velocitySetpoint - vAct));
            }       
            
            ek = velocitySetpoint - vAct;            
            power = (KP*ek) + (KI * uk);           

            if (Math.abs(power) < Math.abs(powerCheck) * 0.8 || Math.abs(power) > Math.abs(powerCheck) * 1.2) // If redundant power does not agree with this power by +/-20%, stop train
            {
                power = 0.0;
            }        
            /*if(power < 0)
            {
                power = 0;
            }*/
            train.setPower(power); 
        //}
    }   

    
    private double setPowerRedundant(double vAct1, double authority1, double ctcSuggestedAuthority1, double mboCommandedAuthority1, double power1, double uk1, double ek1)
    {   
        authority1 = Math.min(ctcSuggestedAuthority1,mboCommandedAuthority1);       
        /*if(authority1 <= 0)
        {
            authority1 = 0.1;            
        }*/
                
        double vLimit = Math.sqrt(2*maxTrainDeceleration*authority1);       
        double velocitySetpoint1 = evaluateVelocity(vLimit);
        
        
        if(power1 < maxTrainPower)//if pcm < pmax
        {
             uk1 = uk1 + (T/2)*(ek1 + (velocitySetpoint1 - vAct1));
        }       
        ek1 = velocitySetpoint1 - vAct1;
       
        power1 = (KP*ek1) + (KI * uk1);      
        
        /*if(power1 < 0)
        {
            power1 = 0;
        }*/
        return power1;
    }
    
    public void setCtcAuthority(double auth)
    {
        this.ctcSuggestedAuthority = auth;       
        setPower();        
    }
    
     public void setCtcSpeed(double speed)
    {
        this.ctcSuggestedSpeed = speed;       
        setPower();        
    }
     
     public void setMboAuthority(double auth)
    {
        this.mboCommandedAuthority = auth;
        setPower();        
    }
    
     public void setMboSpeed(double speed)
    {
        this.mboCommandedSpeed = speed;
        setPower();        
    }
    
     public void setSpeedLimit(double sLimit)
     {         
         this.speedLimit = sLimit;
         setPower();
     }    
    
    //evaluate whether to use ctcSpeed, mboSpeed, or ConductorSpeed
     public double evaluateVelocity(double calculatedLimit)
     {
        double mboOrCtc, v;
        mboOrCtc = Math.max(this.mboCommandedSpeed,this.ctcSuggestedSpeed);
        v = Math.max(mboOrCtc,this.controllerSpeedSetpoint);
        
        if(v > Math.min(this.speedLimit, calculatedLimit))
        {
            v = Math.min(this.speedLimit, calculatedLimit);
        }
        return v;
     }
         
     
    public void evaluateDoors(boolean fromConductor)
    {
        /*
        Evaluate whether doors should be 
        allowed to be opened/closed       
        */
        vAct = train.getCurrentSpeed();
        int v = (int) vAct;
       
        if(train.atStation && v == 0 && !train.doorStatus && !fromConductor)
        {
            train.doorStatus = true;
            this.doorStatus = true;
        }
        else if(v != 0 && train.doorStatus && !fromConductor)
        {
            train.doorStatus = false;
            this.doorStatus = false;
        }
        else if(v == 0 && !train.doorStatus && fromConductor)
        {
            train.doorStatus = true;
            this.doorStatus = true;
        }
        else if(train.doorStatus && fromConductor)
        {
            train.doorStatus = false;
            this.doorStatus = false;
        }        
        
    }

    public void evaluateLights(boolean fromConductor)
    {
        /*
        Evaluate whether lights should be 
        allowed to be turned on or off       
        */      
        if(train.underground && !train.lightStatus && !fromConductor)
        {
            train.lightStatus = true;
            this.lightStatus = true;
        }
        else if(!train.underground && train.lightStatus && !fromConductor && !conductorLight)
        {
            train.lightStatus = false;
            this.lightStatus = false;
        }
        else if(fromConductor && !train.lightStatus)
        {
           train.lightStatus = true;
           this.lightStatus = true;
           this.conductorLight = true;
        }
        else if(fromConductor && train.lightStatus && !train.underground)
        {
           train.lightStatus = false;
           this.lightStatus = false;
        } 
       
    }

    public void evaluateBrake()
    {
        /*
        Evaluate whether brake should be 
        allowed to be turned on or off       
        */
        if(this.brakeStatus)
        {
            train.setConductorBrake(true);
            this.brakeStatus = true;            
        }
        else
        {
            train.setConductorBrake(false);
            this.brakeStatus = false;
        }      
       
    } 
    
    public void evaluateFailure()
    {
        boolean failFlag = false;
        
        this.brakeFailure = train.brakeFailure;
        this.engineFailure = train.engineFailure;
        this.signalPickupFailure = train.signalPickupFailure;
        
        if(train.brakeFailure)
        {
            this.brakeFailure = true;
            this.failureMessage = train.failure;
            failFlag = true;
        }
        else if(train.engineFailure)
        {
            this.engineFailure = true;
            this.failureMessage = train.failure;
            failFlag = true;
        }
        else if(train.signalPickupFailure)
        {
            this.engineFailure = true;
            this.failureMessage = train.failure;
            failFlag = true;
        }
        if(failFlag)//this is causing problems
        {
            train.setFailureBrake(true);   
            //train.eBrake = true;
        }
        else
        {
            train.setFailureBrake(false);
            this.failureMessage="";
            //train.eBrake = false;
        }
    }
    
    public void update()
    {
        this.evaluateLights(false);
        this.evaluateDoors(false);
        this.evaluateEbrake();
        this.evaluateFailure();
    }
    
    public void setNextStop(String n)
    {
        this.nextStop = n;
    }
    
    public void evaluateEbrake()
    {      
        this.eBrakeStatus = train.eBrake;        
    }
    
    public double getMboAuthority()
    {
        return this.mboCommandedAuthority;
    }
    
    public double getCtcAuthority()
    {
        return this.ctcSuggestedAuthority;
    }
    
    public double getMboSpeed()
    {
        return this.mboCommandedSpeed;
    }
    
    public double getCtcSpeed()
    {
        return this.ctcSuggestedSpeed;
    }
}
