package com.wtc.avaj.Aircrafts;

import com.wtc.avaj.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
            String prefex = "Baloon#" + this.name + "(" + super.id + "):\t\t";
            switch (weatherTower.getWeather(this.coordinates)) {
            case "RAIN": {
                this.coordinates.mutateHeight(-5);
                System.out.println(prefex + "Ahhh .... Rain , I hate Rain!");
                break;
            }
            case "FOG": {
                this.coordinates.mutateHeight(-3);
                System.out.println(prefex + "I can't see anything in this fog!");
                break;
            }
            case "SUN": {
               this.coordinates.mutateLongitude(2);
               this.coordinates.mutateHeight(4);
               System.out.println(prefex + "a Great Day For leaving home.");
                break;
            }
            case "SNOW" : {
               this.coordinates.mutateHeight(-15);
               System.out.println(prefex + "I might have frostbite ... EVERYWHERE!");
                break;
            }
        }
        if (this.coordinates.getHeight() <= 0)
        {
            this.weatherTower.unregister(this);
            System.out.println("Tower Feedback\t\t" + prefex + " Landing at " + this.coordinates.toString());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: \t\tBaloon#" + name + " (" + super.id + ") registered to weather tower.");
    
    }
}
