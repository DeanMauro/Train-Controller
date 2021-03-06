
import java.util.Vector;


public class TrainModel extends javax.swing.JFrame {

    int ID;
    static int num_trains = 0;
    String announcement;
    String currentStation;
    String nextStation;
    String failure;
    
    boolean blockClosed = false;
    boolean approachingStation = false;
    boolean atStation = false;              //true means at Station and false means not
    boolean underground = false;            //true means underground and false means not
    boolean lightStatus = false;            //true means on and false mean close
    boolean doorStatus = false;             //true means open and false mean close
    boolean conductorBrake = false;         //true means engaged and false means disengaged
    boolean eBrake = false;                 //true means engaged and false means disengaged
    boolean engineFailure = false;          //true means failed engine and false means okay
    boolean brakeFailure = false;           //true means failed brakes and false means okay
    boolean signalPickupFailure = false;    //true means failed antenna and false means okay
    boolean inputBrakeFailure = false;
    boolean inputEngineFailure = false;
    boolean inputSignalFailure = false;
    boolean inputEmergencyBrake = false;
    boolean failureBrake = false;
    
    
    double passengerMass = 80.7;//Kilograms
    int passengerCount = 0;
    int crewCount = 10;         //Randomly chosen
    double temperature;
    double trainLength = 32.2;  //meters
    double trainHeight = 3.42;  //meters
    double trainWidth  = 2.65;  //meters
    
    double currentPower = 0.0;  //Watts
    double mass = 40900;        //kilograms
    double time = 200;          //seconds
    double currentForce;        //Newtons
    double currentAcceleration; //Meters/sec^2
    double currentSpeed;        //Meters/sec.
    double currentPosition;	//Meters
    double initSpeed = .001;    //Meters/sec.
    int currentTime = 0;        //Seconds
    double deltaT=1;            //Seconds
    double currentSpeedLimit;
    double blockSpeedLimit;
    String stationName;
    double blockLength;
    String NextStation;
    
    
    
    int maxPassengers = 222;    //Train can hold no more than 222 passengers
    double maxSpeed = 19.44;    //Train speed can't exceed 70 km/hr (19.44 m/s)
    double maxAcceleration = 1; //Train can accelerate past 1 m/s^2
    
    public Vector<TrainModel> trainList = new Vector();
     public Vector<String> trainIDList = new Vector();
    //change to trainList.get(trainID);
     private TrainModel TM;
    
    /////////////////////////////////////////
    //Constructor
    /////////////////////////////////////////
    
     void addToTrainList(int ID, TrainModel t){
        this.trainList.add(t);
        num_trains=1;
        updateTrainList(ID);
    }

    public void updateTrainList(int ID)
    {   
        trainIDList.add("Train " + String.valueOf(ID));
        
        trainSelect.setModel(new javax.swing.DefaultComboBoxModel(trainIDList.toArray()));  
        TM = trainList.get(ID-1);
        //updateFields();
    }
    
    public void updateFields()
    {
        doorStatusDisplay.setText( (TM.doorStatus) ? ("Open") : ("Closed") );
        lightStatusDisplay.setText( (TM.lightStatus) ? ("On") : ("Off") );
        nextStationDisplay.setText(nextStation);
        currentSpeedDisplay.setText(String.format("%.2f",TM.currentSpeed));
        currentPositionDisplay.setText(String.format("%.2f",TM.currentPosition));
        passengerCountDisplay.setText(String.valueOf(passengerCount));
        
    }
 
     
    public TrainModel(int trainID) {
        initComponents();
        System.out.println("TRAIN ID: " + trainID);
        this.ID = trainID;
        if(num_trains == 0){
            addToTrainList(trainID, this);
        }
    }
        
    /////////////////////////////////////////
    //Updates Train Movement
    /////////////////////////////////////////
    
    public void update(int time){
        calculateDeltaT(time);
        calculateForce();
        calculateAcceleration();
        calculateSpeed();
        calculatePosition();
       
        
    }
    
