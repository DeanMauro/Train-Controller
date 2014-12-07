/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wisdominator
 */
public class LightController {
    
    private boolean lights;
    
    //an constuctor 
    public LightController(){
        lights = false;
    }
    
    //this method close lights
    public void closeLight(){
        lights = false;
    }
    
    //this method opend lights
    public void openLight(){
        lights = true;
    }
    
    //this method return current status of lights
    public boolean lightState(){
        return lights;
    }
    
    
}
