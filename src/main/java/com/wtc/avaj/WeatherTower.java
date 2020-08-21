package com.wtc.avaj;

import com.wtc.avaj.Aircrafts.Coordinates;

public class WeatherTower extends Tower {

    private static WeatherProvider weatherProvider = WeatherProvider.getProvider();

    public String getWeather(Coordinates coordinates)
    {
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void ChangeWeather()
    {
        super.conditionsChanged();
    }

    public void UpdateWeather() {
        this.ChangeWeather();
    }
}