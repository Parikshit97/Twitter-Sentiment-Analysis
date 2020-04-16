package com.example.demo;

import featureMake.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class Handler {

    @Autowired
    DataGather dataGather;

    public Pair receiverequest(RankingRequest req) throws IOException {

        String userprofileid = req.getUserprofileid();
        System.out.println(userprofileid);

        Hotels hotels[] = dataGather.gatherthroughes(req); //hoteldetails
        ArrayList<Hotels> hotel_list = new ArrayList<>(Arrays.asList(hotels));

        Feature hotelbr = new HotelBR();
        Feature hotelbtod = new HotelBtoD();
        Feature hotelctr = new HotelCtr();
        Feature hotelrbr = new HotelRBR();
        Feature hxubr = new HXUBR();
        Feature hxuctr = new HXUCTR();
        Feature hxubtod = new HXUBtoD();

        ArrayList<Feature> ifeatures = new ArrayList<>();
        ifeatures.add(hotelbr);
        ifeatures.add(hotelbtod);
        ifeatures.add(hotelctr);
        ifeatures.add(hotelrbr);
        ifeatures.add(hxubr);
        ifeatures.add(hxuctr);
        ifeatures.add(hxubtod);

        HashMap<String, HashMap<String, Double>> calculated_features = new HashMap<>();

        for(Hotels hotel : hotel_list){
            calculated_features.put(hotel.getHotel_id(), new HashMap<>());
        }

        for(Feature ifeature : ifeatures){

            System.out.println(ifeature);

            HashMap<String, HashMap<String, Double>>inner = ifeature.calculate(hotel_list, userprofileid);

            ArrayList<String>hotelids = new ArrayList<>(inner.keySet());

            for(String hotelid : hotelids){

                HashMap<String, Double> innermap = inner.get(hotelid);
                calculated_features.get(hotelid).put(String.valueOf(ifeature), innermap.get(String.valueOf(ifeature)));

            }
        }

        System.out.println(calculated_features);


        return new Pair(hotels, calculated_features);
    }
}
