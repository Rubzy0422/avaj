package com.wtc.avaj;


import com.wtc.avaj.Aircrafts.Flyable;

import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable)
    {
        if (observers.indexOf(flyable) > -1)
        {
            observers.remove(flyable);
             System.out.println("Tower says:\t\t Deregistered flyable");
        }
        else 
             System.out.println("[WARNING]\t\t Could Could NOT Unregister Flyable!");
    }
    

    protected void conditionsChanged()
    {
        for (int i = 0; i < this.observers.size(); i++) {
            this.observers.get(i).updateConditions();
        }
    }
}