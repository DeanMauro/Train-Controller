/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wisdominator
 */
public class DoorController {
    private boolean doors;
    
    //constucter to iniciating the door status (closed)
    public DoorController(){
        doors = false;
    }
    
    //Open door method
    public void openDoors(){
        doors = true;
    }
    
    //Close door method
    public void closeDoors(){
        doors = false;
    }
    
    //Return current door state
    public boolean doorState(){
        return doors;
    }
}
