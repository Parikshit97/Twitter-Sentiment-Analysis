package com.example.demo;

import io.searchbox.client.JestClient;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

@RestController
public class Controller {

    @Autowired
    Handler handler;

    @Autowired
    SortBy sortBy;

    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public JSONObject[] allHotels(@RequestBody RankingRequest req) throws IOException {


        Pair pair = handler.receiverequest(req);

        Hotels[] hotels = pair.hotels;
        HashMap<String, HashMap<String, Double>> calculated_features = pair.calculated_features;

        if(req.sortby.equals("popularity")) {
            Model model = new Model();
            JSONObject predictions = model.predictions(hotels, calculated_features, req);
            return sortBy.popularity(predictions);
        }else if(req.sortby.equals("distance")){
            return sortBy.distance(req, hotels);
        }else if (req.sortby.equals("price")){
            return  sortBy.price(req, hotels);
        }else{
            return null;
        }


    }

}
