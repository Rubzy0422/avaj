package com.wtc.avaj.Aircrafts;

import com.wtc.avaj.WeatherTower;

public interface Flyable
{
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);

}