/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.lang.reflect.Field;
import java.time.LocalTime;
import java.lang.Class;





/**
 *
 * @author Baraa Darkazalli
 */
public class Main {

    public static LocalTime DiffTimes(LocalTime Time1,LocalTime Time2){
        Time2=Time2.minusHours(Time1.getHour());
        Time2=Time2.minusMinutes(Time1.getMinute());
        Time2=Time2.minusSeconds(Time1.getSecond());
        Time2=Time2.minusNanos(Time1.getNano());
        return Time2;
    }
    
    public static void main(String[] args)  {
        Game.InitilizeGame();

        Game.SetCostAll();

        State S=new State();
        Logic L=new Logic();

        L.A_Star(S);
        L.print();
    }
    
}