    public void updateBlockItems(Block b){
        blockSpeedLimit = 0.277778 * b.getSpeedLimit();
        underground = b.isUnderground();
        atStation = b.isStation();
        stationName = b.getStationName();
        blockLength = b.getLength();
        blockClosed = b.isClosed();        
    }
   
    
    /////////////////////////////////////////
    //Getter, Setter, & Calculation Functions
    /////////////////////////////////////////
    
    public void setApproachingStation(boolean a){
        this.approachingStation = a;
    }
    
    public void setAtStation(boolean s){
        this.atStation = s;
        //currentStation =
        //nextStation =
    }
    
    public void setUnderground(boolean u){
        this.underground = u;
    }
    
    public void setLights(boolean l){
        this.lightStatus = l;
    }
    
    public void setDoors(boolean d){
        if(atStation){
            this.doorStatus = d;
        }
    }
    
    public void setConductorBrake(boolean b){
        this.conductorBrake = b;
    }
    
    public void setFailureBrake(boolean b)
    {
        this.failureBrake = b;
    }
    
    public void setEBrake(boolean e){
        this.eBrake = e;
    }
    
    public void resetEBrake(){
        this.eBrake = false;
        emergencyBrakeButton.setText("Emergency Brake");
    }
    
    public void setPassengerCount(int p){
        this.passengerCount = p;
        this.passengerMass = (p+crewCount) * 80.7;  //80.7kg is the average american weight
    }
    
    public void setCrewCount(int c){
        this.crewCount = c;
    }
    
    public void setEngineFailure(boolean e){
        this.engineFailure = e;
        failure = "Engine Failure";
    }
    
    public void setSignalPickupFailure(boolean s){
        this.signalPickupFailure = s;
        failure = "Signal Pickup Failure";
    }
    
    public void setBrakeFailure(boolean b){
        this.brakeFailure = b;
        failure = "Brake Failure";
    }
    
    public void setPower(double pow){
        this.currentPower = pow;
    }
    
    public void calculateForce(){
        if (currentSpeed==0)
            currentSpeed = .0001;

        currentForce = currentPower / currentSpeed;
    }

    public void calculateAcceleration(){       
        if(conductorBrake)
            currentAcceleration = -1.2;     // m/sec^2
        else if(eBrake)
            currentAcceleration = -2.73;    // m/sec^2
       /* else if(inputBrakeFailure)
            currentAcceleration = 0;
        else if(currentSpeed==blockSpeedLimit)
            currentAcceleration = 0;
        else if(engineFailure)
            currentAcceleration = 0;*/
        else if(failureBrake)
        {
            currentAcceleration = -2.73;
        }
        else
            currentAcceleration = currentForce / (mass+passengerMass);
        
        System.out.println(mass);
        
        if(currentAcceleration > 1)         //Max acceleration can't
            currentAcceleration = 1;        //exceed 1 m/sec^2
    }

    public void calculateSpeed(){
        if(currentSpeed>blockSpeedLimit && blockSpeedLimit != 0){
            currentSpeed = blockSpeedLimit;
        }else{
            currentSpeed = currentSpeed + (currentAcceleration * deltaT);
        }
        if(currentSpeed > 19.44)            //Max speed can't
            currentSpeed = 19.44;           //exceed 19.44 m/s
        else if(currentSpeed <= 0){         //Min speed can't
            currentSpeed = 0;               //go below 0 m/s
        }
    }

    public void calculatePosition(){
        if(currentSpeed != 0)
            currentPosition = currentPosition + deltaT*currentSpeed + 0.5*currentAcceleration*deltaT*deltaT;
    }

