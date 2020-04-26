package com.example.demo;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

public class Model {

    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    public JSONObject predictions(Hotels[] hotels){
        JSONObject outer = new JSONObject();
        JSONObject[] inner = new JSONObject[hotels.length];
        int inner_counter=0;

        for(int counter=0; counter<hotels.length; counter++){

            Hotels hotel = hotels[counter];

            System.out.println("hotel_"+counter+" "+hotel.getHotel_type());

            inner[inner_counter] = new JSONObject();
            inner[inner_counter].put("f1", hotel.getHotel_type());
            inner[inner_counter].put("f2", 22);
            inner[inner_counter].put("f3", 24);
            inner[inner_counter].put("f4", 26);
            inner[inner_counter].put("f5", 28);
            inner[inner_counter].put("f6", 30);
            inner[inner_counter].put("f7", 32);
            inner[inner_counter].put("f8", hotel.getRatingCount());
            inner[inner_counter].put("f9", hotel.getUser_abp());
            inner[inner_counter].put("f10", 38);
            inner[inner_counter].put("f11", 40);
            inner[inner_counter].put("f12", 42);
            inner[inner_counter].put("f13", 44);
            inner[inner_counter].put("f14", 46);
            inner[inner_counter].put("f15", 48);
            inner[inner_counter].put("f16", hotel.getRecomm_score());
            inner[inner_counter].put("f17", 52);
            inner[inner_counter].put("f18", 54);

            outer.put(hotel.getHotel_id(), inner[inner_counter]);

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
