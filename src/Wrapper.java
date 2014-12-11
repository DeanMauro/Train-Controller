import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

class Wrapper {
	
	protected static Office office;
        protected static TrackModelInterface2 trackModelInterface;
	protected static Vector<TrainModel> trainModel;
	protected static Vector<TrainController> trainController;
        protected static TrainControllerUI trainControllerUI;
	protected static MovingBlockOverlayUI mbo;
        
        protected static ArrayList<ScheduleNode> schedule;
        protected static int numStations = 0;
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
            trainControllerFrame	.add(trainControllerUI);
		
            //Reset JFrames
	    officeFrame			.pack();
            trainControllerFrame	.pack();
		
            //Make JFrames Visible
	    officeFrame			.setVisible(true);
            trainControllerFrame	.setVisible(true);
            mbo          		.setVisible(true);
            trackModelInterface		.setVisible(true);
            
            
		
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
            
            
            /*Initialize modules*/
            office 		= new Office();
            trackModelInterface = new TrackModelInterface2();
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
            addMovingListener(mbo.MovingBlockRadio);
            addFixedListener(mbo.FixedBlockRadio);
            
            
            

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
              String station;
              double timeToStation;
              mbo.updateTrainList(trainModel);
              
              for(int i=0; i<numberOfTrains; i++){
                  currentTrain = trainModel.get(i);
                  station = schedule.get(currentTrain).getStop();
                  timeToStation = schedule.get(i).getTime().get()
                  
                  
                  /*Calculate Trains' new Speeds, Accelerations, Positions*/
                  currentTrain.update(totalSeconds);
                  
                  /*Update Track Model*/
                  trackModelInterface.getTrackModel().updateSpeed(currentTrain.getCurrentSpeed());
                  trackModelInterface.getTrackModel().updatePosition(currentTrain.getCurrentPosition());
                  trackModelInterface.getTrackModel().updateTrainId(currentTrain.getID());
                  trackModelInterface.getTrackModel().findBlockID();

                  
                  /*Calculate Trains' block position*/
                  currentTrain.updateBlockItems(trackModelInterface.getTrackModel().getBlockTrainIsOn(i+1));
                  
                  /*Update MBO*/
                  mbo.updateSpeed(currentTrain.getCurrentSpeed());
                  mbo.updatePosition(currentTrain.getCurrentPosition());
                  mbo.updateBlockAuthority(trackModelInterface.getTrackModel().getNextStationDistance(currentTrain.getID()), i);
                  mbo.updateBlockSpeed(trackModelInterface.getTrackModel().getBlockTrainIsOn(currentTrain.getID()).getSpeedLimit(),i);
                  
                  
                  /*Update Office*/
                  Train.setSpeed(office.trainsOnTracks.get(i), currentTrain.getCurrentSpeed());
                  Train.setPosition(office.trainsOnTracks.get(i), currentTrain.getCurrentPosition());
                  
                  
                  /*Update Train Controllers with new MBO Authorities*/
                  //mbo.updateBlockAuthority(trackModelInterface.getTrackModel().getNextStationDistance(i));
                  //mbo.updateBlockSpeed(trackModelInterface.getTrackModel().getCurrentBlock().getBlockSpeed);

                  Train.setAuthority(office.trainsOnTracks.get(i), mbo.getbauth(i));

                  /*Update Train Controllers*/
                  trainController.get(i).setSpeedLimit(currentTrain.getSpeedLimit());
                  trainController.get(i).setCtcAuthority(Train.getAuthority(office.trainsOnTracks.get(i)));
                  trainController.get(i).setCtcSpeed(Train.getSpeed(office.trainsOnTracks.get(i)));
                  trainController.get(i).setMboAuthority(mbo.getbauth(i));
                  trainController.get(i).setMboSpeed(mbo.getbspeed(i));
                  
                  
              }
              
              //trackModelInterface.getTrackModel().redraw();
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
            mbo.addTrain(numberOfTrains);
            
            //Start the clock if it is not already running
            if(!timer.isRunning())
                timer.start();
        }
	
        
        
        
/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * TRACK CIRCUIT 
 * These listeners are for all swing elements that
 * the user can interact with, and which change
 * values contained in other modules. They are down
 * here because it is unnecessary to call their methods
 * every clock tick and waste valuable time.
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
                    trainController.get(ID).setCtcSpeed(office.getSuggestedSpeed(ID));
                }
            });

	}// </editor-fold> 

        
	/////////////////////////////////
	//MBO LISTENERS
	/////////////////////////////////
        private static void addMovingListener(JRadioButton MovingBlockRadio){
            // <editor-fold defaultstate="collapsed" desc="MBRB">
        MovingBlockRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(((JRadioButton)e.getSource()).isSelected())
                    office.setMovingBlock();
            }
        });

	}// </editor-fold> 
        
        private static void addFixedListener(JRadioButton FixedBlockRadio){
            // <editor-fold defaultstate="collapsed" desc="MBRB">
        FixedBlockRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(((JRadioButton)e.getSource()).isSelected())
                    office.setFixedBlock();
            }
        });

	}// </editor-fold> 
    
        private static void addStartButtonListener(JButton Start){
            // <editor-fold defaultstate="collapsed" desc="MBO Start Button">
		
            Start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    int numTrainsToSpawn = schedule.get(0).getTimes().length();
                    numStations = schedule.size();

                    StringBuffer s = new StringBuffer();
                    schedule = mbo.getSchedule();

                    for(ScheduleNode n : schedule){
                        s.append(n.getStop());
                        s.append(n.getTimes());
                    }

                    office.textSchedule.setText(s.toString());
                    
                    if(!timer.isRunning())
                        timer.start();
                    
                    for(int i=0; i<numTrainsToSpawn; i++){
                        spawnTrain();
                    }
                    
                    
                }
            });

	}// </editor-fold> 
        
}
