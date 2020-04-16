package com.example.demo;

import java.util.HashMap;

public class Pair {
    Hotels[] hotels;
    HashMap<String, HashMap<String, Double>> calculated_features;

    public Pair(Hotels[] hotels, HashMap<String, HashMap<String, Double>> calculated_features) {
        this.hotels = hotels;
        this.calculated_features = calculated_features;
    }

    public HashMap<String, HashMap<String, Double>> getCalculated_features() {
        return calculated_features;
    }

    public Hotels[] getHotels() {
        return hotels;
    }

    public void setHotels(Hotels[] hotels) {
        this.hotels = hotels;
    }

    public void setCalculated_features(HashMap<String, HashMap<String, Double>> calculated_features) {
        this.calculated_features = calculated_features;
    }
}
