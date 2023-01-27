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
public abstract class Transport {
    protected float MoneyCost;
    protected float HealthCost;
    
    public abstract void SetMoneyCost(float MoneyCost);
    public abstract float GetMoneyCost();
    
    public abstract void SetHealthCost(float HealthCost);
    public abstract float GetHealthCost();
        
}
