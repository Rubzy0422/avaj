package com.wtc.avaj.Aircrafts;


import com.wtc.avaj.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String prefex = "Helicopter#" + this.name + "(" + super.id + "):\t";
        switch (weatherTower.getWeather(this.coordinates))
        {
            case "RAIN": {
               this.coordinates.mutateLongitude(5);
                System.out.println(prefex + "Raining?  ... What? I Love Rain!");
               break;
            }
            case "FOG": {
                this.coordinates.mutateLongitude(1);
                 System.out.println(prefex + "Fog ... Ha no Problem!");
                break;
            }
            case "SUN": {
               this.coordinates.mutateLongitude(10);
               this.coordinates.mutateHeight(2);
                System.out.println(prefex + "I love the Sun reminds me of home.");
                break;
            }
            case "SNOW" : {
               this.coordinates.mutateHeight(-12);
                System.out.println(prefex + "my blades are too cold ");
                break;
            }
        }
        if (this.coordinates.getHeight() <= 0)
        {
             System.out.println("Tower says:\t\t" + prefex + " Deregisterd!");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
         System.out.println("Tower says: \t\tHelicopter#" + name + " (" + super.id + ") registered to weather tower.");
    }

}