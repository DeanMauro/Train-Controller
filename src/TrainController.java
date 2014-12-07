
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
    public int failureCode;
    public String failureInfo;
    public String nextStop;
    
    
    public double power;
    
    //variables for power calculation       
    private double maxTrainDeceleration;
    private double maxTrainPower;
    private final double KP = 10;//proportional gain
    private final double KI = 3;//integral gain
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
               
        //tcUI = new TrainControllerUI(this);
        train = newTrain; 
       
        velocitySetpoint = 0;     
       
        
        speedLimit = 0;
        
        authority = 0;//m
        doorStatus = false;
        lightStatus = false;
        brakeStatus = false;
        failureCode = 0;
        failureInfo = null;
        power = 0; //watts
        vAct = 0; //m/s
        
        ctcSuggestedSpeed=0;
        ctcSuggestedAuthority=0;
        mboCommandedSpeed=0;
        mboCommandedAuthority=0;
        controllerSpeedSetpoint = 0;
        
        //maxTrainDeceleration = train.getMaxDeceleration();
        //hardcoding for now
        maxTrainDeceleration = 1.2;
        maxTrainPower = 120000;
    }
    
        
   /* public TrainControllerUI getTrainControllerUI()
    {
        return this.tcUI;
    }*/
    
    public void setControllerSpeedSetpoint(double speed)
    {
        controllerSpeedSetpoint = speed; 
        setPower();
    }
   
    /*private void initUI()
    {
 //       java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TrainControllerUI().setVisible(true);
//            }
//            
//        });
        
    }*/
    
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
       
        //convert vAct to m/s
        
        
      //  tcUI.currentSpeedDisplay.setText(String.format("%.2f", vAct));
        
        //Need to calculate a velocity such that train stops moving 
        //(Vf=0) based on given authority (d) and trains max deceleration (a)
        //Vf^2 = Vi^2 +2ad
        //0 = Vi^2 +2ad
        //Vi^2 = -2ad
        //Vi = sqrt(-2ad)
        
        //authority = Math.min(ctcSuggestedAuthority,mboCommandedAuthority);
        authority = mboCommandedAuthority;
        //authority = 5000;
        if(authority <= 0)
        {
            authority = 0.1;            
        } 
        
        this.evaluateVelocity();
        //double powerCheck = this.setPowerRedundant(vAct, authority, power, ek, uk);
        
        double vLimit = Math.sqrt(2*maxTrainDeceleration*authority);
       //System.out.println("vlimit="+vLimit);
        //decide what speed setpoint to use
        
        //check that setpoint is not greater than track speed limit or velocity limit
        if(velocitySetpoint > Math.min(speedLimit, vLimit))
        {
            velocitySetpoint = Math.min(speedLimit, vLimit);
        }
        System.out.println("velSetpoint="+velocitySetpoint);
       // tcUI.safeSpeedSetpointDisplay.setText(String.valueOf(velocitySetpoint));
        
        if(power < maxTrainPower)//if pcm < pmax
        {
             uk = uk + (T/2)*(ek + (velocitySetpoint - vAct));
        }
       
       // System.out.println("uk="+uk);
        ek = velocitySetpoint - vAct;
        //System.out.println("ek="+ek);
        power = (KP*ek) + (KI * uk);
        
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
            train.setPower(0);
        }  */    
       // tcUI.powerOutputDisplay.setText(String.format("%.2f", power));
        
        //set power to kW to send to TrainModel
        //power = power/1000;
        
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
        this.evaluateVelocity();
        //check that setpoint is not greater than track speed limit or velocity limit
        if(velocitySetpoint > Math.min(speedLimit, vLimit))
        {
            velocitySetpoint = Math.min(speedLimit, vLimit);
        }
        System.out.println("velSetpoint="+velocitySetpoint);
       
        
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
     public void evaluateVelocity()
     {
         velocitySetpoint = Math.max(mboCommandedSpeed,controllerSpeedSetpoint);
         
        // tcUI.safeSpeedSetpointDisplay.setText(String.format("%.2f", velocitySetpoint));
     }
             
         
     
    public void evaluateDoors(boolean doorIn)
    {
        /*
        Evaluate whether doors should be 
        allowed to be opened/closed       
        */
        /*
        if(train.isAtStation())
        {
            this.TransmitDoors(true);
        }
        
        */
        this.transmitDoors(doorIn);
    }

    public void evaluateLights(boolean lightIn)
    {
        /*
        Evaluate whether lights should be 
        allowed to be turned on or off       
        */
        /*
        if(train.isUnderground())
        {
            this.transmitLights(true);
        }
        */
        this.transmitLights(lightIn);
    }

    public void evaluateBrake(boolean brakeIn)
    {
        /*
        Evaluate whether brake should be 
        allowed to be turned on or off       
        */
        this.transmitBrake(brakeIn);
    }  
    
    public int getID()
    {
        return trainID;        
    }
    
    
}
