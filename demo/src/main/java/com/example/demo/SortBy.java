package com.example.demo;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

@Component
public class SortBy {

    public JSONObject[] popularity(JSONObject predictions){
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
            rankedpreds[j].put(pq.peek().getHotelid(), pq.peek().getPredval());
            Predictions removed = pq.remove();
            System.out.println(removed);
            j++;
        }
        System.out.println(rankedpreds);
        return rankedpreds;
    }

    public JSONObject[] distance(RankingRequest req, Hotels[] hotels){
        ArrayList<String>hotelids = new ArrayList<>();
        PriorityQueue<Predictions> pq = new PriorityQueue<>();
        Predictions[] predictions = new Predictions[req.getHotelmap().size()];
        for(int i=0; i<hotels.length; i++){
            hotelids.add(hotels[i].getHotel_id());
            predictions[i] = new Predictions(hotels[i].getHotel_id(), (Double)req.getHotelmap().get(hotels[i].getHotel_id()).getDistance());
            pq.add(predictions[i]);
        }

        int j=0;
        JSONObject[] rankedpreds = new JSONObject[predictions.length];
        while(pq.size()>0){
            rankedpreds[j] = new JSONObject();
            rankedpreds[j].put(pq.peek().getHotelid(), pq.peek().getPredval());
            Predictions removed = pq.remove();
            System.out.println(removed);
            j++;
        }
        Collections.reverse(Arrays.asList(rankedpreds));

        return rankedpreds;
    }

    public JSONObject[] price(RankingRequest req, Hotels[] hotels){
        ArrayList<String>hotelids = new ArrayList<>();
        PriorityQueue<Predictions> pq = new PriorityQueue<>();
        Predictions[] predictions = new Predictions[req.getHotelmap().size()];
        for(int i=0; i<hotels.length; i++){
            hotelids.add(hotels[i].getHotel_id());
            predictions[i] = new Predictions(hotels[i].getHotel_id(), (Double)req.getHotelmap().get(hotels[i].getHotel_id()).getSprice());
            pq.add(predictions[i]);
        }

        int j=0;
        JSONObject[] rankedpreds = new JSONObject[predictions.length];
        while(pq.size()>0){
            rankedpreds[j] = new JSONObject();
            rankedpreds[j].put(pq.peek().getHotelid(), pq.peek().getPredval());
            Predictions removed = pq.remove();
            System.out.println(removed);
            j++;
        }
        Collections.reverse(Arrays.asList(rankedpreds));

        return rankedpreds;
    }
}
