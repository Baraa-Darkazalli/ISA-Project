/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GAMING
 */
public class Path {
    private Station StartStation;
    private Station EndStation;
    private float Distance;
    private boolean TransportEnabled;
    private List<String> BusNames;
    private float TaxiSpeed;
    private float BusSpeed;
    
    //Constructer
    public Path()
    {
        StartStation=new Station();
        EndStation=new Station();
        BusNames=new ArrayList<>();
    }
    
    public void SetStartStation(Station StartStation){this.StartStation=StartStation;}
    public Station GetStartStation(){return this.StartStation;}
    
    public void SetEndStation(Station EndStation){this.EndStation=EndStation;}
    public Station GetEndStation(){return this.EndStation;}
    
    public void SetDistance(float Distance){this.Distance=Distance;}
    public float GetDistance(){return this.Distance;}
    
    public void SetTransportEnabled(boolean TransportEnabled){this.TransportEnabled=TransportEnabled;}
    public boolean GetTransportEnabled(){return this.TransportEnabled;}
    
    public void SetBusNames(List<String> BusNames){this.BusNames=BusNames;}
    public List<String> GetBusNames(){return this.BusNames;}
    
    public void setBusSpeed(float BusSpeed) {this.BusSpeed = BusSpeed;}
    public float getBusSpeed() {return BusSpeed;}

    public void setTaxiSpeed(float TaxiSpeed) {this.TaxiSpeed = TaxiSpeed;}
    public float getTaxiSpeed() {return TaxiSpeed;}
    

    public int CalcTimeBetweenStations(Transport Transport){
        if(Transport instanceof Taxi)return Math.round((Distance/TaxiSpeed)*60);
        else if(Transport instanceof Bus)return Math.round((Distance/BusSpeed)*60);
        else return Math.round((Distance/new Walk().GetSpeed())*60);
    }
    
    public float CalcNewMoneyCost(Transport Transport){
        if(Transport instanceof Taxi)return Transport.GetMoneyCost()*this.Distance;
        return Transport.GetMoneyCost();
    } 
    
    public float CalcNewHealthCost(Transport Transport){
        return this.Distance*Transport.GetHealthCost();
    }
    
    
}
