package com.wtc.avaj.Aircrafts;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void mutateLongitude(int longitude) {
        this.longitude += longitude;
    }

    public void mutateLatitude(int latitude) {
        this.latitude += latitude;
    }

    public void mutateHeight(int height) {
        this.height += height;
        if (this.height >= 100) {
            this.height = 100;
        }
    }

    @Override
    public String toString() {
        return "(" + this.latitude + ", " + this.longitude + ", " + this.height + ")";
    }
}