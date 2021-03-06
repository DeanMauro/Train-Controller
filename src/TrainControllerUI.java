
import java.util.Vector;


public class TrainControllerUI extends javax.swing.JPanel {    
    
    private double inputSpeed;
    private double lastSafeSpeedInput;
    private boolean inputDoors;
    private boolean inputLights;
    private boolean inputBrake;
    private int currentID;
    
    private TrainController TC;
    
    public Vector<TrainController> trainList = new Vector();
    public Vector<String> trainIDList = new Vector();   
     
   public TrainControllerUI() {        
        initComponents();       
    }
    
    void addToTrainList(int ID, TrainController t){
        this.trainList.add(t);
        updateTrainList(ID);
    }

    public void updateTrainList(int ID)
    {   
        trainIDList.add("Train " + String.valueOf(ID));        
        trainSelect.setModel(new javax.swing.DefaultComboBoxModel(trainIDList.toArray()));  
        TC = trainList.get(ID-1);
        updateFields();
    }   
    
    public void updateFields()
    {
        safeSpeedSetpointDisplay.setText(String.format("%.2f", convertToMPH(TC.velocitySetpoint)) +" MPH");
        ctcSuggestedAuthority.setText(String.format("%.2f", convertToM(TC.ctcSuggestedAuthority)) +" M");
        ctcSuggestedSpeed.setText(String.format("%.2f", convertToMPH(TC.ctcSuggestedSpeed)) +" MPH");
        currentSpeedDisplay.setText(String.format("%.2f", convertToMPH(TC.vAct)) +" MPH");
        doorStatusDisplay.setText( (TC.doorStatus) ? ("Open") : ("Closed") );
        inputDoorsButton.setText( (TC.doorStatus) ? ("CLOSE DOORS") : ("OPEN DOORS") );
        inputDoorsButton.setSelected( TC.doorStatus );
        lightStatusDisplay.setText( (TC.lightStatus) ? ("On") : ("Off") );
        inputLightButton.setText( (TC.lightStatus) ? ("TURN LIGHTS OFF") : ("TURN LIGHTS ON") );
        inputLightButton.setSelected( TC.lightStatus );
        inputBrakeButton.setText( (TC.brakeStatus) ? ("RELEASE BRAKE") : ("SET BRAKE") );
        inputBrakeButton.setSelected( TC.brakeStatus );
        mboAuthorityDisplay.setText(String.format("%.2f", convertToM(TC.mboCommandedAuthority)) +" M");
        mboSpeedSetpoint.setText(String.format("%.2f", convertToMPH(TC.mboCommandedSpeed)) +" MPH");
        nextStopDisplay.setText(TC.nextStop);
        failureMessageDisplay.setText(TC.failureMessage);        
        powerOutputDisplay.setText(String.format("%.2f", TC.power/1000)  +" kW");
        speedLimitDisplay.setText(String.format("%.2f", convertToMPH(TC.speedLimit))  +" MPH");        
        inputSpeedSpinner.setValue(TC.controllerSpeedSetpoint);
        
        eBrakeDisplay.setText( (TC.eBrakeStatus) ? ("On") : ("Off") );
        resetEbrakeButton.setVisible(TC.eBrakeStatus);
    }
    
    public double convertToMPH(double mps)
    {        
        return mps* 2.23694;
    }
    
