import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.Timer;

class Wrapper {
	
	protected static Office office;
        protected static TrackModelInterface trackModelInterface;
	protected static Vector<TrainModel> trainModel;
	protected static Vector<TrainController> trainController;
        protected static TrainControllerUI trainControllerUI;
	protected static MovingBlockOverlayUI mbo;
        
        protected static ArrayList<ScheduleNode> schedule;
        protected static int timerDelay = 1000;
        protected static ActionListener ClockListener;
        protected static int seconds = 0;
        protected static int minutes = 0;
        protected static int totalSeconds = 0;
        protected static Timer timer;
        
        protected static int numberOfTrains = 0;

/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * MAIN 
 * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public static void main(String[] args) {
            initialize();
		
            //Create JFrames
            JFrame officeFrame 		= new JFrame("Office");
            JFrame trackModelFrame 	= new JFrame("Track Model");
            JFrame trainControllerFrame = new JFrame("Train Controller");
		
            //Set program to end when any one is closed
	    officeFrame			.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            trackModelFrame		.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            trainControllerFrame	.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mbo          		.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
            //Add visible components
	    officeFrame                 .add(office);
            trackModelFrame		.add(trackModelInterface);
            trainControllerFrame	.add(trainControllerUI);
		
            //Reset JFrames
	    officeFrame			.pack();
            trackModelFrame		.pack();
            trainControllerFrame	.pack();
		
            //Make JFrames Visible
	    officeFrame			.setVisible(true);
            trackModelFrame		.setVisible(true);
            trainControllerFrame	.setVisible(true);
            mbo          		.setVisible(true);
            
            
		
	}
	
	
/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * INITIALIZATION 
 *
 * We initialize the variables in a separate method in case anything 
 * special needs to be done first that could not have simply been done
 * up top where the variables are declared (e.g. if a constructor 
 * takes parameters or requires another component to be initialized
 * first.
 * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static void initialize(){
            
            
		
            office 		= new Office();
            trackModelInterface = new TrackModelInterface();
            trainModel          = new Vector();
            trainController     = new Vector();
            trainControllerUI   = new TrainControllerUI();
            mbo 		= new MovingBlockOverlayUI();

            /*Office Listeners*/
            addIncreaseClockSpeedListener(office.buttonIncreaseClockSpeed);
            addDecreaseClockSpeedListener(office.buttonDecreaseClockSpeed);
            addSpawnNewTrainListener(office.buttonGo);
            
            /*MBO Listeners*/
            addStartButtonListener(mbo.StartButton);
            
            /*Track Model Listeners*/
            addBlockSpeedListener(trackModelInterface.trainSpeed);
            addBlockLengthListener(trackModelInterface.trainLength);
            addIsStationListener(trackModelInterface.trainIsStation);
            addMBRBListener(mbo.MovingBlockRadio);
            
            /*TrainModel Listeners*/
            /*Train Controller Listeners*/
        
            
            

           ClockListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            
            /*Advance clock by 1 tick*/
              minutes = minutes + (seconds / 60);
              seconds = seconds % 60;
              Office.textClock.setText(String.format("%d:%02d", minutes, seconds));
              seconds++;
              totalSeconds++;
              
            /*Calculate new train metrics*/
              TrainModel currentTrain;
              
              for(int i=0; i<numberOfTrains; i++){
                  currentTrain = trainModel.get(i);
                  
                  /*Calculate Trains' new Speed, Acceleration, Positions*/
                  currentTrain.update(totalSeconds);

                  /*Update MBO, Office, and Track Model with new Train speeds*/
                  mbo.updateSpeed(currentTrain.getCurrentSpeed());
                  trackModelInterface.getTrackModel().updateSpeed(currentTrain.getCurrentSpeed());
                  Train.setSpeed(office.trainsOnTracks.get(i), currentTrain.getCurrentSpeed());

                  /*Update MBO, Office, and Track Model with new Train positions*/
                  mbo.updatePosition(currentTrain.getCurrentPosition());
                  trackModelInterface.getTrackModel().updatePosition(currentTrain.getCurrentPosition());
                  Train.setPosition(office.trainsOnTracks.get(i), currentTrain.getCurrentPosition());

                  /*Update Train Controllers with new MBO Authorities*/
                  mbo.updateBlockAuthority(mbo.getbauth());
                  Train.setAuthority(office.trainsOnTracks.get(i), mbo.getbauth());
                  trainController.get(i).setMboAuthority(mbo.getbauth());
                  trainController.get(i).setMboSpeed(mbo.getbspeed());

                  //trackModelInterface.queryButton();

              }
              
              trainControllerUI.updateFields();

            }
            };
           
         /*Start the Timer*/
           timer = new Timer(timerDelay, ClockListener);
           
	}
        
        
/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * SPAWN NEW TRAIN 
 * * * * * * * * * * * * * * * * * * * * * * * * * */
	
        public static void spawnTrain(){
            //Increment number of trains
            numberOfTrains++;
            //Create Train
            trainModel.add(new TrainModel(numberOfTrains)); 
            //Create Train Controller for new Train
            trainController.add(new TrainController(numberOfTrains, trainModel.lastElement()));
            //Add Train Controller to Train Controller UI's list
            trainControllerUI.addToTrainList(numberOfTrains, trainController.lastElement());
            //Display new train in Office
            office.addTrain(numberOfTrains);
            
            if(!timer.isRunning())
                timer.start();
            
            //Train Controller: calculate power for first block
            //Train Controller: send power to train
            //Train Model: receive power
            //Train Model: update fields (current block, target block, lights, etc)
        }
	
