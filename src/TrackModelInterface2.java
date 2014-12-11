/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coelabs
 * Zach Albert
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;

/**
 *
 * @author coelabs
 */
public class TrackModelInterface2 extends JFrame
{

	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT = 600;
	
	private final String[] mLabelStrings = new String[] {"Type:", "Line:", "Section:", "Direction:", "Length (ft):", "Speed Limit (mph):", "Grade (%):", "Elevation (ft): ", "Cumulative Elevation (ft):", "is Underground:", "Train Present:", "Track Heater:"};
	
	private TrackModel trackModel = null;
	private ArrayList<JLabel> mObjectPropertyLabels;
	private ArrayList<JButton> mFailureModeButtons;
	private Object mSelectedObject;
        int lineColor = 0;
        int[][] locationRed;
        int IDID = 0;
        private Point point2;
        private Line2D.Double line2d;
        private Point2D point1;
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new TrackModelInterface2();
			}
		});
	}
	
	public TrackModelInterface2(){
                locationRed = new int[78][5];
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// create/set track model
		if (trackModel == null)
                {
			trackModel = new TrackModel();
                }
		else
                {
			trackModel = trackModel;
                }
		
		// get content pane and set layout
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// create buttons panel and set layout
                JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// fill buttons panel with components
		JButton importTrack = new JButton("Import Track");
                importTrack.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            String path = System.getProperty("user.dir");
                            System.out.println(path);
                            JFileChooser openFile = new JFileChooser(path);
                            openFile.showOpenDialog(null);
                            String fileName = openFile.getSelectedFile().toString();
                            System.out.println(fileName);
			    //{
			       try
			       {
                                   if(trackModel.InternalImport(fileName) == 1)
                                       System.out.println("Good File");
                                   else
                                       System.out.println("Bad File");
                                   
                                   repaint();
//			    	      BufferedReader bufferedReader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
//			    	      String line = "";
//			    	      
//			    	      int lineNumber = 0;
//			    	      while ((line = bufferedReader.readLine()) != null)
//			    	      {
//			    	    	  	 lineNumber++;
//			    	    	  	 
//			    	    	  	 // skip title line
//			    	    	  	 if (lineNumber == 1)
//			    	    	  		 continue;
//			    	    	  	 
//			    	    	     String[] values = line.split(",");
//			    	    	     
//			    	    	     TrackBlock b = new TrackBlock(values[0], values[1], Integer.parseInt(values[2]));
//			    	    	     if (b.getLine().equals(TrackModel.LINE_GREEN))
//			    	    	    	 	b.mPaintColor = Color.GREEN;
//			    	    	     else if (b.getLine().equals(TrackModel.LINE_RED))
//			    	    	    	 	b.mPaintColor = Color.RED;
//			    	    	     else
//			    	    	    	 	continue;
//			    	    	     
//			    	    	     b.setLength(Float.parseFloat(values[3]));
//			    	    	     b.setGrade(Float.parseFloat(values[4]));
//			    	    	     b.setSpeedLimit(Integer.parseInt(values[5]));
//			    	    	     
//			    	    	     if (values[6].contains("SWITCH"))
//			    	    	    	 	b.setIsSwitch(true);
//			    	    	     else
//			    	    	    	 	b.setIsSwitch(false);
//			    	    	     if (values[6].contains("UNDERGROUND"))
//			    	    	    	 	b.setUnderground(true);
//			    	    	     else
//			    	    	    	 	b.setUnderground(false);
//			    	    	     
//			    	    	     b.setElevation(Float.parseFloat(values[7]));
//			    	    	     b.setCumulativeElevation(Float.parseFloat(values[8]));
//			    	    	     
//			    	    	     b.setPreviousBlockNumber(0, Integer.parseInt(values[9]));
//			    	    	     b.setNextBlockNumber(0, Integer.parseInt(values[10]));
//			    	    	     b.setPreviousBlockNumber(1, Integer.parseInt(values[11]));
//			    	    	     b.setNextBlockNumber(1, Integer.parseInt(values[12]));
//			    	    	     
//			    	    	     // get normal drawing coordinates
//			    	    	     b.mX1 = Integer.parseInt(values[13]);
//			    	    	     b.mY1 = Integer.parseInt(values[14]);
//			    	    	     b.mX2 = Integer.parseInt(values[15]);
//			    	    	     b.mY2 = Integer.parseInt(values[16]);
//			    	    	     
//			    	    	     // get other drawing coordinates
//			    	    	     b.mOtherX1 = Integer.parseInt(values[17]);
//			    	    	     b.mOtherY1 = Integer.parseInt(values[18]);
//			    	    	     b.mOtherX2 = Integer.parseInt(values[19]);
//			    	    	     b.mOtherY2 = Integer.parseInt(values[20]);
//			    	    	     
//			    	    	     mTrackModel.addTrackBlock(b.getLine(), b.getBlockNumber(), b);
//			    	    	     if (TrackModel.DEBUG) System.out.println("TrackModel: Added block number " + b.getBlockNumber());
//			    	      }
//			    	      
//			    	      bufferedReader.close();
//			    	      
//			    	      // add yard
//			    	      TrackYard y = new TrackYard(630, 320);
//			    	      mTrackModel.setTrackYard(y);
//			    	      
//			    	      // repaint display
//			    	      repaint();
//			    	      
//			    	      mTrackModel.setupTrackController();
//			    	      
//			    	      /* testing stuff */
//			    	      
////			    	      int[] switches = mTrackModel.getSwitchBlocks("Green");
////			    	      for (int i : switches)
////			    	    	  	System.out.println("TrackModel: Switch at block number " + i);
//			    	      
//			    	      int[] blockPath = mTrackModel.getBlockPath(TrackModel.LINE_GREEN, false);
//			    	      System.out.print("TrackModel: path = ");
//			    	      for (int i = 0; i < blockPath.length; i++)
//			    	      {
//			    	    	  	System.out.print(blockPath[i]);
//			    	    	  	if (i != blockPath.length - 1)
//				    	    	  	System.out.print("->");
//			    	    	  	else
//			    	    	  		System.out.println();
//			    	      }

			       }
			       catch (Exception e2)
			       {
			    	      e2.printStackTrace();
			       }
			    //}
			}
		});
                buttons.add(importTrack);
                JButton deleteTrack = new JButton("Delete Track");
                deleteTrack.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            trackModel = new TrackModel();
                            repaint();
                        }
  
		});
                buttons.add(deleteTrack);
                buttons.setPreferredSize(new Dimension(500, 100));
                contentPane.add(buttons, BorderLayout.PAGE_END);
    
		// create blockAttr panel and set layout
		JPanel blockAttr = new JPanel();
		blockAttr.setLayout(new GridLayout(0, 3));
		blockAttr.setPreferredSize(new Dimension(500, 0)); // only care about width
		
		// fill blockAttr panel with components
                JLabel l = new JLabel("");
                blockAttr.add(l);
		l = new JLabel("Block Attrributes");
                l.setHorizontalAlignment(SwingConstants.CENTER);
		blockAttr.add(l);
                l = new JLabel("");
                blockAttr.add(l);
                mObjectPropertyLabels = new ArrayList<JLabel>();
                for (String mLabelString : mLabelStrings) {
                    l = new JLabel(mLabelString);
                    mObjectPropertyLabels.add(l);
                    blockAttr.add(l);
                }
                contentPane.add(blockAttr, BorderLayout.CENTER);
	
		
		// this is the canvas component where the track is painted
		JComponent map = new JComponent(){
			@Override
			public void paintComponent(Graphics g){
//				System.out.println("painting canvas");
				
				g.clearRect(0, 0, getWidth(), getHeight());
				
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, getWidth(), getHeight());
				
				System.out.println("width: " + getWidth());
				System.out.println("height: " + getHeight());
				
				// draw yard
				g.setColor(Color.BLUE);
                                Graphics2D g2d = (Graphics2D) g;
                                

                               // draw blocks
                                g2d.setStroke(new BasicStroke(2f));
                                g2d.scale(1.75,1.75);
                                g2d.translate(-140, -100);
                                for(int i = 1; i< trackModel.getNumBlocks();i++)
                                {
                                    Block b = trackModel.getTrackObject().getBlock(i);
                                    if(b.isStation())
                                    {
                                        g2d.setColor(Color.BLUE);
                                    }
                                    else if(b.isTrainDetected())
                                    {
                                        g2d.setColor(Color.BLACK);
                                    }
                                    else
                                    {
                                        if(lineColor == 0)//red
                                        {
                                            g2d.setColor(Color.RED);
                                        }
                                        else
                                        {
                                            g2d.setColor(Color.GREEN);
                                        }
                                    }
                                    g2d.draw(new Line2D.Double(b.getStartX(),b.getStartY(),b.getEndX(),b.getEndY()));
                                }
                                
				
//                                int i = mTrackModel.
//				for (TrackBlock b : mTrackModel.getTrackBlocks(TrackModel.LINE_RED))
//				{
//					if (b.mDrawOther)
//						g.drawLine(b.mOtherX1, b.mOtherY1, b.mOtherX2, b.mOtherY2);
//					else
//						g.drawLine(b.mX1, b.mY1, b.mX2, b.mY2);
//				}
				
				// if there is a selected object
				if (mSelectedObject != null)
				{
					// cast graphics to graphics2d and set stroke
					g2d.setStroke(new BasicStroke(3));
					
					// if the object is a track block
//					if (mSelectedObject instanceof TrackBlock)
//					{
//						// cast object to a track block
//						TrackBlock b = (TrackBlock) mSelectedObject;
//						
//						// set color and draw line
//						g2d.setColor(b.mPaintColor);
//						g2d.drawLine(b.mX1, b.mY1, b.mX2, b.mY2);
//						
//						// enable failure mode buttons
//						for (JButton button : mFailureModeButtons)
//							button.setEnabled(true);
//					}
//					
//					// else if the object is a track yard
//					else if (mSelectedObject instanceof TrackYard)
//					{
//						// cast object to a track block
//						y = (TrackYard) mSelectedObject;
//						
//						// set color and draw rectangle
//						g2d.setColor(Color.BLACK);
//						g2d.drawRect(y.mX, y.mY, y.mWidth, y.mHeight);
//						
//						// disable failure mode buttons
//						for (JButton button : mFailureModeButtons)
//							button.setEnabled(false);
//					}
				}
//				
//				// else there is no selected object
//				else
//				{
//					// disable failure mode buttons
//					for (JButton button : mFailureModeButtons)
//						button.setEnabled(false);
//				}
                        }
		};
		map.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get click coordinates
				int clickX = e.getX();
				int clickY = e.getY();
                                
				
				// check if clicked green track block
