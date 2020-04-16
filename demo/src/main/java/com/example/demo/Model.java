package com.example.demo;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class Model {

    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    public JSONObject predictions(Hotels[] hotels, HashMap<String, HashMap<String, Double>> calculated_features, RankingRequest req){
        JSONObject outer = new JSONObject();
        JSONObject[] inner = new JSONObject[hotels.length];
        int inner_counter=0;

        for(int counter=0; counter<hotels.length; counter++){

            Hotels hotel = hotels[counter];

            System.out.println("hotel_"+counter+" "+hotel.getHotel_type());

            inner[inner_counter] = new JSONObject();
            inner[inner_counter].put("f1", hotel.getHotel_type());
            inner[inner_counter].put("f2", calculated_features.get(hotel.getHotel_id()).get("hotel_user_br"));
            inner[inner_counter].put("f3", calculated_features.get(hotel.getHotel_id()).get("hotel_user_ctr"));
            inner[inner_counter].put("f4", calculated_features.get(hotel.getHotel_id()).get("hotel_user_btod"));
            inner[inner_counter].put("f5", req.getHotelmap().get(hotel.getHotel_id()).getDistance());
            inner[inner_counter].put("f6", req.getHotelmap().get(hotel.getHotel_id()).getFprice());
            inner[inner_counter].put("f7", 32); //discount_per
            inner[inner_counter].put("f8", hotel.getRatingCount());
            inner[inner_counter].put("f9", hotel.getUser_abp());
            inner[inner_counter].put("f10", 38); //avg_hotel_rr
            inner[inner_counter].put("f11", calculated_features.get(hotel.getHotel_id()).get("hotel_user_ctr")); //hotel_placeid_ctr
            inner[inner_counter].put("f12", calculated_features.get(hotel.getHotel_id()).get("hotel_user_br")); //hotel_placeid_br
            inner[inner_counter].put("f13", calculated_features.get(hotel.getHotel_id()).get("hotel_user_ctr")); //user_cat_ctr
            inner[inner_counter].put("f14", calculated_features.get(hotel.getHotel_id()).get("hotel_user_br")); //user_cat_br
            inner[inner_counter].put("f15", calculated_features.get(hotel.getHotel_id()).get("hotel_user_btod")); //user_cat_dtob
            inner[inner_counter].put("f16", hotel.getRecomm_score());
            inner[inner_counter].put("f17", 52); //iscitysearch
            inner[inner_counter].put("f18", calculated_features.get(hotel.getHotel_id()).get("hotel_user_btod")); //hotel_placeid_dtob

            outer.put(hotel.getHotel_id(), inner[inner_counter]);

            System.out.println(inner[inner_counter]);

            inner_counter ++;

        }

        System.out.println("This is string "+outer.toJSONString());
        String url = "http://127.0.0.1:5000";


        RestTemplate restTemplate = new RestTemplate();
        JSONObject json = restTemplate.postForEntity(url+"/getprediction", outer, JSONObject.class).getBody();
        System.out.println(json.toJSONString());
        return json;
    }
}
