/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.HashMap;

/**
 *
 * @author MSI GAMING
 */
public class Station {
    //Attributes
    private String StationName;
    private HashMap<TransportType,Integer> TransportArrivalTime;
    
    
    //Constructer
    public Station()
    {
        TransportArrivalTime= new HashMap<>();
    }
    
    //Set and Get
    public void SetStationName(String StationName){this.StationName=StationName;}
    public String GetStationName(){return this.StationName;}
    
    public void SetTransportArrivalTime(HashMap<TransportType,Integer> TransportArrivalTime){this.TransportArrivalTime=TransportArrivalTime;}
    public HashMap<TransportType,Integer> GetTransportArrivalTime(){return this.TransportArrivalTime;}
    
    public int GetArriavalTime(TransportType Transport)
    {
        if(TransportArrivalTime.get(Transport)==null)return 0;
        return TransportArrivalTime.get(Transport);
    }
}
