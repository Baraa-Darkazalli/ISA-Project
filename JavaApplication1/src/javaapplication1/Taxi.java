/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author MSI GAMING
 */
public class Taxi extends Transport{
    
    public Taxi()
    {
        this.MoneyCost=Game.TaxiMoneyCost;
        this.HealthCost=Game.TaxiHealthCost;
    }

    @Override
    public void SetMoneyCost(float MoneyCost) {this.MoneyCost=MoneyCost;}

    @Override
    public float GetMoneyCost() {return this.MoneyCost;}

    @Override
    public void SetHealthCost(float HealthCost) {this.HealthCost=HealthCost;}

    @Override
    public float GetHealthCost() {return this.HealthCost;} 

    
}
