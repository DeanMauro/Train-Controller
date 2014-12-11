

import java.util.ArrayList;
import java.util.Hashtable;


public class TrackObject {
	
	private String line;
	private Hashtable<Integer, Block> blockDB;
	private boolean circuitDown;
	private boolean powerDown;
        private int numBlocks;
        public ArrayList<String> stationList = new ArrayList<String>();
	
	/**
	 * Constructor method for TrackObject.java
	 * creates block database 
	 */ 
	public TrackObject(){
		blockDB= new Hashtable<Integer, Block>();
		circuitDown = false;
		powerDown = false;
                numBlocks = 0;
	}
	
	/**
	 * adds blocks of track to DB
	 * @param b ->block to add
	 */
	public void addBlock(Block b){
		int id = b.getBlockId();
		blockDB.put(id, b);
                numBlocks += 1;
		
	}
	
	public boolean getPowerDown(){
		return powerDown;
	}
	
	public boolean getCircuitDown(){
		return circuitDown;
	}
	
	public void setPowerDown(boolean b){
		powerDown=b;
	}
	
	public void setCircuitDown(boolean b){
		circuitDown=b;
	}
	
	public void setLine(String lineName){
		line = lineName;
	}
	
	public String getLine(){
		return line;
	}
	
	public Block getBlock(int id){
		return blockDB.get(id);
	}
        
        public int getNumBlocks(){
            return numBlocks;
        }
        
