package com.soci.soci.Model;

import java.util.ArrayList;

public class Weather {
    ArrayList<String> wxPhraseLongDay;
    ArrayList<Integer> temperatureMax;
    ArrayList<Integer> temperatureMin;
    ArrayList<Double> rain24Hour;

    @Override
    public String toString() {
        return "Weather\n" +
                "wxPhraseLongDay=" + wxPhraseLongDay + "\n" +
                ", temperatureMax=" + temperatureMax + "\n" +
                ", temperatureMin=" + temperatureMin + "\n" +
                ", rain24Hour=" + rain24Hour + "\n" +
                '}';
    }

    public Weather(ArrayList<String> wxPhraseLongDay, ArrayList<Integer> temperatureMax, ArrayList<Integer> temperatureMin, ArrayList<Double> rain24Hour) {
        this.wxPhraseLongDay = wxPhraseLongDay;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.rain24Hour = rain24Hour;
    }

    public ArrayList<String> getWxPhraseLongDay() {
        return wxPhraseLongDay;
    }

    public void setWxPhraseLongDay(ArrayList<String> wxPhraseLongDay) {
        this.wxPhraseLongDay = wxPhraseLongDay;
    }

    public ArrayList<Integer> getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(ArrayList<Integer> temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public ArrayList<Integer> getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(ArrayList<Integer> temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public ArrayList<Double> getRain24Hour() {
        return rain24Hour;
    }

    public void setRain24Hour(ArrayList<Double> rain24Hour) {
        this.rain24Hour = rain24Hour;
    }
}
