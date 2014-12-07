/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wisdominator
 */

import java.util.Random;

public class PassengerManager {
    public static final double PASSENGER_MASS = 80; //the weight is in KG
    public static final int MAX_PASSENGER = 222; //capasity of the train 
    
    private int passengerCount;
    private double totalPassengerMass;
    private Random generator;
    
    //constructor for passenger manager
    public PassengerManager(){
        passengerCount = 1;  //at least there is one driver on the train
        totalPassengerMass = passengerCount * PASSENGER_MASS;
        generator = new Random();
    }
    
    //this method updates passenger number and it is aways between 1 and 222
    public int updatePassengerCount(){
        passengerCount = (Math.abs(generator.nextInt()) % MAX_PASSENGER) + 1;
        totalPassengerMass = passengerCount * PASSENGER_MASS;
        return passengerCount;
    }
    
    //this method updates the total passenger mass
    public double getTotalPassengerMass(){
        return totalPassengerMass;
    }
    
    //this method clear the passengers 
    public void clearPassengers(){
        passengerCount = 1;
        totalPassengerMass = PASSENGER_MASS * passengerCount;
    }
    
}
