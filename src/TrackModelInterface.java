
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Q
 */
public class TrackModelInterface extends javax.swing.JPanel {
    TrackModel trackModel = new TrackModel();
    int numberTrains = 0;
    public static void main(String args[]){
    // <editor-fold defaultstate="collapsed" desc="Main"> 
        
    JFrame frame1 = new JFrame("Track Model");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.add(new TrackModelInterface());
    frame1.pack();

    frame1.setVisible(true);
    frame1.validate();
    }
    // </editor-fold> 

    /**
     * Creates new form TrackModel
     */
    
    
    public TrackModelInterface() {
        initComponents();
    }
    
    /**queryButton - currently only used to testing
     * will be updated to be able to press button and call updateInfo function with selected block object
     */
    public void queryButton(){
        double x = 0;
            //trackModel.updatePosition(x);
            //x += .01;
        System.out.println("Query");
        //Block ID = trackModel.findBlockID();
        //System.out.println("On block: " + ID.getBlockId());
        //trainSpeed.setText(String.valueOf(ID.getSpeedLimit()));
        //trainLength.setText(String.valueOf(ID.getLength()));
        //trainBlockID.setText(String.valueOf(ID.getBlockId()));
        //trainIsStation.setText(String.valueOf(ID.isStation()));
    }
    
