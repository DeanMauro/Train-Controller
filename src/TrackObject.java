

import java.util.Hashtable;


public class TrackObject {
	
	private String line;
	private Hashtable<Integer, Block> blockDB;
	private boolean circuitDown;
	private boolean powerDown;
        private int numBlocks;
	
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
            System.out.println("Infrastructure");
            for(int x = 0;x<numBlocks;x++)
            {
                System.out.print((switchInfra[x][0]+1)+"\t"+switchInfra[x][1]+"\n");
            }
            System.out.println("Switch Block");
            for(int x = 0;x<numBlocks;x++)
            {
                System.out.print(switchBlock[x][0]+1+"\t"+switchBlock[x][1]+"\n");
            }
            
            
            for(int i = 1;i<numBlocks+1;i++)
            {
                Block b = getBlock(i);
                b.setNextBlockId(i+1);
                b.setPrevBlockId(i-1);

            }
 
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
        }

}