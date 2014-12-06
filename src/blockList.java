/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Zach Albert
 */
public class blockList {
    // reference to the head node.
    private Node head;
    private int listCount;
 
    // LinkedList constructor
    public blockList() {
        // this is an empty list, so the reference to the head node
        // is set to a new node with no data
        head = new Node(null, false, "");
        listCount = 0;
    }
 
    
    public void add(Block block)
    // appends the specified element to the end of this list.
    {
        Node blockTemp = new Node(block, block.isSwitchBoo(), block.getSwitchID());
        Node blockCurrent = head;
        
        //get to current spot
        while (blockCurrent.getNext1() != null) {
            blockCurrent = blockCurrent.getNext1();
        }
        
        blockCurrent.setNext1(blockTemp);
        
        if(!block.isSwitchBoo())//is not a block with a switch
        {
            // the last node's "next" reference set to our new node
            if(blockCurrent != head)
                blockTemp.setPrev1(blockCurrent);
            else
                blockTemp.setPrev1(null);
            blockTemp.setNext1(null);
        }
        else//block is a switch
        {
            //System.out.print("1");
            blockCurrent.setNext1(blockTemp);
            //System.out.print("2");
            blockTemp.setPrev1(blockCurrent);
            //System.out.print("4");
            blockCurrent = head;
            //System.out.print("3");
            
//            while (blockCurrent.getNext1() != null) 
//            {
//                //System.out.print("5");
//                if(blockCurrent.getSwitch())//check each to see if its a switch
//                {
//                    //System.out.print("7");
//                    System.out.println("current block: "+blockCurrent.getSwitchID());
//                    System.out.println("temp block: "+blockTemp.getSwitchID());
//                    if(blockCurrent.getSwitchID().equalsIgnoreCase(blockTemp.getSwitchID()))
//                    {
//                        //System.out.print("6");
//                        //blockCurrent.setPrev2(blockTemp);
//                        blockTemp.setNext2(blockCurrent);
//                        //System.out.print("8");
//                        break;
//                    }
//                }
//                blockCurrent = blockCurrent.getNext1();
//            }
            
        }
        
        listCount++;// increment the number of elements variable
        //System.out.print("out");
    }
 
    // returns the element at the specified position in this list.
    public void refactor()
    {
        //switch 12
        Node blockCurrent = head;
        Node blockTemp = head;
        int i = 9;//goes to 9 and 76
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<77;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        blockTemp.setPrev1(blockCurrent);
        
        //switch 6
        blockCurrent = head;
        blockTemp = head;
        i = 15;//goes to 15 and 1
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<1;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        blockTemp.setPrev1(blockCurrent);
        
        //switch 7
        blockCurrent = head;
        blockTemp = head;
        i = 27;//goes to 27 and 75
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<75;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        
        //switch 8
        blockCurrent = head;
        blockTemp = head;
        i = 32;//goes to 32 and 71
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<71;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        
        //switch 9
        blockCurrent = head;
        blockTemp = head;
        i = 38;//goes to 38 and 70
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<70;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        
        //switch 10
        blockCurrent = head;
        blockTemp = head;
        i = 43;//goes to 43 and 66
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<66;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        
        //switch 11
        blockCurrent = head;
        blockTemp = head;
        i = 52;//goes to 52 and 65
        for(int j = 0; j<i;j++)
        {
            blockCurrent = blockCurrent.getNext1();
        }
        for(int j = 0; j<65;j++)
        {
            blockTemp = blockTemp.getNext1();
        }
        blockCurrent.setNext2(blockTemp);
        
    }
    public Block get(int index)
    {
        // index must be 1 or higher
        if (index <= 0)
            return null;
 
        Node blockCurrent = head.getNext1();
        for (int i = 1; i < index; i++) {
            if (blockCurrent.getNext1() == null)
                return null;
 
            blockCurrent = blockCurrent.getNext1();
        }
        //System.out.println("block ID: "+blockCurrent.getBlockID());
        //if(blockCurrent.getPrev1() != null)
            //System.out.println("prev1: "+blockCurrent.getPrev1().getBlockID());
        //if(blockCurrent.getPrev2() != null)
            //System.out.println("prev2: "+blockCurrent.getPrev2().getBlockID());
        //if(blockCurrent.getNext1() != null)
            //System.out.println("next1: "+blockCurrent.getNext1().getBlockID());
        //if(blockCurrent.getNext2() != null)
            //System.out.println("next2: "+blockCurrent.getNext2().getBlockID());
        //System.out.println();
        return blockCurrent.getBlock();
    }
    
    public Node getNode(int index)
    {
        // index must be 1 or higher
        if (index <= 0)
            return null;
 
        Node blockCurrent = head.getNext1();
        for (int i = 1; i < index; i++) {
            if (blockCurrent.getNext1() == null)
                return null;
 
            blockCurrent = blockCurrent.getNext1();
        }
        return blockCurrent;
    }
 
    public int size()
    // returns the number of elements in this list.
    {
        return listCount;
    }
 
    public String toString() {
        Node blockCurrent = head.getNext1();
        String output = "";
        while (blockCurrent != null) {
            output += "[" + blockCurrent.getData().toString() + "]";
            blockCurrent = blockCurrent.getNext1();
        }
        return output;
    }
 
    public class Node {
        // reference to the next node in the chain,
        // or null if there isn't one.
        Node next1;
        Node next2;
        Node prev1;
        Node prev2;
        // data carried by this node.
        // could be of any type you need.
        Object data;
        Block block;
        boolean isSwitch;
        String switchBlockID;
        int blockID;
 
        // Node constructor
        //block, if the block is a switch, and the int value of switch
        public Node(Block bloc, boolean switchStatus, String switchBlock) {
            next1 = null;
            next2 = null;
            prev1 = null;
            prev2 = null;
            block = bloc;
            isSwitch = switchStatus;
            switchBlockID = switchBlock;
        }
 
        public Object getData() {
            return data;
        }
        
        public boolean getSwitch() {
            return isSwitch;
        }
        
        public String getSwitchID() {
            return switchBlockID;
        }
        public Block getBlock() {
            return block;
        }
        public int getBlockID() {
            return block.getBlockID();
        }
 
        public void setData(Object dataValue) {
            data = dataValue;
        }
 
        public Node getNext1() {
            return next1;
        }
        public Node getPrev1() {
            return prev1;
        }
        public Node getNext2() {
            return next2;
        }
        public Node getPrev2() {
            return prev2;
        }
 
        public void setNext1(Node nextValue) {
            next1 = nextValue;
        }
        
        public void setPrev1(Node nextValue) {
            prev1 = nextValue;
        }
        public void setNext2(Node nextValue) {
            next2 = nextValue;
        }
        
        public void setPrev2(Node nextValue) {
            prev2 = nextValue;
        }
    }
}
