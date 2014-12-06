
import java.util.*;
/**
 *
 * @author jom84
 */
public class ScheduleNode {
    private ArrayList<Double> times = new ArrayList();
    private String stop;
    private StringBuffer timeString = new StringBuffer();
    
    public ScheduleNode(String stop, double time){
        this.stop = stop;
        times.add(time);
    }
    
    public void addTime(double time){
        times.add(time);
    }
    
    public String toString(){
        System.out.print(stop + ": ");
        for(Double i : times){
            System.out.print(i + " ");
        }
        System.out.println();
        return stop;
    }
    
    public String getStop(){
        return stop + ": ";
    }
    
    public String getTimes(){
        for(double n : times){
            timeString.append(String.valueOf(n));
            timeString.append(", ");
        }
        timeString.append("\n");
        
        return timeString.toString();
    }
}
