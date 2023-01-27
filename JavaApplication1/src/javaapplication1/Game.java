/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MSI GAMING
 */
public class Game {
    public static List<Station> Stations;
    public static List<Path> Paths;
    public static List<String> BusesNames;
    
    public static Station FinishStation;
    public static Station FirstStation;
    
    public static float BusMoneyCost;
    public static float BusHealthCost;
    
    public static float TaxiMoneyCost;
    public static float TaxiHealthCost;
    
    public static float WalkSpeed;
    public static float WalkMoneyCost;
    public static float WalkHealthCost;
    
    public static float PersonHealth;
    public static float PersonMoney;

    public static int cost;
    public static float WorstTime;
    
    public static void InitilizeGame()
    {
        Stations=new ArrayList<>();
        Paths=new ArrayList<>();
        BusesNames=new ArrayList<>();
        
        FinishStation= new Station();
        
        DeclareBusesNames();
        DeclarePerson();
        DeclareTransports();
        DeclareStations();
        DeclarePaths();

        CalcWorstTime();
    }
    
    
    private static void DeclareBusesNames(){
        BusesNames.add("Mhagren Senaa");
        BusesNames.add("Medan Senaa");

          
    }
    
    private static void DeclarePerson(){
        PersonHealth=100;
        PersonMoney=3000;
}
    
    private static void DeclareTransports(){
        BusMoneyCost=400;
        BusHealthCost=5;
        
        TaxiMoneyCost=1000;
        TaxiHealthCost=-5;
        
        WalkSpeed=5.5f;
        WalkMoneyCost=0;
        WalkHealthCost=10;
    }
    
    private static void DeclareStations(){
        

        Station Station = new Station();
        Station.SetStationName("Hamak");
        HashMap<TransportType,Integer> TransportArrivalTime=new HashMap<>();
        TransportArrivalTime.put(TransportType.Taxi,1);
        TransportArrivalTime.put(TransportType.Bus,15);
        Station.SetTransportArrivalTime(TransportArrivalTime);
        Stations.add(Station);
        
        FirstStation=Station;
        
        Station=new Station();
        Station.SetStationName("Zahera");
        TransportArrivalTime=new HashMap<>();
        TransportArrivalTime.put(TransportType.Taxi,5);
        TransportArrivalTime.put(TransportType.Bus,20);
        Station.SetTransportArrivalTime(TransportArrivalTime);
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("BabMosalla");
        TransportArrivalTime=new HashMap<>();
        TransportArrivalTime.put(TransportType.Taxi,2);
        TransportArrivalTime.put(TransportType.Bus,10);
        Station.SetTransportArrivalTime(TransportArrivalTime);
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("Fahhama");
        Stations.add(Station);
        
        
        Station=new Station();
        Station.SetStationName("JameAlhasan");
        TransportArrivalTime=new HashMap<>();
        TransportArrivalTime.put(TransportType.Taxi,10);
        TransportArrivalTime.put(TransportType.Bus,20);
        Station.SetTransportArrivalTime(TransportArrivalTime);
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("AlBetara");
        TransportArrivalTime=new HashMap<>();
        TransportArrivalTime.put(TransportType.Taxi,2);
        Station.SetTransportArrivalTime(TransportArrivalTime);
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("Soyika");
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("KaberAteka");
        Stations.add(Station);
        
        Station=new Station();
        Station.SetStationName("ZokakAlArbaein");
        Stations.add(Station);
        
        FinishStation=Station;
    }
    
    private static void DeclarePaths() {


        Path Path = new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(0));
        Path.SetEndStation(Stations.get(1));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        List <String> BusNames=new ArrayList<>();
        BusNames.add(BusesNames.get(0));
        BusNames.add(BusesNames.get(1));
        Path.SetBusNames(BusNames);
        Path.SetDistance(2.1f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(1));
        Path.SetEndStation(Stations.get(2));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        BusNames=new ArrayList<>();
        BusNames.add(BusesNames.get(0));
        Path.SetBusNames(BusNames);
        Path.SetDistance(1.4f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(2));
        Path.SetEndStation(Stations.get(3));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        BusNames=new ArrayList<>();
        BusNames.add(BusesNames.get(0));
        Path.SetBusNames(BusNames);
        Path.SetDistance(1.1f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(0));
        Path.SetEndStation(Stations.get(5));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        Path.SetDistance(1.2f);
        Paths.add(Path);

        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(1));
        Path.SetEndStation(Stations.get(4));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        BusNames=new ArrayList<>();
        BusNames.add(BusesNames.get(1));
        Path.SetBusNames(BusNames);
        Path.SetDistance(1.2f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(4));
        Path.SetEndStation(Stations.get(3));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        BusNames=new ArrayList<>();
        BusNames.add(BusesNames.get(1));
        Path.SetBusNames(BusNames);
        Path.SetDistance(1.4f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(5));
        Path.SetEndStation(Stations.get(2));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        Path.SetDistance(1.7f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(2));
        Path.SetEndStation(Stations.get(6));
        Path.SetTransportEnabled(true);
        Path.setBusSpeed(30);
        Path.setTaxiSpeed(50);
        Path.SetDistance(0.3f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(6));
        Path.SetEndStation(Stations.get(8));
        Path.SetTransportEnabled(false);
        Path.SetDistance(0.2f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(7));
        Path.SetEndStation(Stations.get(8));
        Path.SetTransportEnabled(false);
        Path.SetDistance(0.4f);
        Paths.add(Path);
        
        Path=new Path();//0Hamak 1Zahera 2BabM 3Fah 4JameAlhasan 5Betara 6Soyika 7Kaber  8Zokak
        Path.SetStartStation(Stations.get(3));
        Path.SetEndStation(Stations.get(7));
        Path.SetTransportEnabled(false);
        Path.SetDistance(0.3f);
        Paths.add(Path);

    }
    static public void SetCostTime(){
        cost=1;
    }
    static public void SetCostMoney(){
        cost=2;
    }
    static public void SetCostHealth(){
        cost=3;
    }
    static public void SetCostAll(){
        cost=4;
    }
    static public void CalcWorstTime(){
        //Calc Worst Time
        cost=-1;
        State S=new State();
        Logic L= new Logic();
        L.A_Star(S);
        WorstTime=-L.FinishStateCost;
    }

}
