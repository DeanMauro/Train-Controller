
public class TrainModel extends javax.swing.JFrame {

    int ID;
    String announcement;
    String currentStation;
    String nextStation;
    String failure;
    
    boolean atStation = false;              //true means at Station and false means not
    boolean underground = false;            //true means underground and false means not
    boolean lightStatus = false;            //true means on and false mean close
    boolean doorStatus = false;             //true means open and false mean close
    boolean conductorBrake = false;         //true means engaged and false means disengaged
    boolean eBrake = false;                 //true means engaged and false means disengaged
    boolean engineFailure = false;          //true means failed engine and false means okay
    boolean brakeFailure = false;           //true means failed brakes and false means okay
    boolean signalPickupFailure = false;    //true means failed antenna and false means okay
    
    double passengerMass = 80.7;//Kilograms
    int passengerCount;
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
    double currentTime = 0;     //Seconds
    double deltaT=1;            //Seconds
    
    int maxPassengers = 222;    //Train can hold no more than 222 passengers
    double maxSpeed = 19.44;    //Train speed can't exceed 70 km/hr (19.44 m/s)
    double maxAcceleration = 1; //Train can accelerate past 1 m/s^2
    
    public TrainModel(int trainID) {
        initComponents();
        
        this.ID = trainID;
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
        this.doorStatus = d;
    }
    
    public void setConductorBrake(boolean b){
        this.conductorBrake = b;
    }
    
    public void setEBrake(boolean e){
        this.eBrake = e;
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
            currentSpeed = .001;

        currentForce = currentPower / currentSpeed;
    }

    public void calculateAcceleration(){
        if(conductorBrake)
            currentAcceleration = -1.2;     // m/sec^2
        else if(eBrake)
            currentAcceleration = -2.73;    // m/sec^2
        else
            currentAcceleration = currentForce / mass;
    }

    public void calculateSpeed(){
        currentSpeed = currentSpeed + (currentAcceleration * deltaT);
    }

    public void calculatePosition(){
        currentPosition = currentPosition + deltaT*currentSpeed + 0.5*currentAcceleration*deltaT*deltaT;
        // currentPosition = Math.abs(currentPosition);
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
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        currentSpeedLB = new javax.swing.JLabel();
        currentPosisiontLB = new javax.swing.JLabel();
        setSpeedLB = new javax.swing.JLabel();
        setPowerTF = new javax.swing.JTextField();
        currentPositionTF = new javax.swing.JTextField();
        currentSpeedTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Train Model");

        currentSpeedLB.setText("Current Speed:");

        currentPosisiontLB.setText("Current Position:");

        setSpeedLB.setText("Set Power");

        setPowerTF.setText("Set Power");

        currentPositionTF.setText("Current Position");

        currentSpeedTF.setText("Current Speed");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentPosisiontLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentSpeedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(setSpeedLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(setPowerTF)
                    .addComponent(currentPositionTF)
                    .addComponent(currentSpeedTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentSpeedLB)
                    .addComponent(currentSpeedTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPosisiontLB)
                    .addComponent(currentPositionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setSpeedLB)
                    .addComponent(setPowerTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
    private javax.swing.JLabel currentPosisiontLB;
    private javax.swing.JTextField currentPositionTF;
    private javax.swing.JLabel currentSpeedLB;
    private javax.swing.JTextField currentSpeedTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField setPowerTF;
    private javax.swing.JLabel setSpeedLB;
    // End of variables declaration//GEN-END:variables
}