    public void calculateDeltaT(int newTime){
            deltaT = newTime - currentTime;
            currentTime = newTime;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public double getCurrentPosition(){
        return currentPosition;
    }
    
    public int getID(){
        return ID;
    }
    
    public double getSpeedLimit(){
        return blockSpeedLimit;
    }
    
    public void setNextStation(String s){
        nextStation = s;
    }
    
    public String getNextStation(){
        return nextStation;
    }

    public int getPassengerCount(){
        return passengerCount;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        currentSpeedLB = new javax.swing.JLabel();
        currentPosisiontLB = new javax.swing.JLabel();
        setSpeedLB = new javax.swing.JLabel();
        setPowerTF = new javax.swing.JTextField();
        currentPositionDisplay = new javax.swing.JTextField();
        currentSpeedDisplay = new javax.swing.JTextField();
        engineFailureButton = new javax.swing.JToggleButton();
        brakeFailureButton = new javax.swing.JToggleButton();
        signalFailureButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        engineFailureDisplay = new javax.swing.JTextField();
        brakeFailureDisplay = new javax.swing.JTextField();
        signalFailureDisplay = new javax.swing.JTextField();
        emergencyBrakeButton = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        doorStatusDisplay = new javax.swing.JTextField();
        lightStatusDisplay = new javax.swing.JTextField();
        nextStationDisplay = new javax.swing.JTextField();
        trainSelect = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        passengerCountDisplay = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Train Model");

        currentSpeedLB.setText("Current Speed:");

        currentPosisiontLB.setText("Current Position:");

        setSpeedLB.setText("Set Power:");

        setPowerTF.setText("Set Power");
        setPowerTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPowerTFActionPerformed(evt);
            }
        });

        currentPositionDisplay.setText("Current Position");
        currentPositionDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentPositionDisplayActionPerformed(evt);
            }
        });

        currentSpeedDisplay.setText("Current Speed");
        currentSpeedDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentSpeedDisplayActionPerformed(evt);
            }
        });

        engineFailureButton.setText("Engine Failure");
        engineFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                engineFailureButtonActionPerformed(evt);
            }
        });

        brakeFailureButton.setText("Brake Failure");
        brakeFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brakeFailureButtonActionPerformed(evt);
            }
        });

        signalFailureButton.setText("Signal Failure");
        signalFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signalFailureButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Current Failure: ");

        engineFailureDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                engineFailureDisplayActionPerformed(evt);
            }
        });

        brakeFailureDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brakeFailureDisplayActionPerformed(evt);
            }
        });

        signalFailureDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signalFailureDisplayActionPerformed(evt);
            }
        });

        emergencyBrakeButton.setText("Emergency Brake");
        emergencyBrakeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emergencyBrakeButtonActionPerformed(evt);
            }
        });

        jButton1.setLabel("Reset EBrake");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Door Status:");

        jLabel4.setText("Light Status:");

        jLabel5.setText("Next Station:");

        doorStatusDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doorStatusDisplayActionPerformed(evt);
            }
        });

        nextStationDisplay.setText(" ");

        trainSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        trainSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainSelectActionPerformed(evt);
            }
        });

        jLabel6.setText("Passengers:");

        passengerCountDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passengerCountDisplayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(currentPosisiontLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentSpeedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(setSpeedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(trainSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(signalFailureButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(brakeFailureButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(engineFailureButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brakeFailureDisplay)
                            .addComponent(engineFailureDisplay)
                            .addComponent(signalFailureDisplay)
                            .addComponent(setPowerTF)
                            .addComponent(currentPositionDisplay)
                            .addComponent(currentSpeedDisplay))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(emergencyBrakeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 8, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nextStationDisplay)
                                            .addComponent(lightStatusDisplay)
                                            .addComponent(doorStatusDisplay)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(passengerCountDisplay)))))))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(emergencyBrakeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(trainSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentSpeedLB)
                            .addComponent(currentSpeedDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentPosisiontLB)
                            .addComponent(currentPositionDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setSpeedLB)
                            .addComponent(setPowerTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(passengerCountDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(engineFailureButton)
                    .addComponent(engineFailureDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(doorStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brakeFailureButton)
                    .addComponent(brakeFailureDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lightStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signalFailureButton)
                    .addComponent(signalFailureDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(nextStationDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jButton1.getAccessibleContext().setAccessibleName("resetEbrakeButton");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brakeFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brakeFailureButtonActionPerformed
        // TODO add your handling code here:
        inputBrakeFailure = brakeFailureButton.isSelected();
        setBrakeFailure(inputBrakeFailure);
        if(inputBrakeFailure){
            brakeFailureButton.setText("Fix Failure");
            brakeFailureDisplay.setText("Applied");
        }
        else{
            brakeFailureButton.setText("Break Failure");
            brakeFailureDisplay.setText("Fixed");
        }
    }//GEN-LAST:event_brakeFailureButtonActionPerformed

    private void engineFailureDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_engineFailureDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_engineFailureDisplayActionPerformed

    private void signalFailureDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signalFailureDisplayActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_signalFailureDisplayActionPerformed

    private void engineFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_engineFailureButtonActionPerformed
        // TODO add your handling code here:
        inputEngineFailure = engineFailureButton.isSelected();
        setEngineFailure(inputEngineFailure);
        if(inputEngineFailure){
            engineFailureButton.setText("Fix Failure");
            engineFailureDisplay.setText("Applied");
        }
        else{
            engineFailureButton.setText("Engine Failure");
            engineFailureDisplay.setText("Fixed");
        }
    }//GEN-LAST:event_engineFailureButtonActionPerformed

    private void signalFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signalFailureButtonActionPerformed
        // TODO add your handling code here:
        inputSignalFailure = signalFailureButton.isSelected();
        setSignalPickupFailure(inputSignalFailure);
        if(inputSignalFailure){
            signalFailureButton.setText("Engine Failure");
            signalFailureDisplay.setText("Applied");
        }
        else{
            signalFailureButton.setText("Fix Failure");
            signalFailureDisplay.setText("Fixed");
        }
    }//GEN-LAST:event_signalFailureButtonActionPerformed

    private void emergencyBrakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencyBrakeButtonActionPerformed
        // TODO add your handling code here:
        inputEmergencyBrake = signalFailureButton.isSelected();
        if(inputEmergencyBrake){
            
        }
        else{
            emergencyBrakeButton.setText("E-Brake Applied");
            setEBrake(true);
        }
    }//GEN-LAST:event_emergencyBrakeButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        resetEBrake();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void currentSpeedDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentSpeedDisplayActionPerformed
        // TODO add your handling code here:
        currentSpeedDisplay.setText(String.valueOf(currentSpeed));
    }//GEN-LAST:event_currentSpeedDisplayActionPerformed

    private void currentPositionDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentPositionDisplayActionPerformed
        // TODO add your handling code here:
        currentPositionDisplay.setText(String.valueOf(currentPosition));
    }//GEN-LAST:event_currentPositionDisplayActionPerformed

    private void setPowerTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPowerTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setPowerTFActionPerformed

    private void doorStatusDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doorStatusDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doorStatusDisplayActionPerformed

    private void trainSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainSelectActionPerformed
        // TODO add your handling code here:
        //Extract train number
        int id = trainSelect.getSelectedIndex();
         
        //Set TC to selected train
        TM = trainList.get(id);
        
        //Update UI fields
        updateFields();
        
    }//GEN-LAST:event_trainSelectActionPerformed

    private void brakeFailureDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brakeFailureDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_brakeFailureDisplayActionPerformed

    private void passengerCountDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passengerCountDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passengerCountDisplayActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) "
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrainModel(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton brakeFailureButton;
    private javax.swing.JTextField brakeFailureDisplay;
    private javax.swing.JLabel currentPosisiontLB;
    private javax.swing.JTextField currentPositionDisplay;
    private javax.swing.JTextField currentSpeedDisplay;
    private javax.swing.JLabel currentSpeedLB;
    private javax.swing.JTextField doorStatusDisplay;
    private javax.swing.JToggleButton emergencyBrakeButton;
    private javax.swing.JToggleButton engineFailureButton;
    private javax.swing.JTextField engineFailureDisplay;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField lightStatusDisplay;
    private javax.swing.JTextField nextStationDisplay;
    private javax.swing.JTextField passengerCountDisplay;
    private javax.swing.JTextField setPowerTF;
    private javax.swing.JLabel setSpeedLB;
    private javax.swing.JToggleButton signalFailureButton;
    private javax.swing.JTextField signalFailureDisplay;
    private javax.swing.JComboBox trainSelect;
    // End of variables declaration//GEN-END:variables
}