    /**updateInfo - Used to update the display which will show specific block information.
     * Parameters:
     * @param train - is there a train?
     * @param ID - Block ID num
     * @param speed - block speed
     * @param grade - block grade
     * @param elevation - block elevation
     * @param length - block length
     * @param failure - is there a failure?
     * 
     * UPDATE:  NEEDS to accepts a block object, not the individual stats
     * 
     */
    private void updateInfo(boolean train, int ID, int speed, int grade, int elevation, int length, int failure){
        failureMode.setText(Integer.toString(failure));
        if(failure == 0)
        {
            failureMode.setVisible(true);
            //failureMode.setText("No Block Failure");
            
        }
        else
        {
            failureMode.setVisible(true);
            //failureMode.setText("Power Failure");
        }
        failureMode.setVisible(false);
        
        
        if(train == true)
            onBlock.setText("True");
        else
            onBlock.setText("False");
        blockID.setText(Integer.toString(ID));
        blockSpeed.setText(Integer.toString(speed).concat("mph"));
        blockGrade.setText(Integer.toString(grade).concat("%"));
        blockElevation.setText(Integer.toString(elevation).concat("m"));
        blockLength.setText(Integer.toString(length).concat("m"));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImportTrack = new javax.swing.JButton();
        DeleteTrack = new javax.swing.JButton();
        IDTextField = new javax.swing.JTextField();
        Query = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        onBlock = new javax.swing.JLabel();
        blockID = new javax.swing.JLabel();
        blockSpeed = new javax.swing.JLabel();
        blockGrade = new javax.swing.JLabel();
        blockElevation = new javax.swing.JLabel();
        blockLength = new javax.swing.JLabel();
        power = new javax.swing.JButton();
        Rail = new javax.swing.JButton();
        TrackCircuit = new javax.swing.JButton();
        failureMode = new javax.swing.JLabel();
        trainBlockID = new javax.swing.JTextField();
        trainSpeed = new javax.swing.JTextField();
        trainLength = new javax.swing.JTextField();
        trainIsStation = new javax.swing.JTextField();
        trackDisplay = new javax.swing.JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                ourCustomPaintingMethod(g);
            }
        };
        ;

        ImportTrack.setText("Import Track");
        ImportTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportTrackActionPerformed(evt);
            }
        });

        DeleteTrack.setText("Delete Track");

        IDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTextFieldActionPerformed(evt);
            }
        });

        Query.setText("Query");
        Query.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QueryActionPerformed(evt);
            }
        });

        jLabel1.setText("Block Information:");

        jLabel2.setText("Block ID:");

        jLabel3.setText("Speed:");

        jLabel4.setText("Grade:");

        jLabel5.setText("Elevation:");

        jLabel6.setText("Length");

        jLabel7.setText("Train on Block:");

        power.setText("Power Failure");
        power.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerActionPerformed(evt);
            }
        });

        Rail.setText("Rail Break");

        TrackCircuit.setText("Circuit Failure");

        javax.swing.GroupLayout trackDisplayLayout = new javax.swing.GroupLayout(trackDisplay);
        trackDisplay.setLayout(trackDisplayLayout);
        trackDisplayLayout.setHorizontalGroup(
            trackDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        trackDisplayLayout.setVerticalGroup(
            trackDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(power, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TrackCircuit)
                            .addComponent(Rail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(failureMode))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(trainBlockID, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addComponent(trainSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addComponent(trainLength)
                        .addComponent(trainIsStation)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trackDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(onBlock))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(blockLength))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(blockElevation, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(blockID, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(blockGrade))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(blockSpeed))
                            .addComponent(ImportTrack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DeleteTrack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Query, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trackDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(power)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Rail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TrackCircuit)
                        .addGap(46, 46, 46)
                        .addComponent(failureMode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainBlockID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainIsStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ImportTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteTrack)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Query)
                            .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(onBlock))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(blockID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(blockSpeed))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(blockGrade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(blockElevation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(blockLength))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ImportTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportTrackActionPerformed
        // TODO add your handling code here:
        String path = System.getProperty("user.dir");
        System.out.println(path);
        JFileChooser openFile = new JFileChooser(path);
        openFile.showOpenDialog(null);
        String fileName = openFile.getSelectedFile().toString();
        System.out.println(fileName);

        int validFile = trackModel.InternalImport(fileName);
        if(validFile == 1)//good file
        {
            System.out.println("good file");
        }
        else//bad file
        {
            System.out.println("bad file");
        }
        //Block ID = trackModel.findBlockID();

    }//GEN-LAST:event_ImportTrackActionPerformed

    private void IDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTextFieldActionPerformed

    private void QueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QueryActionPerformed
        // TODO add your handling code here:
        //int ID = Integer.parseInt(IDTextField.getText());
        //updateInfo(false, 12, 10, 1, 5, 50, 0);
        queryButton();
        
    }//GEN-LAST:event_QueryActionPerformed

    private void powerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerActionPerformed
        // TODO add your handling code here:
        int ID = Integer.parseInt(blockID.getText());
        String block = "track".concat(Integer.toString(ID));
        if(failureMode.getText().equals("1"))
        {
            //track32.setBackground(new Color(102,255,51));
            //(JButton)block.setBackground(Color.black);
        }

    }//GEN-LAST:event_powerActionPerformed

   /**class track Model
    * track class that contains methods for finding blocks, trains, and information
    */ 
    public class TrackModel {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    TrackObject trckOb; 
    
    //blockList blockLinkList;
    double trainSpeed;
    double trainDistance;
    int nodePostion;
    int nodeNum;
    int trainId = 0;
    double[][] trainDist = new double[100][3];
    public TrackModel(){
        trckOb = new TrackObject();
        //blockLinkList = new blockList();
        trainSpeed = 0;
        trainDistance = 10000;
        nodePostion = 0;
        nodeNum = 77;
        
        for(int i=0; i<100; i++){
            trainDist[i][2] = 77;
        }
    }
    
    
    public void updateSpeed(double speed){
        trainSpeed = speed;
    }
    
    public void updatePosition(double position){
        trainDistance = position;
    }
    
    public void updateTrainId(int ID){
        trainId = ID;
    }
    
    //update block object to train true or false
    public void findBlockID(){
        double newDist = trainDistance - trainDist[trainId-1][1];
        trainDist[trainId-1][1] = trainDistance;
        double blockLength = 0;
        int blockSpeed = 0;
        Block b;
        while(true)
        {
            //System.out.println("1");

            b = trckOb.getBlock((int)trainDist[trainId-1][2]);
            //System.out.println("on block: "+b.getBlockId());
            //System.out.println("2");
            blockLength = b.getLength();
            //System.out.println("block length: "+ blockLength);
            //System.out.println("postion: "+ postion);
            blockSpeed = b.getSpeedLimit();
            if(blockLength > newDist)
            {
                trainDist[trainId-1][2] = b.getBlockId();
                System.out.println("on block: "+b.getBlockId());
                break;
            }
            else
            {
                newDist -= blockLength;
                trainDist[trainId-1][2] = trckOb.getBlock((int)trainDist[trainId-1][2]).getPrevBlockId();
            }
                        
        }
    }
    
    //redraw with new color 
   public void redraw(){
        
    }
    //import csv track file
    public int InternalImport(String text){
    	
        //System.out.println(text);
    	String trackLineColor=null;
    	try 
        {
                Scanner fileScan = new Scanner(new File(text));

                String linetwo = fileScan.nextLine();
                int i  =0;
                String[] blockInfo = null;
                Block newBlock;
                while (fileScan.hasNextLine()) 
                {
                    String line = fileScan.nextLine();
                    System.out.println(line);
                    blockInfo = line.split(",");
                    //System.out.println("number of items:"+blockInfo.length);

                    trackLineColor=blockInfo[0];
                    trckOb.setLine(blockInfo[0]);
                    newBlock = new Block(blockInfo);
                    i++;
                    newBlock.setYard(false);
                    trckOb.addBlock(newBlock);
                    //blockLinkList.add(newBlock);
                    
          
                    /*
                    System.out.print(blockInfo[0] + " ");
                    System.out.print(blockInfo[1] + " ");
                    System.out.print(blockInfo[2] + " ");
                    System.out.print(blockInfo[3] + " ");
                    System.out.print(blockInfo[4] + " ");
                    System.out.print(blockInfo[5] + " ");
                    System.out.print(blockInfo[6] + " ");
                    System.out.print(blockInfo[7] + " ");
                    System.out.print(blockInfo[8] + " ");
                    System.out.print(blockInfo[9] + " ");

                    System.out.println();
                    */
                }
                fileScan.close();
                if(i>0)
                {
                    newBlock = new Block(blockInfo);
                    newBlock.setYard(true);
                    trckOb.addBlock(newBlock);
                    i++;
                }
                System.out.println("Num blocks: "+i);
                
                //refactor hashtable
                
                
                //link list only
                /**Refactor the linked list
                 * 1 - the current list just reads the csv file from head to tail, doesnt take into account switches, etc
                 * 2 - this method will scan through the linked list and arrange the list so that branches due to switches will be correct
                 */
                //blockLinkList.refactor();
                //Track.trackArray.put(trckOb.getLine(), trckOb);
                //Track.trackListData.add(trackLineColor);
                //for(int j = 1; j< 78;j++)
                //{
                    //System.out.println(j);
                    //Block y = blockLinkList.get(j);
                    //System.out.println(y);
                    
                //}

                trckOb.refactor();
                for(int x = 1;x<trckOb.getNumBlocks();x++)
                {
                    Block temp = trckOb.getBlock(x);
                    
                    
                    System.out.println("iterator: "+x);//print iterator
                    System.out.println("blockID: "+temp.getBlockId());//print block ID
                    System.out.println("nextBlock: "+temp.getNextBlockId());//print next block ID 
                    System.out.println("is main switch: "+temp.isSwitchInfra());//print if main switch
                    System.out.println("currently switched to: "+temp.getCurrentlySwitchedTo());//print who it is switched to
                    System.out.println("switch option 1: "+temp.getBlockSwitchID1());//print switch option 1
                    System.out.println("switch option 2: "+temp.getBlockSwitchID2());//print switch option 2
                    System.out.println("Prev block: "+temp.getPrevBlockId());//print switch option 2
                    System.out.println("");//print 
                    System.out.println("");//print 
                }
                return 1;
        } 
        catch (FileNotFoundException e) 
        {
                return 0;
        }	
    }
    
    public void setNumTrains(int n){
        numberTrains = n;
    }
    
    
}
    
    //getter for the track model so the wrapper has access
    public TrackModel getTrackModel(){
        return this.trackModel;
    }
    
    
    public void ourCustomPaintingMethod(Graphics g){
        super.paintComponent(g);

            g.drawString("BLAH", 20, 20);
            g.drawRect(200, 200, 200, 200);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton DeleteTrack;
    protected javax.swing.JTextField IDTextField;
    protected javax.swing.JButton ImportTrack;
    protected javax.swing.JButton Query;
    protected javax.swing.JButton Rail;
    protected javax.swing.JButton TrackCircuit;
    protected javax.swing.JLabel blockElevation;
    protected javax.swing.JLabel blockGrade;
    protected javax.swing.JLabel blockID;
    protected javax.swing.JLabel blockLength;
    protected javax.swing.JLabel blockSpeed;
    protected javax.swing.JLabel failureMode;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel7;
    protected javax.swing.JLabel onBlock;
    protected javax.swing.JButton power;
    protected javax.swing.JPanel trackDisplay;
    protected javax.swing.JTextField trainBlockID;
    protected javax.swing.JTextField trainIsStation;
    protected javax.swing.JTextField trainLength;
    protected javax.swing.JTextField trainSpeed;
    // End of variables declaration//GEN-END:variables
}
