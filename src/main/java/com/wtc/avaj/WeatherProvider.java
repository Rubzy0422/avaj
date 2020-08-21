package com.wtc.avaj;

import com.wtc.avaj.Aircrafts.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    // Get Instance 
    public static WeatherProvider getProvider() 
    { 
       return weatherProvider;
    } 

    public String getCurrentWeather(Coordinates coordinates)
    {
        //        Custom weather generation algorythim to get Weather ...

        String tmp = "";
        if (coordinates.getHeight() > 50 && coordinates.getLatitude() > 20)
            tmp = weather[0];
        else if (coordinates.getLongitude() < 20 && coordinates.getHeight() > 10)
            tmp = weather[1];
        else if (coordinates.getHeight() >= 60 || coordinates.getLatitude() >= 60)
            tmp = weather[2];
        else 
            tmp = weather[3];
        
        return tmp;
    }
}
