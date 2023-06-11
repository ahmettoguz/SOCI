package com.soci.soci.Model;

import java.util.ArrayList;

public class Weather {
    ArrayList<String> wxPhraseLongDay;
    ArrayList<Integer> temperatureMax;
    ArrayList<Integer> temperatureMin;


    @Override
    public String toString() {
        return "Weather\n" +
                "wxPhraseLongDay=" + wxPhraseLongDay + "\n" +
                ", temperatureMax=" + temperatureMax + "\n" +
                ", temperatureMin=" + temperatureMin + "\n" +
                '}';
    }

    public Weather(ArrayList<String> wxPhraseLongDay, ArrayList<Integer> temperatureMax, ArrayList<Integer> temperatureMin) {
        this.wxPhraseLongDay = wxPhraseLongDay;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
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
}
