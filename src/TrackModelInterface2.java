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
        JComponent map;
	
	private final String[] mLabelStrings = new String[] {"Type:", "Line:", "Section:", "Direction:", "Length (ft):", "Speed Limit (mph):", "Grade (%):", "Elevation (ft): ", "Cumulative Elevation (ft):", "is Underground:", "Train Present:", "Track Heater:"};
	
	private TrackModel trackModel;
	private ArrayList<JLabel> mObjectPropertyLabels;
        int lineColor = 0;
        int[][] locationRed;
        int IDID = 0;
        private Point point2;
        private Line2D.Double line2d;
        private Point2D point1;
	
	public static void main(String[] args){
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
                //buttons.setLayout(new GridLayout(0, 3));
		
		// fill buttons panel with components
		JButton importTrack = new JButton("Import Track");
                importTrack.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            trackModel = new TrackModel();
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
			       }
			       catch (Exception e2)
			       {
			    	      e2.printStackTrace();
			       }
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
                final JTextField field = new JTextField(10);
                buttons.add(field);
                JButton failure1 = new JButton(" Broken Rail");
                failure1.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            int num = Integer.parseInt(field.getText());
                            trackModel.setFailState(num);
                            repaint();
                        }
  
		});
                buttons.add(failure1);
                JButton failure2 = new JButton("Track Circuit Failure");
                failure2.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            int num = Integer.parseInt(field.getText());
                            trackModel.setFailState(num);
                            repaint();
                            repaint();
                        }
  
		});
                buttons.add(failure2);
                JButton failure3 = new JButton(" Power Failure");
                failure3.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e){
                            int num = Integer.parseInt(field.getText());
                            trackModel.setFailState(num);
                            repaint();
                            repaint();
                        }
  
		});
                buttons.add(failure3);
                //JButton deleteTrack = new JButton("Delete Track");
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
		map = new JComponent(){
			@Override
			public void paintComponent(Graphics g){
				System.out.println("painting canvas");
				
				g.clearRect(0, 0, getWidth(), getHeight());
				
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, getWidth(), getHeight());
				
				//System.out.println("width: " + getWidth());
				//System.out.println("height: " + getHeight());
				
				// draw yard
				g.setColor(Color.BLUE);
                                Graphics2D g2d = (Graphics2D) g;
                                

                               // draw blocks
                                g2d.setStroke(new BasicStroke(2f));
                                if(trackModel.getNumBlocks() > 0)
                                {
                                    if(trackModel.getLineColor() == 0)//red
                                    {
                                        //g2d.scale(1.75,1.75);
                                        //g2d.translate(-140, -100);
                                    }
                                    else
                                    {
                                        //g2d.translate(0, 10);
                                    }
                                }
                                for(int i = 1; i< trackModel.getNumBlocks();i++)
                                {
                                    Block b = trackModel.getTrackObject().getBlock(i);
                                    if(b.getFailState())
                                    {
                                        g2d.setColor(Color.YELLOW);
                                    }
                                    else if(b.isStation())
                                    {
                                        g2d.setColor(Color.BLUE);
                                    }
                                    else if(b.isTrainDetected())
                                    {
                                        g2d.setColor(Color.BLACK);
                                    }
                                    else
                                    {
                                        if(trackModel.getLineColor() == 0)//red
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
                                
                        }
		};
		map.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get click coordinates
				int clickX = e.getX();
				int clickY = e.getY();
                                
				for (int i = 1; i<trackModel.getTrackObject().getNumBlocks();i++)       
				{
                                        Block b = trackModel.getTrackObject().getBlock(i);
					if (new Line2D.Double(b.getStartX(), b.getStartY(), b.getEndX(), b.getEndY()).intersects(clickX -3 , clickY-3, 7, 7))
					{
						//System.out.println("Intersects");
						mObjectPropertyLabels.get(0).setText(mLabelStrings[0] + " Block");
						mObjectPropertyLabels.get(1).setText(mLabelStrings[1] + " " + trackModel.getTrackObject().getLine());
						mObjectPropertyLabels.get(2).setText(mLabelStrings[2] + " " + b.getSection());
						mObjectPropertyLabels.get(3).setText(mLabelStrings[3]);
						mObjectPropertyLabels.get(4).setText(mLabelStrings[4] + " " + (b.getLength() * 3.28084)); // m to ft
						mObjectPropertyLabels.get(5).setText(mLabelStrings[5] + " " + (b.getSpeedLimit() * 0.621371)); // km/hr to mi/hr
						mObjectPropertyLabels.get(6).setText(mLabelStrings[6] + " " + b.getGrade());
						mObjectPropertyLabels.get(7).setText(mLabelStrings[7] + " " + (b.getElevation() * 3.28084)); // m to ft
						mObjectPropertyLabels.get(8).setText(mLabelStrings[8] + " " + (b.getCumElevation() * 3.28084)); // m to ft
						mObjectPropertyLabels.get(9).setText(mLabelStrings[9] + " " + b.isUnderground());
						
						// mark selected object and repaint
						repaint();
						return;
					}
                                        else
                                        {
                                            //System.out.println("Intersects NOT");
                                        }
				}
				
				
				// reset labels
				for (int i = 0; i < mLabelStrings.length; i++)
				{
					mObjectPropertyLabels.get(i).setText(mLabelStrings[i]);
				}
				
				// unmark current selected object and repaint
				repaint();
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
        
        public class TrackModel {

            TrackObject trackObject; 
            //blockList blockLinkList;
            double trainSpeed;
            double totalDistance;
            int nodePostion;
            int trainId;
            int numberTrains;
            int numBlocks;
            double[][] trainDistRed = new double[100][3];
            double[][] trainDistGreen = new double[100][3];
            
            public TrackModel(){
                trackObject = new TrackObject();
                //blockLinkList = new blockList();
                trainSpeed = 0;
                totalDistance = 10000;
                nodePostion = 0;
                
                for(int i=0; i<100; i++){
                    trainDistRed[i][2] = 77;
                    trainDistGreen[i][2] = 152;
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
                double newDist;
                if(getLineColor() == 0)
                    newDist = totalDistance - trainDistRed[trainId-1][1];
                else
                    newDist = totalDistance - trainDistGreen[trainId-1][1];
                double blockLength = 0;
                int blockSpeed = 0;
                Block b;
                while(true)
                {
                    if(getLineColor() == 0)
                        b = trackObject.getBlock((int)trainDistRed[trainId-1][2]);
                    else
                        b = trackObject.getBlock((int)trainDistGreen[trainId-1][2]);
                    blockLength = b.getLength();
                    blockSpeed = b.getSpeedLimit();
                    if(blockLength > newDist)
                    {
                        if(getLineColor() == 0)
                            trainDistRed[trainId-1][2] = b.getBlockId();
                        else
                            trainDistGreen[trainId-1][2] = b.getBlockId();
                        System.out.println("on block: "+b.getBlockId());
                        redraw(b.getBlockId());
                        break;
                    }
                    else
                    {
                        if(getLineColor() == 0)
                            trainDistRed[trainId-1][1] += blockLength;
                        else
                            trainDistGreen[trainId-1][1] += blockLength;
                        newDist -= blockLength;
                        b.setTrainDetected(false);
                        if(getLineColor() == 0)
                            trainDistRed[trainId-1][2] = trackObject.getBlock((int)trainDistRed[trainId-1][2]).getPrevBlockId();
                        else
                            trainDistGreen[trainId-1][2] = trackObject.getBlock((int)trainDistGreen[trainId-1][2]).getPrevBlockId();
                    }

                }
            }
            
            //determines if the block is two from station
            public void isTwoFromStation(Block b){
                Block s, next;
                s = b;
                double distance = 0;
                if(s.isStation())
                {
                    int a = s.getPrevBlockId();
                    s =  trackObject.getBlock(a);
                }
                next = s;
                int i = 0;
                while(true)
                {
                    if(next.isStation())
                    {
                        i++;
                        break;
                    }
                    else
                    {
                        //System.out.println(next.getPrevBlockId());
                        next = trackObject.getBlock(next.getPrevBlockId());
                        i++;
                    }
                }
                if(i == 2)
                    b.setTwoFromStation(true);
                else
                    b.setTwoFromStation(false);
            }
            
            //redraw with new color 
            public void redraw(int ID){
                Block b = trackObject.getBlock(ID);
                b.setTrainDetected(true);
                System.out.println("on block: "+b.getBlockId()+1);
                System.out.println("on block: "+b.getBlockId()+2);
                repaint();
                System.out.println("on block: "+b.getBlockId()+3);
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
            
            //gives ID of train....returns block train is on
            public Block getBlockTrainIsOn(int ID){
                Block b;
                if(getLineColor() == 0)
                {
                    b = trackObject.getBlock((int)trainDistRed[ID-1][2]);
                    return trackObject.getBlock((int)trainDistRed[ID-1][2]);
                }
                else
                {
                    b = trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                    return trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                }
                
            }
            
            public TrackObject getTrackObject(){
                return trackObject;
            }
            
            //get distance to next station from train block
            public double getNextStationDistance(int ID){
                Block b;
                if(getLineColor() == 0)
                    b = trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    b = trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                Block s, next;
                if(getLineColor() == 0)
                    s =  trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    s =  trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                double distance = 0;
                if(s.isStation())
                {
                    int a = s.getPrevBlockId();
                    s =  trackObject.getBlock(a);
                }
                next = s;
                while(true)
                {
                    if(next.isStation())
                    {
                        break;
                    }
                    else
                    {
                        distance += next.getLength();
                        next = trackObject.getBlock(next.getPrevBlockId());
                    }
                }
                return distance;
            }
            
            //get distance to next specific station from train block
            public double getNextSpecificStationDistance(int ID, String name){
                Block b;
                if(getLineColor() == 0)
                    b = trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    b = trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                Block s, next;
                if(getLineColor() == 0)
                    s =  trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    s =  trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                double distance = 0;
                if(s.isStation())
                {
                    int a = s.getPrevBlockId();
                    s =  trackObject.getBlock(a);
                }
                next = s;
                while(true)
                {
                    if(next.isStation() && next.getStationName().equals(name))
                    {
                        break;
                    }
                    else
                    {
                        distance += next.getLength();
                        next = trackObject.getBlock(next.getPrevBlockId());
                    }
                    if(distance>5000)
                    {
                        return 0;
                    }
                }
                return distance;
            }
            
            //returns the next stations name
            public String getNextStationName(int ID){
                Block b;
                if(getLineColor() == 0)
                    b = trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    b = trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                Block s, next;
                if(getLineColor() == 0)
                    s =  trackObject.getBlock((int)trainDistRed[ID-1][2]);
                else
                    s =  trackObject.getBlock((int)trainDistGreen[ID-1][2]);
                if(s.isStation())
                {
                    int a = s.getPrevBlockId();
                    s =  trackObject.getBlock(a);
                }
                next = s;
                while(true)
                {
                    if(next.isStation())
                    {
                        break;
                    }
                    else
                    {
                        next = trackObject.getBlock(next.getPrevBlockId());
                    }
                }
                
                return next.getStationName();
            }
            
            public void setFailState(int ID)
            {
                Block b = trackObject.getBlock(ID);
                b.setFailState();
            }
        }
        
        //getter for the track model so the wrapper has access
        public TrackModel getTrackModel(){
            return trackModel;
        }
        
        public JComponent getMap(){
            JComponent map2;
            map2 = new JComponent() {};
            map2 = map;
            return map2;
        }
}
