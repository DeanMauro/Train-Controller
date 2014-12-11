
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
    boolean eBrake;                 //true means engaged and false means disengaged
    boolean engineFailure;          //true means failed engine and false means okay
    boolean brakeFailure;           //true means failed brakes and false means okay
    boolean signalPickupFailure;    //true means failed antenna and false means okay
    String failureMessage;
    
    public String nextStop;    
    public double power;
    
    //variables for power calculation       
    private double maxTrainDeceleration;
    private double maxTrainPower;    
    private final double KP = 1000;//proportional gain
    private final double KI = 300;//integral gain
    private double uk = 0; //integral error
    private double ek = 0;//proportional error
    private double T = 0.1;
   
    public double ctcSuggestedSpeed;
    public double ctcSuggestedAuthority;
    public double mboCommandedSpeed;
    public double mboCommandedAuthority;
    public double controllerSpeedSetpoint;    
    
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
        eBrake = false;                 //true means engaged and false means disengaged
        engineFailure = false;          //true means failed engine and false means okay
        brakeFailure = false;           //true means failed brakes and false means okay
        signalPickupFailure = false;    //true means failed antenna and false means okay
    
        eBrake = false;                 //true means engaged and false means disengaged
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
        controllerSpeedSetpoint = speed; 
        setPower();
    }
   
    private boolean transmitLights(boolean tLight)
    {
        this.lightStatus = tLight;
        //train.setLights(this.lightStatus);
        return true;
    }
     
    private boolean transmitDoors(boolean tDoor)
    {
        this.doorStatus = tDoor;
        //train.setDoors(this.doorStatus);
        return true;
    }
      
    private boolean transmitBrake(boolean tBrake)
    {
        this.brakeStatus = tBrake;
        //train.setBrake(this.brakeStatus);
        return true;
    }
       
    public void setPower()
    {
        //actual train velcity
        vAct = train.getCurrentSpeed();        
        
        //use min authority
        authority = Math.min(ctcSuggestedAuthority,mboCommandedAuthority);       
        if(authority <= 0)
        {
            authority = 0.1;            
        }      
        
        //double powerCheck = this.setPowerRedundant(vAct, authority, power, ek, uk);
        
        //Need to calculate a velocity such that train stops moving 
        //(Vf=0) based on given authority (d) and trains max deceleration (a)
        //Vf^2 = Vi^2 +2ad
        //0 = Vi^2 +2ad
        //Vi^2 = -2ad
        //Vi = sqrt(-2ad) -> ommitting the - sign and using a positive value for deceleration(1.2)
        
        double vLimit = Math.sqrt(2*maxTrainDeceleration*authority);
        //System.out.println("vlimit="+vLimit);        
        
        //decide what speed setpoint to use
        velocitySetpoint = evaluateVelocity(vLimit);
        
        //trying this temporarily
        //velocitySetpoint = vLimit;
       
        //check that setpoint is not greater than track speed limit or velocity limit    
        
      
        if(power < maxTrainPower)//if pcm < pmax
        {
             uk = uk + (T/2)*(ek + (velocitySetpoint - vAct));
        }
       
        //System.out.println("uk="+uk);
        ek = velocitySetpoint - vAct;
        //System.out.println("ek="+ek);
        power = (KP*ek) + (KI * uk);
        //System.out.println("power="+power);
        
        //Add redundant power check here
        /*if(powerCheck == power)
        {
           // tcUI.powerOutputDisplay.setText(String.format("%.2f", power));
        
            //set power to kW to send to TrainModel
            //power = power/1000;
            train.setPower(power); 
        }
        else
        {
            train.setPower();
        }  */  
      
        
        //set power to kW to send to TrainModel
        //power = power/1000;
        if(power < 0)
        {
            power = 0.1;
        }
        train.setPower(power); 
    }
    
    private double setPowerRedundant(double v, double auth, double pow, double ek1, double uk1)
    {       
        if(auth <= 0)
        {
            auth = 0.1;            
        }        
        
        double vLimit = Math.sqrt(2*maxTrainDeceleration*auth);
       //System.out.println("vlimit="+vLimit);
        //decide what speed setpoint to use
        velocitySetpoint = this.evaluateVelocity(vLimit);
        //try this
        velocitySetpoint = vLimit;
        //check that setpoint is not greater than track speed limit or velocity limit
        if(velocitySetpoint > Math.min(speedLimit, vLimit))
        {
            velocitySetpoint = Math.min(speedLimit, vLimit);
        }
       // System.out.println("velSetpoint="+velocitySetpoint);
       
        
        if(pow < maxTrainPower)//if pcm < pmax
        {
             uk1 = uk1 + (T/2)*(ek1 + (velocitySetpoint - v));
        }
       
       // System.out.println("uk="+uk);
        ek1 = velocitySetpoint - v;
        //System.out.println("ek="+ek);
        pow = (KP*ek1) + (KI * uk1);
        return pow; 
    }
    
    public void setCtcAuthority(double auth)
    {
        this.ctcSuggestedAuthority = auth;
        //tcUI.ctcSuggestedAuthority.setText(String.format("%.2f", auth));
        setPower();        
    }
    
     public void setCtcSpeed(double speed)
    {
        this.ctcSuggestedSpeed = speed;
        //tcUI.ctcSuggestedAuthority.setText(String.format("%.2f", speed));
        setPower();        
    }
     
     public void setMboAuthority(double auth)
    {
        this.mboCommandedAuthority = auth;
        
       // tcUI.mboAuthorityDisplay.setText(String.format("%.2f", auth));
        setPower();        
    }
    
     public void setMboSpeed(double speed)
    {
        this.mboCommandedSpeed = speed;
       
       // tcUI.mboSpeedSetpoint.setText(String.format("%.2f", speed));
        setPower();        
    }
    
     public void setSpeedLimit(double sLimit)
     {         
         this.speedLimit = sLimit;
       //  tcUI.speedLimitDisplay.setText(String.format("%.2f", sLimit));
         setPower();
     }
    
    
    //evaluate whether to use ctcSpeed, mboSpeed, or ConductorSpeed
     public double evaluateVelocity(double calculatedLimit)
     {
         
       /* velocitySetpoint = Math.max(trainOperatorVelocity, ctcOperatorVelocity); // Selects faster of two velocities.

        if (velocitySetpoint > Math.min(Math.min(trackLimit, TRAIN_LIMIT), authorityVelocityLimit)) // If the operator sends a dangerous velocity,
        {
            velocitySetpoint = Math.min(Math.min(trackLimit, TRAIN_LIMIT), authorityVelocityLimit); // set to next highest allowable velocity
        }
         
        double v = Math.max(mboCommandedSpeed,controllerSpeedSetpoint);         
        return v;*/
        double mboOrCtc, v;
        mboOrCtc = Math.max(this.controllerSpeedSetpoint,this.ctcSuggestedSpeed);
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
        
        if(train.atStation && vAct == 0 && !train.doorStatus && !fromConductor)
        {
            train.doorStatus = true;
            this.doorStatus = true;
        }
        else if(vAct != 0 && train.doorStatus && !fromConductor)
        {
            train.doorStatus = false;
            this.doorStatus = false;
        }
        else if(vAct == 0 && train.doorStatus && fromConductor)
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
        else if(!train.underground && train.lightStatus && !fromConductor)
        {
            train.lightStatus = false;
            this.lightStatus = false;
        }
        else if(fromConductor && !train.lightStatus)
        {
           train.lightStatus = true;
           this.lightStatus = true;
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
        if(train.brakeFailure)
        {
            this.brakeFailure = true;
            this.failureMessage = train.failure;
        }
        else if(train.engineFailure)
        {
            this.engineFailure = true;
            this.failureMessage = train.failure;
        }
        else if(train.signalPickupFailure)
        {
            this.engineFailure = true;
            this.failureMessage = train.failure;
        }
        if(failFlag)
        {
            train.setConductorBrake(true);
        }
    }
    
    public void setNextStop(String n)
    {
        this.nextStop = n;
    }
    
    public void evaluateEbrake()
    {
        eBrake = train.eBrake;        
    }
    
  
    
    
}
