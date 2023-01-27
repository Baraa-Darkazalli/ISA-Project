/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author MSI GAMING
 */
public class State {
    private Person Person;
    private int Time;
    private Pair<TransportType,String>Transport; 
    private State FatherState;
    private Station Station;
    
    public State(){
        Person=new Person();
        Time=0;
        Transport=new Pair<>(TransportType.Walk,"");
        FatherState=null;
        Station=Game.FirstStation;
    }
    
    //Stters And Getters
    public void SetPerson(Person Person){this.Person=Person;}
    public Person GetPerson(){return this.Person;}
    
    public void SetTime(int Time){this.Time=Time;}
    public int GetTime(){return this.Time;}
    
    public void SetFatherState(State FatherState){this.FatherState=FatherState;}
    public State GetFatherState(){return this.FatherState;}
    
    public void SetStation(Station Station){this.Station=Station;}
    public Station GetStation(){return this.Station;}
    
    public void SetTransport(Pair<TransportType, String> Transport) {this.Transport = Transport;}
    public Pair<TransportType, String> GetTransport() {return Transport;}
    
    
    
    
    
    //CheckMove
    public HashMap<Path,HashMap<TransportType,Boolean>> CheckMovesTransports(){
        HashMap<Path,HashMap<TransportType,Boolean>> CheckMoves=new HashMap<>();
        for(Path Path : Game.Paths)
        {
            HashMap<TransportType,Boolean>Transports=new HashMap<>();
            if(Path.GetStartStation().GetStationName()!=this.Station.GetStationName())continue;
            Transports.put(TransportType.Walk,true);
            if(Path.GetTransportEnabled()){
                Transports.put(TransportType.Taxi,true);
                if(Path.GetBusNames().isEmpty())Transports.put(TransportType.Bus,false);
                else Transports.put(TransportType.Bus,true);
            }
            else {
                Transports.put(TransportType.Taxi,false);
                Transports.put(TransportType.Bus,false);
            }
            CheckMoves.put(Path,Transports);
        }       
        return CheckMoves;
    }
    
    //Move
    public void Move(Path Path){
        boolean SameBus=false;
        Transport Transport;
        switch(this.Transport.getKey())
        {
            case Taxi:
                Transport = new Taxi();
                break;
            case Bus:
                Transport = new Bus();
                break;
            default:
                Transport =new Walk();
                break;
        }
        
        //Add Wait Time
        if(this.FatherState.Transport.getKey()!=this.Transport.getKey() ||
                (
                    this.FatherState.Transport.getKey()==TransportType.Bus &&
                    this.Transport.getKey()==TransportType.Bus &&
                    this.FatherState.Transport.getValue()!=this.Transport.getValue()
                )
            )
        {
            Time+=Station.GetArriavalTime(this.Transport.getKey());
        }
        
        //Change Person Money
        if(this.Transport.getKey()==TransportType.Bus && this.FatherState.Transport.getKey()==this.Transport.getKey())
        {
            if(this.FatherState.Transport.getValue()==this.Transport.getValue())SameBus=true;
        }
        if(!SameBus)
        {
            this.Person.SetMoney(this.Person.GetMoney()-Path.CalcNewMoneyCost(Transport));
        }    
        
        //Change Time
        Time+=Path.CalcTimeBetweenStations(Transport);      
        
        //Change Person Health
        this.Person.SetHealth(this.Person.GetHealth()-Path.CalcNewHealthCost(Transport));
        
        //Change Station
        this.Station=Path.GetEndStation();        
    }
    
    //GetNextStates
    public List<State> GetNextStates(){
        List<State> NextStates = new ArrayList<>();
        
        //Check Moves
        HashMap<Path,HashMap<TransportType,Boolean>> CheckMoves=this.CheckMovesTransports();
        State CopyState;  
        for (Map.Entry<Path,HashMap<TransportType,Boolean>> entry : CheckMoves.entrySet())
        {
            for(Map.Entry<TransportType,Boolean> entry1 : entry.getValue().entrySet())
            {
                
                if(entry1.getKey()==TransportType.Bus && entry1.getValue())
                {
                    for(String BusName : entry.getKey().GetBusNames())
                    {
                        CopyState=this.DeepCopy();
                        
                        Pair<TransportType,String> newTransport=new Pair<>(TransportType.Bus,BusName);
                        CopyState.SetTransport(newTransport);
                        CopyState.Move(entry.getKey());
                        
                        if(CopyState.IsWrongState())continue;
                        NextStates.add(CopyState);
                    }
                }
                else if(entry1.getValue())
                {
                    CopyState=this.DeepCopy();
                    Pair<TransportType,String> newTransport=new Pair<>(entry1.getKey(),"");
                    CopyState.SetTransport(newTransport);
                    CopyState.Move(entry.getKey());

                    if(CopyState.IsWrongState())continue;
                    NextStates.add(CopyState);
                }  
            }
        }
        
        return NextStates;
    }
    