//				for (TrackBlock b : mTrackModel.getTrackBlocks(TrackModel.LINE_GREEN))
//				{
//					if (new Line2D.Double(b.mX1, b.mY1, b.mX2, b.mY2).intersects(clickX - 3, clickY - 3, 7, 7))
//					{
//						if (TrackModel.DEBUG) System.out.println("TrackModel: found clicked object = green block");
//						
//						mObjectPropertyLabels.get(0).setText(mLabelStrings[0] + " Block");
//						mObjectPropertyLabels.get(1).setText(mLabelStrings[1] + " " + b.getLine());
//						mObjectPropertyLabels.get(2).setText(mLabelStrings[2] + " " + b.getSection());
//						mObjectPropertyLabels.get(3).setText(mLabelStrings[3]);
//						mObjectPropertyLabels.get(4).setText(mLabelStrings[4] + " " + (b.getLength() * 3.28084)); // m to ft
//						mObjectPropertyLabels.get(5).setText(mLabelStrings[5] + " " + (b.getSpeedLimit() * 0.621371)); // km/hr to mi/hr
//						mObjectPropertyLabels.get(6).setText(mLabelStrings[6] + " " + b.getGrade());
//						mObjectPropertyLabels.get(7).setText(mLabelStrings[7] + " " + (b.getElevation() * 3.28084)); // m to ft
//						mObjectPropertyLabels.get(8).setText(mLabelStrings[8] + " " + (b.getCumulativeElevation() * 3.28084)); // m to ft
//						mObjectPropertyLabels.get(9).setText(mLabelStrings[9] + " " + b.isUnderground());
//						
//						// mark selected object and repaint
//						mSelectedObject = b;
//						repaint();
//						return;
//					}
//				}
				
				// check if clicked red track block
				// TODO: repeat clicked algorithm for red blocks

				// check if clicked track yard
