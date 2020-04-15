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


    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public JSONObject[] allHotels(@RequestBody RankingRequest req) throws IOException {


        Hotels[] hotels = handler.receiverequest(req);

        Model model = new Model();
        JSONObject predictions = model.predictions(hotels);

        Predictions[] preds = new Predictions[predictions.size()];
        ArrayList<String> keys = new ArrayList<>(predictions.keySet());

        PriorityQueue<Predictions> pq = new PriorityQueue<>();

        for(int i=0 ; i<preds.length ; i++){
            preds[i] = new Predictions(keys.get(i), (Double) predictions.get(keys.get(i)));
            pq.add(preds[i]);
        }

        int j=0;
        JSONObject[] rankedpreds = new JSONObject[preds.length];
        while(pq.size()>0){
            rankedpreds[j] = new JSONObject();
            rankedpreds[j].put(pq.peek().hotelid, pq.peek().predval);
            Predictions removed = pq.remove();
            System.out.println(removed);
            j++;
        }
        System.out.println(rankedpreds);
        return rankedpreds;
    }

}