        public void refactor(){
            int[][] switchBlock = new int[numBlocks][2];
            int[][] switchInfra = new int[numBlocks][2];
        
            //creates/updates the arrays that will be used to create correct track
            
            for(int i = 1;i<numBlocks;i++) {
                //System.out.println(i);
                Block b = getBlock(i);
                //Block checkBlock = blockCurrent.getBlock();
                if(b.isSwitchInfra())//the node is a main switch
                {
                    switchInfra[i][0] = i;
                    switchInfra[i][1] = 1;
                    switchBlock[i][0] = i;
                    switchBlock[i][1] = 999;
                }
                else
                {
                    switchInfra[i][0] = i;
                    switchInfra[i][1] = 0;
                    switchBlock[i][0] = i;
                    switchBlock[i][1] = b.getSwitchID();
                }
  
            }
            //System.out.println("Infrastructure");
            for(int x = 0;x<numBlocks;x++)
            {
                //System.out.print((switchInfra[x][0]+1)+"\t"+switchInfra[x][1]+"\n");
            }
            //System.out.println("Switch Block");
            for(int x = 0;x<numBlocks;x++)
            {
                //System.out.print(switchBlock[x][0]+1+"\t"+switchBlock[x][1]+"\n");
            }
            
            //set up track
            for(int i = 1;i<numBlocks+1;i++)
            {
                Block b = getBlock(i);
                if(i!= numBlocks)
                    b.setNextBlockId(i+1);
                if(i!= numBlocks)
                    b.setPrevBlockId(i-1);

            }
            
            if(getLine().equals("Red"))
            {
                //create station list
                for(int i = 1;i<numBlocks;i++)
                {
                    Block b = getBlock(i);
                    if(b.isStation())
                    {
                        stationList.add(b.getStationName());
                    }
                }
                
                //create switchs
                Block b;
                //switch 12
                b = getBlock(9);
                b.setBlockSwitchId1(77);
                b.setBlockSwitchId2(10);
                b.setPrevBlockId(8);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(77);
                b = getBlock(77);
                b.setPrevBlockId(9);
                
                //swtich 6
                b = getBlock(15);
                b.setBlockSwitchId1(1);
                b.setBlockSwitchId2(16);
                b.setPrevBlockId(14);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(1);
                b = getBlock(1);
                b.setPrevBlockId(15);
                
                //switch 7
                b = getBlock(27);
                b.setBlockSwitchId1(28);
                b.setBlockSwitchId2(76);
                b.setPrevBlockId(26);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(28);
                b = getBlock(28);
                b.setPrevBlockId(27);
                
                //switch 8
                b = getBlock(32);
                b.setBlockSwitchId1(33);
                b.setBlockSwitchId2(72);
                b.setPrevBlockId(31);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(33);
                b = getBlock(33);
                b.setPrevBlockId(32);
                
                //switch 9
                b = getBlock(38);
                b.setBlockSwitchId1(39);
                b.setBlockSwitchId2(71);
                b.setPrevBlockId(37);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(39);
                b = getBlock(39);
                b.setPrevBlockId(38);
                
                //switch 10
                b = getBlock(43);
                b.setBlockSwitchId1(44);
                b.setBlockSwitchId2(67);
                b.setPrevBlockId(42);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(44);
                b = getBlock(44);
                b.setPrevBlockId(43);
                
                //switch 11
                b = getBlock(52);
                b.setBlockSwitchId1(53);
                b.setBlockSwitchId2(66);
                b.setPrevBlockId(51);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(53);
                b = getBlock(53);
                b.setPrevBlockId(52);
                
            }
            if(getLine().equals("Green"))
            {
                for(int i = 1;i<numBlocks;i++)
                {
                    Block b = getBlock(i);
                    if(b.isStation())
                    {
                        stationList.add(b.getStationName());
                    }
                }
                
                for(int i = 1;i<13;i++)
                {
                    Block b = getBlock(i);
                    if(i == 1)
                    {
                        b.setPrevBlockId(12);
                        b.setNextBlockId(i+1);
                    }
                    else if(i == 12)
                    {
                        b.setNextBlockId(11);
                        b.setPrevBlockId(13);
                    }
                    else
                    {
                        b.setPrevBlockId(i-1);
                        b.setNextBlockId(i+1);
                    }

                }
                
                //create switchs
                Block b;
                
                //switch 0
                b = getBlock(62);
                b.setBlockSwitchId1(61);
                b.setBlockSwitchId2(152);
                b.setPrevBlockId(63);
                b.setCurrentlySwitchedTo(2);
                b.setNextBlockId(152);
                b = getBlock(152);
                b.setPrevBlockId(62);
                
                
                //switch 4
                b = getBlock(76);
                b.setBlockSwitchId1(77);
                b.setBlockSwitchId2(107);
                b.setPrevBlockId(77);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(107);
                b = getBlock(77);
                b.setPrevBlockId(78);
                b.setNextBlockId(76);
                
                
                //switch 4
                b = getBlock(76);
                b.setBlockSwitchId1(77);
                b.setBlockSwitchId2(107);
                b.setPrevBlockId(77);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(107);
                b = getBlock(77);
                b.setPrevBlockId(78);
                b.setNextBlockId(76);
                
                
                
                //switch 5
                b = getBlock(86);
                b.setBlockSwitchId1(85);
                b.setBlockSwitchId2(100);
                b.setPrevBlockId(85);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(100);
//                b = getBlock(13);
//                b.setPrevBlockId(14);
//                b.setNextBlockId(12);
                
                
                //switch 2
                b = getBlock(29);
                b.setBlockSwitchId1(28);
                b.setBlockSwitchId2(150);
                b.setPrevBlockId(30);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(28);
                b = getBlock(150);
                b.setPrevBlockId(29);
                b.setNextBlockId(149);
                
                //switch 3
                b = getBlock(29);
                b.setBlockSwitchId1(28);
                b.setBlockSwitchId2(150);
                b.setPrevBlockId(30);
                b.setCurrentlySwitchedTo(1);
                b.setNextBlockId(28);
                b = getBlock(150);
                b.setPrevBlockId(29);
                b.setNextBlockId(149);
                
                
            }
        /*
            for(int i = 1;i<numBlocks+1;i++)
            {
                Block b = getBlock(i);
                int ID = b.getBlockId();
                if(b.isSwitchInfra())//it is a main switch
                {
                    
                    System.out.println("block: "+ID+" is a main switch");
                    int switchNum = b.getSwitchID();
                    //b.setNextBlockId(ID+1);
                    
                    int temp = 0;
                    for(int j = 1;j<numBlocks+1;j++)
                    {
                         Block bb = getBlock(j);

                         if(!bb.isSwitchInfra())
                         {
                             if(bb.getSwitchID() == switchNum)
                             {
                                 if(temp == 0)
                                 {
                                     b.setBlockSwitchId1(j);
                                     bb.setNextBlockId(ID);
                                     temp++;
                                 }
                                 else
                                 {
                                     b.setBlockSwitchId2(j);
                                     bb.setNextBlockId(ID);
                                 }
                             }
                         }
                    }
                    System.out.println("its next switches are: "+b.getBlockSwitchID1()+" and "+b.getBlockSwitchID2());
                    System.out.println(b.getBlockSwitchID1()+" prev block is: "+getBlock(b.getBlockSwitchID1()).getPrevBlockId());
                    System.out.println(b.getBlockSwitchID2()+" prev block is: "+getBlock(b.getBlockSwitchID2()).getPrevBlockId());
                    b.setCurrentlySwitchedTo(1);
                    //b.setNextBlockId(b.getBlockSwitchID1());
                    b.setPrevBlockId(b.getBlockSwitchID1());
                    if(b.getNextBlockId() == b.getBlockSwitchID1() || b.getNextBlockId() == b.getBlockSwitchID2())
                    {
                        b.setNextBlockId(ID-1);
                    }
                    if(b.getPrevBlockId() == b.getBlockSwitchID1() || b.getPrevBlockId() == b.getBlockSwitchID2())
                    {
                        b.setPrevBlockId(ID+1);
                    }
                }
                //else
                //{
                //    if(b.getNextBlockId() == 999)
                //        b.setNextBlockId(ID+1);
                //    if(b.getPrevBlockId() == 999)
                //        b.setPrevBlockId(ID-1);
                //}
            }
            
            int ID = 0;
            for(int i = 1;i<numBlocks+1;i++)
            {
                Block b = getBlock(i);
                ID = b.getBlockId();
                if(b.isYard())
                {
                    break;
                }
            }
            while(true)
            {
                ID = ID -1;
                Block b = getBlock(ID);
            }
                    */
        }

}