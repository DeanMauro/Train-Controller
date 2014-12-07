import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.Timer;

class Wrapper {
	
	protected static Office office;
	protected static TrackModel trackModel;
        protected static TrackModelInterface trackModelInterface;
	protected static TrainModel trainModel;
	protected static TrainController trainController;
	protected static MovingBlockOverlayUI mbo;
        
        protected static ArrayList<ScheduleNode> schedule;
        protected static int timerDelay = 1000;
        protected static ActionListener ClockListener;
        protected static int seconds = 0;
        protected static int minutes = 0;
        protected static int totalSeconds = 0;
        protected static Timer timer;

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
            trainControllerFrame	.add(trainController.getTrainControllerUI());
		
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
 * I initialized the variables in a separate method in case anything 
 * special needs to be done first that could not have simply been done
 * up top where the variables are declared (e.g. if a constructor 
 * takes parameters or requires another component to be initialized
 * first.
 * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static void initialize(){
            
            
		
            office 		= new Office();
            trackModel          = new TrackModel();
            trackModelInterface = new TrackModelInterface();
            trainModel          = new TrainModel();
            trainController     = new TrainController(1, trainModel);
            office.addTrain();
            mbo 		= new MovingBlockOverlayUI();

            addIncreaseClockSpeedListener(office.buttonIncreaseClockSpeed);
            addDecreaseClockSpeedListener(office.buttonDecreaseClockSpeed);
            
            addStartButtonListener(mbo.StartButton);
            addBlockSpeedListener(trackModelInterface.trainSpeed);
            addBlockLengthListener(trackModelInterface.trainLength);
            addIsStationListener(trackModelInterface.trainIsStation);
            addMBRBListener(mbo.MovingBlockRadio);
            
            
            
            

           ClockListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              minutes = minutes + (seconds / 60);
              seconds = seconds % 60;
              
                
              Office.textClock.setText(String.format("%d:%02d", minutes, seconds));
              
              seconds++;
              totalSeconds++;
              trainModel.calculateDeltaT(totalSeconds);
              
              trainModel.calculateForce();
              trainModel.calculateAcceleration();
              trainModel.calculateSpeed();
              trainModel.calculatePosition();
              trainModel.calculateDeltaT(totalSeconds); 
              
              mbo.updateSpeed(trainModel.getSpeed());
              office.trainsOnTracks.get(0).textSpeed.setText(String.valueOf(trainModel.getSpeed()));
              
              mbo.updatePosition(trainModel.getPosition());
              trackModel.updatePosition(trainModel.getPosition());
              office.trainsOnTracks.get(0).textPosition.setText(String.valueOf(trainModel.getPosition()));
              
              mbo.updateBlockAuthority(mbo.getbauth());
              trainController.setMboAuthority(mbo.getbauth());
              trainController.setMboSpeed(mbo.getbspeed());
              
              trackModelInterface.queryButton();

            }
            };
           
           timer = new Timer(timerDelay, ClockListener);
           
	}
	
	
	