/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * TRACK CIRCUIT 
 * * * * * * * * * * * * * * * * * * * * * * * * * */

	
	/////////////////////////////////
	//OFFICE LISTENERS
	/////////////////////////////////
        private static void addIncreaseClockSpeedListener(JButton IncreaseSpeed){
            // <editor-fold defaultstate="collapsed" desc="Increase Clock Speed">	
            IncreaseSpeed.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    timerDelay = 100;
                    timer.setDelay(timerDelay);
                }
            });

	}// </editor-fold> 
        
        private static void addDecreaseClockSpeedListener(JButton DecreaseSpeed){
            // <editor-fold defaultstate="collapsed" desc="Decrease Clock Speed">	
            DecreaseSpeed.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    timerDelay = 1000;
                    timer.setDelay(timerDelay);
                }
            });

	}// </editor-fold> 
        
        private static void addSpawnNewTrainListener(JButton Go){
            // <editor-fold defaultstate="collapsed" desc="Spawn New Train">	
            Go.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    spawnTrain();
                }
            });

	}// </editor-fold> 
        
	private static void addOfficeRerouteListener(JButton Reroute){
            // <editor-fold defaultstate="collapsed" desc="Reroute Train">
            Reroute.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    int ID = Integer.parseInt(((JButton)e.getSource()).getActionCommand());
                    //trainController.get(ID).setCtcPosition(office.getSuggestedPosition(ID));
                }
            });

	}// </editor-fold> 
        
        private static void addSpeedAndAuthorityListener(JButton Send){
            // <editor-fold defaultstate="collapsed" desc="Suggest Speed/Authority">
            Send.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    int ID = Integer.parseInt(((JButton)e.getSource()).getActionCommand());
                    trainController.get(ID).setCtcAuthority(office.getSuggestedAuthority(ID));
                    trainController.get(ID).setCtcSpeed(office.getSuggestedAuthority(ID));
                }
            });

	}// </editor-fold> 
	 

	/////////////////////////////////
	//TRACK MODEL LISTENERS
	/////////////////////////////////
        private static void addBlockSpeedListener(JTextField bs){
            final JTextField blocky = bs;
            // <editor-fold defaultstate="collapsed" desc="Block Speed">
            bs.getDocument().addDocumentListener(new DocumentListener(){

                public void changedUpdate(DocumentEvent documentEvent) {
                    double speed = Double.parseDouble(blocky.getText());
                    mbo.updateBlockSpeed(speed);
                }
                public void insertUpdate(DocumentEvent documentEvent) {
                    double speed = Double.parseDouble(blocky.getText());
                    mbo.updateBlockSpeed(speed);
                }
                public void removeUpdate(DocumentEvent documentEvent) {
                    //double speed = Double.parseDouble(bs.getText());
                    //mbo.updateBlockSpeed(speed);
                }
            });        
    }// </editor-fold> 
    
        private static void addBlockLengthListener(JTextField trainLength){
            // <editor-fold defaultstate="collapsed" desc="Block Length">
            trainLength.getDocument().addDocumentListener(new DocumentListener(){
        	
 		      public void changedUpdate(DocumentEvent documentEvent) {
 		        //double position = Double.parseDouble(trainLength.getText());
                        //trackModel.updatePosition(position);
 		      }
 		      public void insertUpdate(DocumentEvent documentEvent) {
 		        //double position = Double.parseDouble(trainLength.getText());
                        //trackModel.updatePosition(position);
 		      }
 		      public void removeUpdate(DocumentEvent documentEvent) {
 		        //double position = Double.parseDouble(trainLength.getText());
                        //trackModel.updatePosition(position);
		      }
 		      
            });        
                      
	}// </editor-fold> 
    
        private static void addIsStationListener(JTextField trainIsStation){
            final JTextField s = trainIsStation;
            // <editor-fold defaultstate="collapsed" desc="Is Station">
            trainIsStation.getDocument().addDocumentListener(new DocumentListener(){
        	
 		      public void changedUpdate(DocumentEvent documentEvent) {
 		        boolean position = Boolean.parseBoolean(s.getText());
                        //trackModel.updatePosition(position);
 		      }
 		      public void insertUpdate(DocumentEvent documentEvent) {
 		        //double position = Double.parseDouble(trainIsStation.getText());
                        //trackModel.updatePosition(position);
 		      }
 		      public void removeUpdate(DocumentEvent documentEvent) {
 		        //double position = Double.parseDouble(trainIsStation.getText());
                        //trackModel.updatePosition(position);
		      }
 		      
            });        
                      
	}// </editor-fold> 
    
	
	/////////////////////////////////
	//TRAIN MODEL LISTENERS
	/////////////////////////////////
        
	/////////////////////////////////
	//TRAIN CONTROLLER LISTENERS
	/////////////////////////////////
	
	/////////////////////////////////
	//MBO LISTENERS
	/////////////////////////////////
        private static void addMBRBListener(JRadioButton MovingBlockRadio){
            // <editor-fold defaultstate="collapsed" desc="MBRB">
        MovingBlockRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(((JRadioButton)e.getSource()).isSelected())
                    office.setMovingBlock();
                else
                    office.setFixedBlock();
            }
        });

	}// </editor-fold> 
    
        private static void addStartButtonListener(JButton Start){
            // <editor-fold defaultstate="collapsed" desc="MBO Start Button">
		
            Start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    if(!timer.isRunning())
                        timer.start();

                    StringBuffer s = new StringBuffer();
                    schedule = mbo.getSchedule();

                    for(ScheduleNode n : schedule){
                        s.append(n.getStop());
                        s.append(n.getTimes());
                    }

                    office.textSchedule.setText(s.toString());
                }
            });

	}// </editor-fold> 
        
}