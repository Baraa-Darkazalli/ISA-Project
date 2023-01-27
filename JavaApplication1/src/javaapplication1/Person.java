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
public class Person {
    private float Health;
    private float Money;

    public Person()
    {
        Health=Game.PersonHealth;
        Money=Game.PersonMoney;
    }
    
    //Set and Get
    public void SetHealth(float Health){this.Health=Health;}
    public float GetHealth(){return this.Health;}
    public void SetMoney(float Money){this.Money=Money;}
    public float GetMoney(){return this.Money;}
    
    
}