/* * * * * * * * * * * * * * * * * * * * * * * * * *
 * TRACK CIRCUIT 
 * * * * * * * * * * * * * * * * * * * * * * * * * */

	
	/////////////////////////////////
	//OFFICE LISTENERS
	/////////////////////////////////
        private static void addIncreaseClockSpeedListener(JButton IncreaseSpeed){
		
            IncreaseSpeed.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    timerDelay = 100;
                    timer.setDelay(timerDelay);
                }
            });

	}
        
        private static void addDecreaseClockSpeedListener(JButton DecreaseSpeed){
		
            DecreaseSpeed.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    timerDelay = 1000;
                    timer.setDelay(timerDelay);
                }
            });

	}
        
        
        
	private static void addOfficeRerouteListener(JButton Reroute){
		
            Reroute.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    /*If the "reroute" button on a train module in the Office is
                     pressed, the code here will send a new route to the
                     Train Controller for that specific train. */
                }
            });

	}
        
        private static void addSpeedAndAuthorityListener(JButton Send){
		
            Send.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                
                }
            });

	}
	
	private static void addRouteEnableListener(JTextField NewRoute){
		
            NewRoute.getDocument().addDocumentListener(new DocumentListener() {

                  public void changedUpdate(DocumentEvent documentEvent) {
                    /*The value in the "NewRoute" textfield will be tested.
                    If it is the name of a valid route, the "Reroute" button
                    will be enabled. If not, nothing will happen.*/
                  }
                  public void insertUpdate(DocumentEvent documentEvent) {
                    /*The value in the "NewRoute" textfield will be tested.
                    If it is the name of a valid route, the "Reroute" button
                    will be enabled. If not, nothing will happen.*/
                  }
                  public void removeUpdate(DocumentEvent documentEvent) {
                    /*The value in the "NewRoute" textfield will be tested.
                    If it is the name of a valid route, the "Reroute" button
                    will be enabled. If not, nothing will happen.*/
                  }

                });
	}

	/////////////////////////////////
	//TRACK MODEL LISTENERS
	/////////////////////////////////
        
        private static void addBlockSpeedListener(JTextField bs){
        bs.getDocument().addDocumentListener(new DocumentListener(){
            
            public void changedUpdate(DocumentEvent documentEvent) {
                double speed = Double.parseDouble(bs.getText());
                mbo.updateBlockSpeed(speed);
            }
            public void insertUpdate(DocumentEvent documentEvent) {
                double speed = Double.parseDouble(bs.getText());
                mbo.updateBlockSpeed(speed);
            }
            public void removeUpdate(DocumentEvent documentEvent) {
                //double speed = Double.parseDouble(bs.getText());
                //mbo.updateBlockSpeed(speed);
            }
        });        
    }
    
        private static void addBlockLengthListener(JTextField trainLength){
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
                      
	}
    
        private static void addIsStationListener(JTextField trainIsStation){
            trainIsStation.getDocument().addDocumentListener(new DocumentListener(){
        	
 		      public void changedUpdate(DocumentEvent documentEvent) {
 		        boolean position = Boolean.parseBoolean(trainIsStation.getText());
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
                      
	}
    
	
	/////////////////////////////////
	//TRAIN MODEL LISTENERS
	/////////////////////////////////
	
        private static void addSpeedListener(JTextField S){
        S.getDocument().addDocumentListener(new DocumentListener(){
            
            public void changedUpdate(DocumentEvent documentEvent) {
                double speed = Double.parseDouble(S.getText());
                mbo.updateSpeed(speed);
                trackModel.updateSpeed(speed);
            }
            public void insertUpdate(DocumentEvent documentEvent) {
                double speed = Double.parseDouble(S.getText());
                mbo.updateSpeed(speed);
                trackModel.updateSpeed(speed);
            }
            public void removeUpdate(DocumentEvent documentEvent) {
                double speed = Double.parseDouble(S.getText());
                mbo.updateSpeed(speed);
                trackModel.updateSpeed(speed);
            }
        });        
    }
        
        private static void addPositionListener(JTextField P){
        P.getDocument().addDocumentListener(new DocumentListener(){
            
            public void changedUpdate(DocumentEvent documentEvent) {
                double position = Double.parseDouble(P.getText());
                mbo.updatePosition(position);
                trackModel.updatePosition(position);
            }
            public void insertUpdate(DocumentEvent documentEvent) {
                double position = Double.parseDouble(P.getText());
                mbo.updatePosition(position);
                trackModel.updatePosition(position);
            }
            public void removeUpdate(DocumentEvent documentEvent) {
                double position = Double.parseDouble(P.getText());
                mbo.updatePosition(position);
                trackModel.updatePosition(position);
            }
        });        
    }
        
	/////////////////////////////////
	//TRAIN CONTROLLER LISTENERS
	/////////////////////////////////
	
	/////////////////////////////////
	//MBO LISTENERS
	/////////////////////////////////
        private static void addMBRBListener(JRadioButton MovingBlockRadio){
		
       MovingBlockRadio.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e)
           {
               if(MovingBlockRadio.isSelected())
                   office.setMovingBlock();
               else
                   office.setFixedBlock();
           }
       });

	}
    
        private static void addStartButtonListener(JButton Start){
		
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

	}
        
}