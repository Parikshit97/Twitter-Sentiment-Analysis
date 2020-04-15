package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelCtr implements Feature{
    @Override
    public HashMap<String, HashMap<String, Double>> calculate(ArrayList<Hotels> hotels) {
        HashMap<String, HashMap<String, Double>> outer = new HashMap<>();
        HashMap<String, Double> inner = new HashMap<>();

        for(Hotels hotel : hotels){
            double hotelctr = hotel.getTotal_details() / (1.0 * hotel.getTotal_impressions() );
            inner.put("hotel_ctr", hotelctr);
            outer.put(hotel.getHotel_id(), inner);
        }

        return outer;
    }

    @Override
    public String toString() {
        return "hotel_ctr";
    }

}
