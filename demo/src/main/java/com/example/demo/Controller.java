package com.example.demo;

import model.Model;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class Controller {

    @Autowired
    Handler handler;

    @Autowired
    SortBy sortBy;

    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public JSONObject[] allHotels(@RequestBody RankingRequest req) throws IOException {


        Pair pair = handler.receiverequest(req);

        Hotels[] hotels = pair.getHotels();
        HashMap<String, HashMap<String, Double>> calculated_features = pair.getCalculated_features();

        if(req.getSortby().equals("popularity")) {
            Model model = new Model();
            JSONObject predictions = model.predictions(hotels, calculated_features, req);
            return sortBy.popularity(predictions);
        }else if(req.getSortby().equals("distance")){
            return sortBy.distance(req, hotels);
        }else if (req.getSortby().equals("price")){
            return  sortBy.price(req, hotels);
        }else{
            return null;
        }


    }

}
