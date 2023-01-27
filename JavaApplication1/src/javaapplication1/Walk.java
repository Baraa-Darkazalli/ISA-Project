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
public class Walk extends Transport {
    
    private float Speed;

    public Walk()
    {
        this.Speed=Game.WalkSpeed;
        this.MoneyCost=Game.WalkMoneyCost;
        this.HealthCost=Game.WalkHealthCost;
    }
        
    public void SetSpeed(float Speed) {this.Speed=Speed;}

    public float GetSpeed() {return this.Speed;}

    @Override
    public void SetMoneyCost(float MoneyCost) {this.MoneyCost=MoneyCost;}

    @Override
    public float GetMoneyCost() {return this.MoneyCost;}

    @Override
    public void SetHealthCost(float HealthCost) {this.HealthCost=HealthCost;}

    @Override
    public float GetHealthCost() {return this.HealthCost;}
}
