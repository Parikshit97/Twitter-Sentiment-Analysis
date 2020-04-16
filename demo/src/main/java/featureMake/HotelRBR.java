package featureMake;

import com.example.demo.Hotels;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelRBR implements Feature {
    @Override
    public HashMap<String, HashMap<String, Double>> calculate(ArrayList<Hotels> hotels, String userprofileid) {
        HashMap<String, HashMap<String, Double>> outer = new HashMap<>();

        for(Hotels hotel : hotels){
            HashMap<String, Double> inner = new HashMap<>();
            double hotelrbr = hotel.getRealised_bookings() / (1.0 * hotel.getTotal_bookings());
            inner.put("hotel_rbr", hotelrbr);
            outer.put(hotel.getHotel_id(), inner);
        }

        return outer;
    }

    @Override
    public String toString() {
        return "hotel_rbr";
    }

}