    public double convertToM(double m)
    {        
        return m * 0.000621371;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        speedLimit4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        inputSpeedSpinner = new javax.swing.JSpinner();
        inputDoorsButton = new javax.swing.JToggleButton();
        inputLightButton = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        inputBrakeButton = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        trainSelect = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        ctcSuggestedSpeed = new javax.swing.JTextField();
        ctcSuggestedAuthority = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        currentSpeedDisplay = new javax.swing.JTextField();
        speedLimitDisplay = new javax.swing.JTextField();
        lightStatusDisplay = new javax.swing.JTextField();
        nextStopDisplay = new javax.swing.JTextField();
        doorStatusDisplay = new javax.swing.JTextField();
        powerOutputDisplay = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        mboSpeedSetpoint = new javax.swing.JTextField();
        mboAuthorityDisplay = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        safeSpeedSetpointDisplay = new javax.swing.JTextField();
        failureMessageDisplay = new javax.swing.JTextField();
        eBrakeDisplay = new javax.swing.JTextField();
        resetEbrakeButton = new javax.swing.JButton();

        speedLimit4.setEditable(false);

        jLabel1.setText("Adjust Speed:");

        inputSpeedSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                inputSpeedSpinnerStateChanged(evt);
            }
        });

        inputDoorsButton.setText("OPEN DOORS");
        inputDoorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDoorsButtonActionPerformed(evt);
            }
        });

        inputLightButton.setText("TURN LIGHTS ON");
        inputLightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLightButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("E-Brake Status:");

        inputBrakeButton.setText("SET BRAKE");
        inputBrakeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBrakeButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Track Speed Limit:");

        jLabel6.setText("CTC Speed Setpoint:");

        jLabel7.setText("CTC Authority:");

        jLabel9.setText("Door Status:");

        jLabel10.setText("Light Status:");

        jLabel11.setText("Next Stop:");

        jLabel13.setText("Power Output:");

        jLabel14.setText("Failure Message:");

        trainSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Train #" }));
        trainSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainSelectActionPerformed(evt);
            }
        });

        jLabel15.setText("Train Select");

        ctcSuggestedSpeed.setEditable(false);
        ctcSuggestedSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctcSuggestedSpeedActionPerformed(evt);
            }
        });

        ctcSuggestedAuthority.setEditable(false);
        ctcSuggestedAuthority.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctcSuggestedAuthorityActionPerformed(evt);
            }
        });

        jLabel16.setText("Current Speed:");

        currentSpeedDisplay.setEditable(false);
        currentSpeedDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentSpeedDisplayActionPerformed(evt);
            }
        });

        speedLimitDisplay.setEditable(false);

        lightStatusDisplay.setEditable(false);

        nextStopDisplay.setEditable(false);
        nextStopDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStopDisplayActionPerformed(evt);
            }
        });

        doorStatusDisplay.setEditable(false);

        powerOutputDisplay.setEditable(false);

        jLabel17.setText("MBO Speed Setpoint:");

        jLabel18.setText("MBO Authority:");

        mboSpeedSetpoint.setEditable(false);

        mboAuthorityDisplay.setEditable(false);

        jLabel19.setText("Safe Speed Setpoint:");

        safeSpeedSetpointDisplay.setEditable(false);

        failureMessageDisplay.setEditable(false);

        eBrakeDisplay.setEditable(false);
        eBrakeDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eBrakeDisplayActionPerformed(evt);
            }
        });

        resetEbrakeButton.setText("RESET EBRAKE");
        resetEbrakeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetEbrakeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel5))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lightStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(doorStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(nextStopDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ctcSuggestedSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(failureMessageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ctcSuggestedAuthority)
                                                .addComponent(safeSpeedSetpointDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(mboSpeedSetpoint))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(speedLimitDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                            .addComponent(mboAuthorityDisplay))))
                                .addGap(89, 89, 89))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(powerOutputDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(18, 18, 18)
                                    .addComponent(currentSpeedDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inputSpeedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eBrakeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(inputDoorsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputLightButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputBrakeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetEbrakeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel15)
                    .addGap(18, 18, 18)
                    .addComponent(trainSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(powerOutputDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(currentSpeedDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ctcSuggestedSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(mboSpeedSetpoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputSpeedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(safeSpeedSetpointDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ctcSuggestedAuthority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(inputDoorsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputLightButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputBrakeButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eBrakeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetEbrakeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(mboAuthorityDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(speedLimitDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(doorStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lightStatusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(nextStopDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(failureMessageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(trainSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addContainerGap(366, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inputSpeedSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_inputSpeedSpinnerStateChanged
        inputSpeed = Double.parseDouble(inputSpeedSpinner.getValue().toString());             
        if(inputSpeed < 0){ //cannot set speed to be lower than 0
            inputSpeedSpinner.setValue(0);
        }
        TC.setControllerSpeedSetpoint(inputSpeed);        
    }//GEN-LAST:event_inputSpeedSpinnerStateChanged

    private void inputDoorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDoorsButtonActionPerformed

        inputDoors = inputDoorsButton.isSelected();
        TC.evaluateDoors(true);
        if(TC.doorStatus){
            inputDoorsButton.setText("CLOSE DOORS");
            doorStatusDisplay.setText("Open");
            inputDoorsButton.setSelected(true);
        }
        else{
            inputDoorsButton.setText("OPEN DOORS");
            doorStatusDisplay.setText("Closed");
            inputDoorsButton.setSelected(false);
        }
    }//GEN-LAST:event_inputDoorsButtonActionPerformed

    private void inputLightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLightButtonActionPerformed

        inputLights = inputLightButton.isSelected();
        TC.evaluateLights(true);        
        if(TC.lightStatus){
            inputLightButton.setText("TURN LIGHTS OFF");
            lightStatusDisplay.setText("On");
            inputLightButton.setSelected(true);
        }
        else{
            inputLightButton.setText("TURN LIGHTS ON");
            lightStatusDisplay.setText("Off");
            inputLightButton.setSelected(false);
        }
    }//GEN-LAST:event_inputLightButtonActionPerformed

    private void inputBrakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBrakeButtonActionPerformed
        TC.brakeStatus = inputBrakeButton.isSelected();
        TC.evaluateBrake();        
    }//GEN-LAST:event_inputBrakeButtonActionPerformed

    private void trainSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainSelectActionPerformed
        //Extract train number
        int id = trainSelect.getSelectedIndex();         
        //Set TC to selected train
        TC = trainList.get(id);        
        //Update UI fields
        updateFields();
    }//GEN-LAST:event_trainSelectActionPerformed

    private void currentSpeedDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentSpeedDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentSpeedDisplayActionPerformed

    private void ctcSuggestedSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctcSuggestedSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctcSuggestedSpeedActionPerformed

    private void ctcSuggestedAuthorityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctcSuggestedAuthorityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctcSuggestedAuthorityActionPerformed

    private void nextStopDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStopDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextStopDisplayActionPerformed

    private void eBrakeDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eBrakeDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eBrakeDisplayActionPerformed

    private void resetEbrakeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetEbrakeButtonActionPerformed
        // TODO add your handling code here:
        TC.eBrakeStatus = false;
        TC.train.eBrake = false;
        resetEbrakeButton.setVisible(false);
       // TC.resetEbrake();
    }//GEN-LAST:event_resetEbrakeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField ctcSuggestedAuthority;
    public javax.swing.JTextField ctcSuggestedSpeed;
    public javax.swing.JTextField currentSpeedDisplay;
    private javax.swing.JTextField doorStatusDisplay;
    private javax.swing.JTextField eBrakeDisplay;
    private javax.swing.JTextField failureMessageDisplay;
    private javax.swing.JToggleButton inputBrakeButton;
    private javax.swing.JToggleButton inputDoorsButton;
    private javax.swing.JToggleButton inputLightButton;
    private javax.swing.JSpinner inputSpeedSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lightStatusDisplay;
    public javax.swing.JTextField mboAuthorityDisplay;
    public javax.swing.JTextField mboSpeedSetpoint;
    private javax.swing.JTextField nextStopDisplay;
    public javax.swing.JTextField powerOutputDisplay;
    private javax.swing.JButton resetEbrakeButton;
    public javax.swing.JTextField safeSpeedSetpointDisplay;
    private javax.swing.JTextField speedLimit4;
    public javax.swing.JTextField speedLimitDisplay;
    public javax.swing.JComboBox trainSelect;
    // End of variables declaration//GEN-END:variables
}
