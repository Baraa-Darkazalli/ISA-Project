/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MSI GAMING
 */
public class UserInputs {
    private Scanner scanner = new Scanner(System.in);
    private String Choice="1";
    State S=new State();
    Logic L=new Logic();
    
    public UserInputs(){
        Welcome();
        MainMenu();
    }
    
    public void Welcome(){
        System.out.println("************************");
        System.out.println("Welcome to MLOKHIA Game:");
        System.out.println("************************");
        System.out.println("");
    }
    
    public void MainMenu(){
        System.out.println("********");
        System.out.println("MAIN MENU:");
        System.out.println("********");
        System.out.println("Chosse one from these options:");
        System.out.println("1- Enter Buses Names");
        System.out.println("2- Enter Stations");
        System.out.println("3- Enter Paths");
        System.out.println("4- Change Settings");
        System.out.println("5- START GAME");
        System.out.println("6- Exit Game");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
                case "1":
                    EnterBusesNames();
                    break;
                case "2":
                    EnterStations();
                    break;
                case "3":
                    EnterPaths();
                    break;
                case "4":
                    Settings();
                case "5":
                    System.out.println("");
                    L.A_Star(S);
                    L.print();
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Wrong Value.. Please Try Again");
                    System.out.println("");
                    MainMenu();
                    break;
            }
        
    }
    
    public void EnterBusesNames(){
        System.out.println("********");
        System.out.println("ENTER BUSES NAMES:");
        System.out.println("********");
        System.out.println("Chosse one from these options:");
        System.out.println("1- Add Bus Name");
        System.out.println("2- Show All Buses Names");
        System.out.println("3- Back");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
            case "1":
                AddBusesNames();
                break;
            case "2":
                ShowAllBusesNames();
                break;
            case "3":
                MainMenu();
                break;
            default:
                System.out.println("Wrong Value.. Please Try Again");
                System.out.println("");
                EnterBusesNames();
                break;
        }
    }
    
    public void ShowAllBusesNames(){
        System.out.println("********");
        System.out.println("BUSES NAMES:");
        System.out.println("********");
        for(String BusName : Game.BusesNames)
        {
            System.out.println(BusName);
        }
        System.out.println("");
        System.out.print("Press Any Key To Back: ");
        scanner.next();
        EnterBusesNames();
    }
    
    public void AddBusesNames(){
        String next = "y";
        while("y".equals(next)){
            System.out.print("Enter Bus Name: ");
            String BusName=scanner.next();
            Game.BusesNames.add(BusName);
            System.out.print("Add Another Bus Name? (y,n) ");
            next = scanner.next();
        }
        MainMenu();
    }
    
    public void EnterStations(){
        System.out.println("********");
        System.out.println("ENTER STATIONS:");
        System.out.println("********");
        System.out.println("Chosse one from these options:");
        System.out.println("1- Add Station");
        System.out.println("2- Show All Stations");
        System.out.println("3- Back");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
            case "1":
                AddStation();
                break;
            case "2":
                ShowAllStations();
                break;
            case "3":
                MainMenu();
                break;
            default:
                System.out.println("Wrong Value.. Please Try Again");
                System.out.println("");
                EnterStations();
                break;
        }
    }
    
    public void ShowAllStations(){
        System.out.println("********");
        System.out.println("STATIONS:");
        System.out.println("********");
        if(Game.Stations.isEmpty())System.out.println("Stations Are Empty!");
        for(Station Station : Game.Stations)
        {
            System.out.println(Station.GetStationName()+": ");
            Station.GetTransportArrivalTime().forEach((key,value)->{
                System.out.println("Arrival Time For "+key+" is: "+value);
            });
            System.out.println("");
        }
        System.out.print("Press Any Key To Back: ");
        scanner.next();
        EnterStations();
        
    }
    
    public void AddStation(){
        System.out.println("********");
        System.out.println("ADD STATION:");
        System.out.println("********");
        System.out.print("Enter Staion Name: ");
        String StationName = scanner.next();
        Station Station = new Station();
        Station.SetStationName(StationName);
        int BusArrival=0,TaxiArrival=0;
        boolean error=false;
        boolean HaveBus=YesOrNo("Is Station Have Arrival Time For Buses?");
        if(HaveBus){
            do{
                System.out.print("Arrival Time For Bus By Minutes Is: ");
                try{
                    error=false;
                    BusArrival = Integer.parseInt(scanner.next());
                }catch(Exception e){
                    System.out.println("Wrong Value .. Please Try Again");
                    error=true;
                }
            }while(error);
        }
        boolean HaveTaxi=YesOrNo("Is Station Have Arrival Time For Taxis?");
        
        if(HaveTaxi){
            System.out.print("Arrival Time For Taxi By Minutes Is: ");
            TaxiArrival = scanner.nextInt();
        }
        if(HaveTaxi||HaveBus){
        HashMap<TransportType,Integer> TransportArrivalTime=new HashMap<>();
            if(HaveBus) TransportArrivalTime.put(TransportType.Bus,BusArrival);
            if(HaveTaxi) TransportArrivalTime.put(TransportType.Taxi,TaxiArrival);
            Station.SetTransportArrivalTime(TransportArrivalTime);
        }
        
        Game.Stations.add(Station);
        FinishAndStart(Station);
        System.out.println("");
        System.out.println("Station "+Station.GetStationName()+" Added Successfully");
        System.out.println("");
        EnterStations();
    }
    
    public boolean YesOrNo(String String){
        System.out.println(String);
        System.out.println("1- Yes");
        System.out.println("2- No");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
            case "1":
                return true;
            case "2":
                return false;
            default:
                System.out.println("Wrong Value.. Please Try Again");
                System.out.println("");
                return YesOrNo(String);
        }
    }
    
    public void FinishAndStart(Station Station){
        boolean IsStart=YesOrNo("Is "+Station.GetStationName()+" Start Station?");
        if(IsStart)Game.FirstStation=Station;
        boolean IsFinish=YesOrNo("Is "+Station.GetStationName()+" Finish Station?");
        if(IsFinish)Game.FinishStation=Station;
    }
    
    public void EnterPaths(){
        System.out.println("********");
        System.out.println("ENTER PATHS:");
        System.out.println("********");
        System.out.println("Chosse one from these options:");
        System.out.println("1- Add Path");
        System.out.println("2- Show All Paths");
        System.out.println("3- Back");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
            case "1":
                AddPath();
                return;
            case "2":
                ShowAllPaths();
                return;
            case "3":
                MainMenu();
                return;
            default:
                System.out.println("Wrong Value.. Please Try Again");
                System.out.println("");
                EnterPaths();
        }
    }
    
    public void AddPath(){
        System.out.println("********");
        System.out.println("ADD PATH:");
        System.out.println("********");
        System.out.println("Chosse Start Station For This Path:");
        PrintStatoinsNames();
        System.out.print("Your Choice: ");
        int StartIndex = scanner.nextInt();
        Path Path =new Path();
        Path.SetStartStation(Game.Stations.get(StartIndex));
        System.out.println("Chosse End Station For This Path:");
        PrintStatoinsNames();
        System.out.print("Your Choice: ");
        int EndIndex = scanner.nextInt();
        Path.SetEndStation(Game.Stations.get(EndIndex));
        boolean Enable=YesOrNo("Is Tranport Enabled In This Path?");
        if(Enable){
            Path.SetTransportEnabled(true);
            List<String> PathBuses=new ArrayList<>();
            boolean next=true;
            while(next){
                System.out.println("Chose Buses Names In This Bath:");
                PrintBusesNames();
                System.out.print("Your Choice: ");
                int BusIndex=scanner.nextInt();
                PathBuses.add(Game.BusesNames.get(BusIndex));
                next=YesOrNo("Is There Ara Another Bus? ");
            }
            Path.SetBusNames(PathBuses);
        }else{
            Path.SetTransportEnabled(false);
        }
        System.out.print("Enter Distance Between "+Path.GetStartStation().GetStationName()+" and "+Path.GetEndStation().GetStationName()+" By KM : ");
        float Dis=scanner.nextFloat();
        Path.SetDistance(Dis);
        Game.Paths.add(Path);
        System.out.println("");
        System.out.println("Path Added Successfully");
        System.out.println("");
        EnterPaths();
    }
    
    public void PrintStatoinsNames()
   {
        if(Game.Stations.isEmpty()){
            System.out.println("");
            System.out.println("Stations Are Empty");
            System.out.println("");
            MainMenu();
            return;
        }
        int i=0;
        for(Station Station:Game.Stations)
        {
            System.out.println(i+"- "+Station.GetStationName());
            i++;
        }
    }
    public void PrintBusesNames(){
        if(Game.BusesNames.isEmpty()){
            System.out.println("");
            System.out.println("Buses Names Are Empty");
            System.out.println("");
            MainMenu();
            return;
        }
        int i=0;
        for(String BusName:Game.BusesNames)
        {
            System.out.println(i+"- "+BusName);
            i++;
        }
    }
    public void ShowAllPaths(){
        System.out.println("********");
        System.out.println("PATHS:");
        System.out.println("********");
        if(Game.Paths.isEmpty())System.out.println("Paths Are Empty!");
        for(Path Path : Game.Paths)
        {
            System.out.println("From "+Path.GetStartStation().GetStationName()+" to "+Path.GetEndStation().GetStationName());
            System.out.println("Distance: "+Path.GetDistance());
            System.out.print("Bus Names: ");
            for(String BusName:Path.GetBusNames()){
                System.out.print(BusName+ " ");
            }
            System.out.println("");
            System.out.println("");
        }
        System.out.print("Press Any Key To Back: ");
        scanner.next();
        EnterPaths();
    }
    public void Settings(){
        System.out.println("********");
        System.out.println("SETTING:");
        System.out.println("********");
        System.out.println("Chosse one from these options:");
        System.out.println("1- Change Bus Settings");
        System.out.println("2- Change Taxi Settings");
        System.out.println("3- Change Walk Settings");
        System.out.println("4- Change Person Settings");
        System.out.println("5- Back");
        System.out.print("Your Choice: ");
        Choice = scanner.next();
        switch(Choice){
            case "1":
                ChangeBusSettings();
                return;
            case "2":
                ChangeTaxiSettings();
                return;
            case "3":
                ChangeWalkSettings();
                return;
            case "4":
                ChangePersonSettings();
                return;
            case "5":
                return;
            default:
                System.out.println("Wrong Value.. Please Try Again");
                System.out.println("");
                Settings();
        }  
    }
    public void ChangeBusSettings(){
        System.out.println("********");
        System.out.println("BUS SETTINGS:");
        System.out.println("********");
//        System.out.println("Default Bus Speed is: "+Game.BusSpeed);
//        boolean yes=YesOrNo("Do You Want To Change It? ");
//        if(yes){
//            System.out.print("A New Bus Speed Value Is: ");
//            BusSpeed=scanner.nextFloat();
//        }
        System.out.println("Default Bus Money Cost Is: "+ Game.BusMoneyCost);
        boolean yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Bus Money Cost value Is");
            Game.BusMoneyCost=scanner.nextFloat();
        }
        System.out.println("Default Bus Health Cost Is: "+ Game.BusHealthCost);
        yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Bus Health Cost value Is");
            Game.BusMoneyCost=scanner.nextFloat();
        }
        MainMenu();
    }
    public void ChangeTaxiSettings(){
        System.out.println("********");
        System.out.println("TAXI SETTINGS:");
        System.out.println("********");
//        System.out.println("Default Taxi Speed is: "+TaxiSpeed);
//        boolean yes=YesOrNo("Do You Want To Change It? ");
//        if(yes){
//            System.out.print("A New Taxi Speed Value Is: ");
//            TaxiSpeed=scanner.nextFloat();
//        }
        System.out.println("Default Taxi Money Cost Is: "+ Game.TaxiMoneyCost);
        boolean yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Taxi Money Cost value Is");
            Game.TaxiMoneyCost=scanner.nextFloat();
        }
        System.out.println("Default Taxi Health Cost Is: "+ Game.TaxiHealthCost);
        yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Taxi Health Cost value Is");
            Game.TaxiMoneyCost=scanner.nextFloat();
        }
        MainMenu();
    }
    
    public void ChangeWalkSettings(){
        System.out.println("********");
        System.out.println("WALK SETTINGS:");
        System.out.println("********");
        System.out.println("Default Walk Speed is: "+Game.WalkSpeed);
        boolean yes=YesOrNo("Do You Want To Change It? ");
        if(yes){
            System.out.print("A New Walk Speed Value Is: ");
            Game.WalkSpeed=scanner.nextFloat();
        }
        System.out.println("Default Walk Money Cost Is: "+ Game.WalkMoneyCost);
        yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Walk Money Cost value Is");
            Game.WalkMoneyCost=scanner.nextFloat();
        }
        System.out.println("Default Walk Health Cost Is: "+ Game.WalkHealthCost);
        yes=YesOrNo("Do You Want To Change It?");
        if(yes){
            System.out.println("A New Walk Health Cost value Is");
            Game.WalkMoneyCost=scanner.nextFloat();
        }
        MainMenu();
    }
    public void ChangePersonSettings(){
        System.out.println("********");
        System.out.println("PERSON SETTINGS:");
        System.out.println("********");
        System.out.println("Default Person Money Is: "+Game.PersonMoney);
        boolean yes=YesOrNo("Do You Want To Change It? ");
        if(yes){
            System.out.print("A New Person Money Value Is: ");
            Game.PersonMoney=scanner.nextFloat();
        }
        MainMenu();
    }
}
