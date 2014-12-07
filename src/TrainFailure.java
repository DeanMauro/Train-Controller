/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wisdominator
 */

import java.util.ArrayList;

public class TrainFailure {
    ArrayList<String> failures;
    private String Signal = "Signal";
    private String Break = "Break";
    private String Engine = "Engine";
    
    //an constructor to init the failure
    public TrainFailure(){
        failures = new ArrayList<String>();
    }
    
    //return failure when called 
    public ArrayList<String> getFailure(){
        ArrayList<String> fail = new ArrayList<String>();
        if(failures.contains(Signal)){
            fail.add(Signal);
        }
        if(failures.contains(Break)){
            fail.add(Break);
        }
        if(failures.contains(Engine)){
            fail.add(Engine);
        }
        
        return fail;
    }
    
    //fix all the failures 
    public void fixAllFailures(){
        failures.clear();
    }
    
    //this method add engine failure
    public void addEngineFailure(){
        failures.add(Engine);
    }
    
    //this method fix engine failure
    public void fixEngineFailure(){
        failures.remove(Engine);
    }
    
    //this mehotd add signal failure
    public void addSignalFailure(){
        failures.add(Signal);
    }
    
    //this method fix signal failure
    public void fixSignalFailure(){
        failures.remove(Signal);
    }
    
    //this method add break failrue
    public void addBreakFailure(){
        failures.add(Break);
    }
    
    //this mehthod remove break failrue
    public void fixBreakFailrue(){
        failures.remove(Break);
    }
    
    //this method check if anything is broken
    public boolean isBroken(){
        if(failures.isEmpty()){
            return false;
        }else{
            return true;
        }
        
    }
    
    
}
