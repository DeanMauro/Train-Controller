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
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private boolean yard;
	private String line;
	private boolean closed;
	private int signalState;
	private int crossingSigState;
	
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
            }
            //System.out.println(switchBlock);
//            if(!blockInfo[12].equals(""))
//                NextBlockId = Integer.parseInt(blockInfo[12]);
//            if(!blockInfo[13].equals(""))
//                SecondNextBlockId = Integer.parseInt(blockInfo[13]);
//            if(!blockInfo[14].equals(""))
//                thirdNextBlockId = Integer.parseInt(blockInfo[14]);
//            if(!blockInfo[15].equals(""))
//                isNextBlockSwitch = Boolean.parseBoolean(blockInfo[15]);
//            if(!blockInfo[16].equals(""))
//                nextBlockSwitchId = Integer.parseInt(blockInfo[16]);
//            System.out.println(blockInfo[17]);
//            if(!blockInfo[17].equals(""))
//                currentlySwitchedTo = Integer.parseInt(blockInfo[17]);
//            if(!blockInfo[18].equals(""))
//                startX = Double.parseDouble(blockInfo[18]);
//            if(!blockInfo[19].equals(""))
//                startY = Double.parseDouble(blockInfo[19]);
//            if(!blockInfo[20].equals(""))
//                endX = Double.parseDouble(blockInfo[20]);
//            if(!blockInfo[21].equals(""))
//                endY = Double.parseDouble(blockInfo[21]);
            

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

//	public boolean isBiDirectional() {
//		return BiDirectional;
//	}
//
//	public void setBiDirectional(boolean biDirectional) {
//		BiDirectional = biDirectional;
//	}
//
//	public boolean isCanGoToTwoPlaces() {
//		return canGoToTwoPlaces;
//	}
//
//	public void setCanGoToTwoPlaces(boolean canGoToTwoPlaces) {
//		this.canGoToTwoPlaces = canGoToTwoPlaces;
//	}

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

        public int getBlockSwitchID1()
        {
            return this.blockSwitchId1;
        }
        
        public int getBlockSwitchID2()
        {
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
	
	public int[] getPossibleNextBlocks()
	{
		int[] next = {NextBlockId, thirdNextBlockId};
		return next;
	}
	
	public int[] getPossiblePrevBlocks()
	{
		// change this for redline
		int[] prev = {SecondNextBlockId, -1};
		return prev;
	}
	
	public boolean isClosed()
	{
		return closed;
	}
	
	public void setClosed(boolean closed)
	{
		this.closed = closed;
	}
	
	/*
	 * Signal States
	 * 1 - Super Green
	 * 2 - Green
	 * 3 - Yellow
	 * 4 - Red
	 * *//////////
	public void setSignalState(int state)
	{
		signalState = state;
	}
	
	public int getSignalState()
	{
		return signalState;
	}
	
	// 0 - Crossing sig off
	// 1 - Crossing sig on
	public void setCrossingSignalState(int state)
	{
		crossingSigState = state;
	}
	
	public int getCrossingSignalState()
	{
		return crossingSigState;
	}
        
}