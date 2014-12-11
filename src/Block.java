public class Block {
	
	private String section;
	private int blockId;
	private double length;
	private double grade;
	private int speedLimit;
	private boolean station;
	private boolean switchInfra;
        private int switchID;
	private boolean underground;
	private boolean crossing;
	private boolean switchToYard;
	private boolean switchFromYard;
	private String stationName;
	private String infrastructure;
	private double elevation;
	private double cumElevation;
	private int switchBlock;
	private String arrowDirection;
	
        private int NextBlockId;
        private int PrevBlockId;
	private int SecondNextBlockId;
	private int thirdNextBlockId;
	private boolean isNextBlockSwitch;
	private int nextBlockSwitchId;
	private int currentlySwitchedTo;
        private int blockSwitchId1;
	private int blockSwitchId2;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private boolean yard;
	private String line;
	private boolean closed;
	private int signalState;
	private int crossingSigState;
        public boolean twoFromStation;
        public boolean failState;
	
	private boolean trainDetected;

	//Constructor
	public Block(String[] blockInfo){
            line = blockInfo[0];
            section = blockInfo[1];
            if(!blockInfo[2].equals(""))
                blockId = Integer.parseInt(blockInfo[2]);
            if(!blockInfo[3].equals(""))
                length = Double.parseDouble(blockInfo[3]);
            if(!blockInfo[4].equals(""))
                grade = Double.parseDouble(blockInfo[4]);
            if(!blockInfo[5].equals(""))
                speedLimit = Integer.parseInt(blockInfo[5]);
            infrastructure = blockInfo[6];
            stationName = blockInfo[7];
            if(!blockInfo[8].equals(""))
                elevation = Double.parseDouble(blockInfo[8]);
            if(!blockInfo[9].equals(""))
                cumElevation = Double.parseDouble(blockInfo[9]);
            if(blockInfo.length <= 10)
            {
                switchBlock = 999;
                arrowDirection = "";
                startX = 0;
                startY = 0;
                endX = 0;
                endY = 0;
            }
            else if(blockInfo.length <=11)
            {
                String TempSwitchBlock = blockInfo[10];
                
                if(TempSwitchBlock.length() > 0)
                {
                    switchBlock = Integer.parseInt(TempSwitchBlock.substring(TempSwitchBlock.length() - 1));
                }  
                else
                {
                    switchBlock = 999;
                }
                //System.out.println("switch block: "+switchBlock);
                arrowDirection = "";
                startX = 0;
                startY = 0;
                endX = 0;
                endY = 0;
            }
            else
            {
                String TempSwitchBlock = blockInfo[10];
                
                if(TempSwitchBlock.length() > 0)
                {
                    switchBlock = Integer.parseInt(TempSwitchBlock.substring(TempSwitchBlock.length() - 1));
                }  
                else
                {
                    switchBlock = 999;
                }
                //System.out.println("switch block: "+switchBlock);
                arrowDirection = (blockInfo[11]);
                startX = Integer.parseInt(blockInfo[12]);
                startY = Integer.parseInt(blockInfo[13]);
                endX = Integer.parseInt(blockInfo[14]);
                endY = Integer.parseInt(blockInfo[15]);
            }

            station = false;
            switchInfra = false;
            underground = false;
            crossing = false;
            switchToYard = false;
            switchFromYard = false;
            signalState = 1;
            crossingSigState = 0;

            trainDetected=false;

            closed = false;

//            System.out.println("You got here 2");
//
//            System.out.println(infrastructure);
//            System.out.println("You got here 2.5");
//            System.out.println(infrastructure.contains("Station"));
            if(infrastructure.contains("STATION")){
                    station=true;
            }
            //System.out.println("You got here 3");
            if(infrastructure.contains("SWITCH")){
                //System.out.println(infrastructure);
                    switchInfra=true;
            }
            else
            {
                switchInfra=false;
            }
            //System.out.println("You got here 4");
            if(infrastructure.contains("RAILWAY CROSSING")){
                    crossing = true;
            }
            if(infrastructure.contains("UNDERGROUND")){
                    underground = true;
            }
            if(infrastructure.contains("SWITCH TO YARD")){
                    switchToYard = true;
            }
            if(infrastructure.contains("SWITCH FROM YARD")){
                    switchFromYard = true;
            }
            if(infrastructure.contains("SWITCH TO/FROM YARD")){
                    switchToYard = true;
                    switchFromYard = true;
            }
            if(infrastructure.contains("YARD")){
                    yard = true;
            }
            PrevBlockId = 999;
            NextBlockId = 999;
		
	}
	
	//the following methods are getters and setters for the block attributes
	
	public String getTrackLine(){
		return line;
	}
	
	public boolean isTrainDetected(){
		return trainDetected;
	}
	
	public void setTrainDetected(boolean cond){
		trainDetected=cond;
	}
	
	public String getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(String infrastructure) {
		this.infrastructure = infrastructure;
	}

	public int getNextBlockId() {
		return NextBlockId;
	}

	public void setNextBlockId(int nextBlockId) {
		NextBlockId = nextBlockId;
	}
        
        public int getPrevBlockId() {
		return PrevBlockId;
	}

	public void setPrevBlockId(int nextBlockId) {
		PrevBlockId = nextBlockId;
	}

        public void setBlockSwitchId1(int nextBlockSwitchId) {
		this.blockSwitchId1 = nextBlockSwitchId;
	}
        
        public void setBlockSwitchId2(int nextBlockSwitchId) {
		this.blockSwitchId2 = nextBlockSwitchId;
	}

        public int getBlockSwitchID1(){
            return this.blockSwitchId1;
        }
        
        public int getBlockSwitchID2(){
            return this.blockSwitchId2;
        }
        
	public int getCurrentlySwitchedTo() {
		return currentlySwitchedTo;
	}

	public void setCurrentlySwitchedTo(int currentlySwitchedTo) {
		this.currentlySwitchedTo = currentlySwitchedTo;
	}
        
        public boolean isSwitchInfra() {
		return switchInfra;
	}
        
        public void setSwitchInfra(boolean switchInfra) {
		this.switchInfra = switchInfra;
	}
        
        public int getSwitchID() {
		return switchBlock;
	}

	public boolean isYard() {
		return yard;
	}

	public void setYard(boolean yard) {
		this.yard = yard;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

	public boolean isStation() {
		return station;
	}

	public void setStation(boolean station) {
		this.station = station;
	}

	public boolean isUnderground() {
		return underground;
	}

	public void setUnderground(boolean underground) {
		this.underground = underground;
	}

	public boolean isCrossing() {
		return crossing;
	}

	public void setCrossing(boolean crossing) {
		this.crossing = crossing;
	}

	public boolean isSwitchToYard() {
		return switchToYard;
	}

	public void setSwitchToYard(boolean switchToYard) {
		this.switchToYard = switchToYard;
	}

	public boolean isSwitchFromYard() {
		return switchFromYard;
	}

	public void setSwitchFromYard(boolean switchFromYard) {
		this.switchFromYard = switchFromYard;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public double getCumElevation() {
		return cumElevation;
	}

	public void setCumElevation(double cumElevation) {
		this.cumElevation = cumElevation;
	}
	
	public int[] getPossibleNextBlocks(){
		int[] next = {NextBlockId, thirdNextBlockId};
		return next;
	}
	
	public int[] getPossiblePrevBlocks(){
		// change this for redline
		int[] prev = {SecondNextBlockId, -1};
		return prev;
	}
	
	public boolean isClosed(){
		return closed;
	}
	
	public void setClosed(boolean closed){
		this.closed = closed;
	}
	
	public void setSignalState(int state){
		signalState = state;
	}
	
	public int getSignalState(){
		return signalState;
	}
	
	public void setCrossingSignalState(int state){
		crossingSigState = state;
	}
	
	public int getCrossingSignalState(){
		return crossingSigState;
	}
        
        public int getStartX(){
            return startX;
        }
        
        public int getStartY(){
            return startY;
        }
        
        public int getEndX(){
            return endX;
        }
        
        public int getEndY(){
            return endY;
        }
        
        public void setTwoFromStation(boolean x){
            twoFromStation = x;
        }
        
        public boolean getTwoFromStation(){
            return twoFromStation;
        }
        
        public void setFailState(){
            if(failState)
                failState = false;
            else
                failState = true;
        }
        
        public boolean getFailState(){
            return failState;
        }
}