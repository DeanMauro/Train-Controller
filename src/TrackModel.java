/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Zach Albert
 */
public class TrackModel 
{

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    TrackObject trckOb; 
    blockList blockLinkList;
    double trainSpeed;
    double trainDistance;
    int nodePostion;
    int nodeNum;
    public TrackModel()
    {
        trckOb = new TrackObject();
        blockLinkList = new blockList();
        trainSpeed = 0;
        trainDistance = 0;
        nodePostion = 0;
        nodeNum = 77;
    }
    
    
    public void updateSpeed(double speed)
    {
        trainSpeed = speed;
    }
    
    public void updatePosition(double position)
    {
        trainDistance = position;
    }
    
    public Block findBlockID()
    {
        double blockLength = 0;
        int blockSpeed = 0;
        double postion = trainDistance;
        Block x;
        while(true)
        {
            postion = trainDistance;
            //System.out.println("1");
            x = blockLinkList.get(nodeNum);
            //System.out.println(x.getBlockID());
            //System.out.println("2");
            blockLength = x.getLength();
            //System.out.println("block length: "+ blockLength);
            //System.out.println("postion: "+ postion);
            blockSpeed = x.getSpeedLimit();
            if(blockLength > postion)
                break;
            else
            {
                trainDistance -= blockLength;
                nodeNum = blockLinkList.getNode(nodeNum).getPrev1().getBlockID();
                nodePostion += 1;
            }
                        
        }
        return x;
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
                while (fileScan.hasNextLine()) 
                {
                    String line = fileScan.nextLine();
                    System.out.println(line);
                    String[] blockInfo = line.split(",");
                    //System.out.println("number of items:"+blockInfo.length);

                    trackLineColor=blockInfo[0];
                    trckOb.setLine(blockInfo[0]);
                    Block newBlock = new Block(blockInfo);
                    i++;
                    //trckOb.addBlock(newBlock);
                    blockLinkList.add(newBlock);
                    //System.out.println("back");
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
                blockLinkList.refactor();
                //Track.trackArray.put(trckOb.getLine(), trckOb);
                //Track.trackListData.add(trackLineColor);
                int x = 0;
                for(int j = 1; j< 78;j++)
                {
                    //System.out.println(x);
                    x++;
                    Block y = blockLinkList.get(j);
                    //System.out.println(y);
                    
                }
                
                
                return 1;
        } 
        catch (FileNotFoundException e) 
        {
                return 0;
        }	
    }
    
}