//				TrackYard y = mTrackModel.getTrackYard();
//				if ((y != null) && (new Rectangle2D.Double(y.mX, y.mY, y.mWidth, y.mHeight).intersects(clickX - 3, clickY - 3, 7, 7)))
//				{
//					if (TrackModel.DEBUG) System.out.println("TrackModel: found clicked object = yard");
//					
//					mObjectPropertyLabels.get(0).setText(mLabelStrings[0] + " YARD");
//					for (int i = 1; i < mLabelStrings.length; i++)
//					{
//						mObjectPropertyLabels.get(i).setText(mLabelStrings[i] + " N/A");
//					}
//					
//					// mark selected object and repaint
//					mSelectedObject = y;
//					repaint();
//					return;
//				}
				
//				if (TrackModel.DEBUG) System.out.println("no objects selected");
//				
//				// reset labels
//				for (int i = 0; i < mLabelStrings.length; i++)
//				{
//					mObjectPropertyLabels.get(i).setText(mLabelStrings[i]);
//				}
//				
//				// unmark current selected object and repaint
//				mSelectedObject = null;
//				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
                            System.out.println("hey");
                            point1 = e.getPoint();
                            System.out.println(point1);
                        }

			@Override
			public void mouseReleased(MouseEvent e) {
                            point2 = e.getPoint();
                            line2d = new Line2D.Double(point1, point2); 
                            System.out.println(point2);
                            repaint();
                        }

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
                        
                        public void mouseDragged(MouseEvent e) {}	
		});
		map.setPreferredSize(new Dimension(0, 400));
		// add panels to content pane
		//contentPane.add(buttons, BorderLayout.NORTH);
		contentPane.add(map, BorderLayout.PAGE_START);
		
		
		// show frame
		setVisible(true);
	}
        
        /**
     *
     */
        public class TrackModel {

        //    /**
        //     * @param args the command line arguments
        //     */
        //    public static void main(String[] args) {
        //        // TODO code application logic here
        //    }
            TrackObject trackObject; 
            //blockList blockLinkList;
            double trainSpeed;
            double totalDistance;
            int nodePostion;
            int trainId;
            int numberTrains;
            int numBlocks;
            double[][] trainDist = new double[100][3];
            
            public TrackModel(){
                trackObject = new TrackObject();
                //blockLinkList = new blockList();
                trainSpeed = 0;
                totalDistance = 10000;
                nodePostion = 0;
                
                for(int i=0; i<100; i++){
                    trainDist[i][2] = 77;
                }
            }

            //gets the current trains speed
            public void updateSpeed(double speed){
                trainSpeed = speed;
            }
    
            //gets the new total distance the current train traveled
            public void updatePosition(double position){
                totalDistance = position;
                System.out.println("Total distance: "+ totalDistance);
            }
    
            //gets train ID of the current one being tracked
            public void updateTrainId(int ID){
                trainId = ID;
            }

            //update block object to train true or false
            public void findBlockID(){
                //System.out.println("train ID: "+ trainId);
                //System.out.println("train distance: "+ trainDist[trainId-1][1]);
                double newDist = totalDistance - trainDist[trainId-1][1];
                //System.out.println("new distance: "+ newDist);
                //trainDist[trainId-1][1] += newDist;
                //System.out.println("train distance: "+ trainDist[trainId-1][1]);
                double blockLength = 0;
                int blockSpeed = 0;
                Block b;
                while(true)
                {
                    //System.out.println("1");

                    b = trackObject.getBlock((int)trainDist[trainId-1][2]);
                    //System.out.println("on block: "+b.getBlockId());
                    //System.out.println("2");
                    blockLength = b.getLength();
                    //System.out.println("block length: "+ blockLength);
                    //System.out.println("postion: "+ postion);
                    blockSpeed = b.getSpeedLimit();
                    //System.out.println("block length: "+ blockLength);
                    if(blockLength > newDist)
                    {
                        trainDist[trainId-1][2] = b.getBlockId();
                        //trainDist[trainId-1][1] = 0;
                        System.out.println("on block: "+b.getBlockId());
                        redraw(b.getBlockId());
                        //trainDist[trainId-1][1] = 0;
                        break;
                    }
                    else
                    {
                        trainDist[trainId-1][1] += blockLength;
                        newDist -= blockLength;//trainDist[trainId-1][1] -= blockLength;
                        //newDist -= blockLength;
                        b.setTrainDetected(false);
                        trainDist[trainId-1][2] = trackObject.getBlock((int)trainDist[trainId-1][2]).getPrevBlockId();
                    }

                }
            }

            //redraw with new color 
            public void redraw(int ID){
                Block b = trackObject.getBlock(ID);
                b.setTrainDetected(true);
                repaint();
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
                            //System.out.println(line);
                            blockInfo = line.split(",");
                            //System.out.println("number of items:"+blockInfo.length);

                            trackLineColor=blockInfo[0];
                            trackObject.setLine(blockInfo[0]);
                            newBlock = new Block(blockInfo);
                            i++;
                            newBlock.setYard(false);
                            trackObject.addBlock(newBlock);
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
//                        if(i>0)
//                        {
//                            newBlock = new Block(blockInfo);
//                            newBlock.setYard(true);
//                            i++;
//                            newBlock.setBlockId(i);
//                            trackObject.addBlock(newBlock);     
//                        }
                        System.out.println("Num blocks: "+i);
                        numBlocks = i;

                        //refactor table
                        trackObject.refactor();
                        //for loop is for debugging purposes
//                        for(int x = 1;x<trackObject.getNumBlocks();x++)
//                        {
//                            Block temp = trackObject.getBlock(x);
//
//
//                            System.out.println("iterator: "+x);//print iterator
//                            System.out.println("blockID: "+temp.getBlockId());//print block ID
//                            System.out.println("nextBlock: "+temp.getNextBlockId());//print next block ID 
//                            System.out.println("is main switch: "+temp.isSwitchInfra());//print if main switch
//                            System.out.println("currently switched to: "+temp.getCurrentlySwitchedTo());//print who it is switched to
//                            System.out.println("switch option 1: "+temp.getBlockSwitchID1());//print switch option 1
//                            System.out.println("switch option 2: "+temp.getBlockSwitchID2());//print switch option 2
//                            System.out.println("Prev block: "+temp.getPrevBlockId());//print switch option 2
//                            System.out.println("");//print 
//                            System.out.println("");//print 
//                        }
                        return 1;
                } 
                catch (FileNotFoundException e) 
                {
                        return 0;
                }	
            }

            //get the number of trains running
            public void setNumTrains(int n){
                numberTrains = n;
            }

            //return 0 for red and 1 for green
            public int getLineColor(){
                if(trackObject.getLine().equals("Red"))
                    return 0;
                else
                    return 1;    
            }
            
            //return number of blocks on track
            public int getNumBlocks(){
                return trackObject.getNumBlocks();
            }
            
            public TrackObject getTrackObject(){
                return trackObject;
            }
        }
        
        //getter for the track model so the wrapper has access
        public TrackModel getTrackModel(){
            return trackModel;
        }
}
