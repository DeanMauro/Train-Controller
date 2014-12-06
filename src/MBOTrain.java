package my.MovingBlockOverlay;
/*
Jeffrey O Mitchell
Class for tracking train info
*/

import java.util.*;


/**
 *
 * @author jom84
 */
public class MBOTrain{
    
    private Hashtable<String,int[]> stops;
    private int[] shady_array = new int[2];
    private int[] herron_array = new int[2];
    private int[] swiss_array = new int[2];
    private int[] penn_array = new int[2];
    private int[] steel_array = new int[2];
    private int[] first_array = new int[2];
    private int[] station_array = new int[2];
    private int[] south_array = new int[2];
    private int id;
    private int pass_onboard;
    private double speed;
    private double location;
    private double safe_speed;
    private double safe_auth;
    
    public MBOTrain(int id, double speed, double loc){
        this.id = id;
        this.speed = speed;
        this.location = loc;
        
        stops = new Hashtable();
        stops.put("Shady",shady_array);
        stops.put("Herron",herron_array);
        stops.put("Swiss",swiss_array);
        stops.put("Penn",penn_array);
        stops.put("Steel",steel_array);
        stops.put("First",first_array);
        stops.put("Station",station_array);
        stops.put("South",south_array);
        
        pass_onboard = 0;
        
        safe_speed = 0;
        safe_auth = 0;
    }
    
    public void update(double speed, double loc){
        this.speed = speed;
        this.location = loc;
    }
    
    public void reachedStop(String k, int on, int off){
        int[] count_array = stops.get(k);
        count_array[0] = on;
        count_array[1] = off;
        stops.put(k, count_array);
        pass_onboard = pass_onboard + on - off;
    }
    
    public void setSafes(double s_speed, double s_auth){
        safe_speed = s_speed;
        safe_auth = s_auth;
    }
    
    public String getSpeed(){
        return String.valueOf(speed);
    }
    
    public String getLoc(){
        return String.valueOf(location);
    }
    
    public String getTotal(){
        return String.valueOf(pass_onboard);
    }
    
    public Hashtable getStops(){
        return stops;
    }
    
    public double getSafeSpeed(){
        return safe_speed;
    }
    
    public double getSafeAuthority(){
        return safe_auth;
    }
    
    public String toString(){
        String ret = String.valueOf(id);
        return ret;
    }
}