    //IsFinish
    public boolean IsFinish(){
        return Station.GetStationName() == Game.FinishStation.GetStationName();
    }
    
    //IsEqual
    public boolean IsEqual(State State){
        if(State.Person.GetHealth()!= this.Person.GetHealth() || State.Person.GetMoney()!=this.Person.GetMoney())return false;
        if(State.Time!=this.Time)return false;
        if(State.GetStation().GetStationName()!= this.GetStation().GetStationName())return false;
        if(State.GetStation().GetTransportArrivalTime().size()!=this.GetStation().GetTransportArrivalTime().size())return false;
        if(State.GetTransport().getKey()!=this.GetTransport().getKey())return false;
        if(State.GetTransport().getValue()!=this.GetTransport().getValue())return false;
        return true;
    }
    
    //DeepCopy
    public State DeepCopy(){
        State newState=new State();
        
        newState.Person=new Person();
        newState.Person.SetHealth(this.Person.GetHealth());
        newState.Person.SetMoney(this.Person.GetMoney());

        newState.Time=this.Time;
        
        newState.Station=new Station();
        newState.Station.SetStationName(this.GetStation().GetStationName());
        HashMap<TransportType,Integer>newTransportArrivalTime=new HashMap<>();
        this.Station.GetTransportArrivalTime().forEach((key,value)->{
            newTransportArrivalTime.put(key,value);
        });
        newState.Station.SetTransportArrivalTime(newTransportArrivalTime);
        Pair<TransportType,String>newTransport=new Pair<>(this.Transport.getKey(),this.Transport.getValue());
        
        newState.FatherState=this;
 
        return newState;
    }

    //IsWrongState
    public boolean IsWrongState(){
        if(this.Person.GetHealth()<=0)return true;
        if(this.Person.GetMoney()<=0)return true;
        return false;
    }
    
    //GetHeuristic
    public float GetHeuristic(){
        float H=0;
        return CalcHeuristic(this.Station,H);
    }
    
    //CalcHeuristic
    private float CalcHeuristic(Station Station,float distance){
        if(Station.GetStationName()==Game.FinishStation.GetStationName())return distance;
        float MinDistance=Float.MAX_VALUE;
        Station NextStation=new Station();
        for(Path Path : Game.Paths)
        {
            if(Path.GetStartStation().GetStationName()!=Station.GetStationName())continue;
            
            if(Path.GetDistance()<MinDistance){
                MinDistance=Path.GetDistance();
                NextStation=Path.GetEndStation();
            }
        }
        return CalcHeuristic(NextStation,MinDistance+distance);
    }
    
    
    //Get Cost
    public float GetCost()
    {
        return (Game.cost==-1)?CalcCostIsWorsTime():
        (Game.cost==1)?CalcCostIsTime():
        (Game.cost==2)?CalcCostIsMoney():
        (Game.cost==3)?CalcCostIsHealth():CalcCostIsAll();
    }
    
    //Cost is Time
    private float CalcCostIsTime()
    {
        return (float)Time;
    }
    //Cost is Money
    private float CalcCostIsMoney()
    {
        return -(Person.GetMoney());
    }
    //Cost is Health
    private float CalcCostIsHealth()
    {
        return -(Person.GetHealth());
    }
    //Cost is All
    private float CalcCostIsAll()
    {
        return -(Person.GetMoney()*100/Game.PersonMoney)
                -(Person.GetHealth()*100/Game.PersonHealth)
                -((100-Time)*100/Game.WorstTime);



        //        float MaxDistances=0;
//        for (Path path: Game.Paths) {
//            MaxDistances+=path.GetDistance();
//        }
//        float MaxTime=(MaxDistances/Game.WalkSpeed)*60;
    }
    private float CalcCostIsWorsTime(){return (float)-Time;}
    
}
