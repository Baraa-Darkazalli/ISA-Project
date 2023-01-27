/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;

/**
 *
 * @author MSI GAMING
 */
public class Logic {
    
    private List<State>VisitedStates=new ArrayList<>();
    private Stack<State>Solution=new Stack<>();
    public LocalTime StartRunTime;
    public LocalTime StopRunTime;
    public float FinishStateCost;
    public String Algorithm;

    public Logic(){
        VisitedStates=new ArrayList<>();
        Solution=new Stack<>();
    }
    
    private boolean isVisited(State State){
        for(State VState : VisitedStates)
        {
            if(VState.IsEqual(State))return true;
        }
        return false;
    }
    private void GetSolution(State State){
        if(State.GetFatherState()!=null)
        {
            Solution.push(State);
            GetSolution(State.GetFatherState());
        }
    }
    static public void printState(State State){
        if(State.GetFatherState()==null)
        {
            System.out.println("First Station: "+State.GetStation().GetStationName());
            System.out.println("TotalMoney: "+State.GetPerson().GetMoney());
            System.out.println("TotalHealth: "+State.GetPerson().GetHealth());
            System.out.println("TotalTime: "+State.GetTime());
            System.out.println("");
            return;
        }
        System.out.print(State.GetFatherState().GetStation().GetStationName());
        System.out.print(" to ");
        System.out.println(State.GetStation().GetStationName());
        System.out.print(State.GetTransport().getKey());
        System.out.println(" "+State.GetTransport().getValue()); 
        System.out.print("MoneyCost: ");
        System.out.println(-(State.GetFatherState().GetPerson().GetMoney() - State.GetPerson().GetMoney()));
        System.out.print("HealthCost: ");
        System.out.println(-(State.GetFatherState().GetPerson().GetHealth() - State.GetPerson().GetHealth()));
        System.out.print("TimeCost: ");
        System.out.println(-(State.GetFatherState().GetTime() - State.GetTime()));

        System.out.println("TotalMoney: "+State.GetPerson().GetMoney());
        System.out.println("TotalHealth: "+State.GetPerson().GetHealth());
        System.out.println("TotalTime: "+State.GetTime());
        System.out.println("");
   }
   public void print(){
        printDetails();
        PrintSolution();
   }
    private void printDetails(){
       System.out.println("Algorithm: "+Algorithm);
       System.out.println("Visited Count: "+this.VisitedStates.size());
       System.out.println("Steps Count: "+this.Solution.size());
       System.out.println("Run Time: "+Main.DiffTimes(StartRunTime,StopRunTime));
        System.out.println("Default Money Value: "+Game.PersonMoney);
       System.out.println("Cost By: "+((Game.cost==1)?"Time":(Game.cost==2)?"Money":(Game.cost==3)?"Health":"All"));
       System.out.println("*************************************");
       System.out.println("");
       System.out.println("");
       System.out.println("Solution Path:");
       System.out.println("");
   }
    
    private void PrintSolution(){
        if(Solution.size()==0){
            System.out.println("Solution Stack is empty");
            return;
        }
        printState(Solution.pop());
        if(Solution.size()!=0)PrintSolution();
    }
    
    public void BFS(State State) {
        StartRunTime=LocalTime.now();
        Queue <State> q= new LinkedList<>();
        q.add(State);
        while(q.size()!=0)
        {
            State v=new State();
            v=q.poll();
            if(!isVisited(v))
            {
                VisitedStates.add(v);
                if(v.IsFinish())
                {
                    FinishStateCost=v.GetCost();
                    StopRunTime=LocalTime.now();
                    GetSolution(v);
                    Algorithm="BFS";
                    return;
                    
                }
                for(State next : v.GetNextStates())
                {
                    q.add(next);
                }
            }
            
        }
    }
    
    public void UCS(State State) {
        StartRunTime=LocalTime.now();
        PriorityQueue<Pair<Float, State>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.add(new Pair<>(State.GetCost(),State));while(pq.size()!=0)
        {
            State v=new State();
            v=pq.remove().getValue();
            if(!isVisited(v))
            {
                VisitedStates.add(v);
                if(v.IsFinish())
                {
                    FinishStateCost=v.GetCost();
                    StopRunTime=LocalTime.now();
                    GetSolution(v);
                    Algorithm="UCS";
                    return;
                    
                }
                for(State next : v.GetNextStates())
                {
                    pq.add(new Pair<>(next.GetCost(),next));
                }
            }
            
        }
    }
    
    public void A_Star(State State){
        StartRunTime=LocalTime.now();
        PriorityQueue<Pair<Float, State>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.add(new Pair<>(State.GetCost()+State.GetHeuristic(),State));
        
        while(pq.size()!=0)
        {
            State v=new State();
            v=pq.remove().getValue();
            if(!isVisited(v))
            {
                VisitedStates.add(v);
                if(v.IsFinish())
                {
                    FinishStateCost=v.GetCost();
                    StopRunTime=LocalTime.now();
                    GetSolution(v);
                    Algorithm="A*";
                    return;
                    
                }
                for(State next : v.GetNextStates())
                {
                    pq.add(new Pair<>(next.GetCost()+next.GetHeuristic(),next));
                }
            }
            
        }
    }
}
