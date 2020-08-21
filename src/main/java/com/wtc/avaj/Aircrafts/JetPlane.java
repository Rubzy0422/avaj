package com.wtc.avaj.Aircrafts;


import com.wtc.avaj.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
            super(name, coordinates);
    }

        @Override
        public void updateConditions()
        {
            String prefex = "JetPlane#" + this.name + "(" + super.id + "):\t\t";
            switch (weatherTower.getWeather(this.coordinates))
            {
                case "RAIN": {
                   this.coordinates.mutateLatitude(5);
                    System.out.println(prefex + "Raining?  ... What? I Love Rain!");
                   break;
                }
                case "FOG": {
                    this.coordinates.mutateLatitude(1);
                     System.out.println(prefex + "Fog ... Ha no Problem!");
                    break;
                }
                case "SUN": {
                   this.coordinates.mutateLatitude(10);
                   this.coordinates.mutateHeight(2);
                    System.out.println(prefex + "Sunny day nice day to fly away");
                    break;
                }
                case "SNOW" : {
                   this.coordinates.mutateHeight(-7);
                    System.out.println(prefex + "my jet fuel has no problem with snow mwha haha ha ha");
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
             System.out.println("Tower says: \t\tJetPlane#" + name + " (" + super.id + ") registered to weather tower.");
        }

}