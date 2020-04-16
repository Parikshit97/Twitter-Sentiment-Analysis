package com.example.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.awt.image.ImageWatched;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class DataGather {

    @Autowired
    ElasticConfig elasticConfig;

    public Hotels[] gatherthroughes(RankingRequest req) throws IOException {

        Hotels[] hotels = new Hotels[req.getHotelmap().size()];
        int j=0;

        String hotel_ids[] = new String[req.getHotelmap().size()];
        int i=0;
        ArrayList<String>hotelid_keys = new ArrayList<>(req.getHotelmap().keySet());
        for(String key : hotelid_keys){
            hotel_ids[i] = key;
            i++;
        }

        JestClient jestClient = elasticConfig.jestClient();

        String search = "{" +
                "  \"query\": {" +
                "    \"bool\": {" +
                "      \"must\": [" +
                "        { \"match\": { \"hotel_id\":   \"x\" }}" +
                "      ]" +
                "    }" +
                "  }" +
                "}";

        int iter=1;
        String prev = "";
        for(String hotel_id : hotel_ids) {


            if(iter==1) {
                search = search.replace("x", hotel_id);
                iter ++ ;
            }else{
                search = search.replace(prev, hotel_id);
            }


            SearchResult searchResult = jestClient.execute(new Search.Builder(search).build());

            String hotel_idc = searchResult.getSourceAsObject(Hotels.class).getHotel_id();
            long hotel_type = searchResult.getSourceAsObject(Hotels.class).getHotel_type();
            long ratingCount = searchResult.getSourceAsObject(Hotels.class).getRatingCount();
            long user_abp = searchResult.getSourceAsObject(Hotels.class).getUser_abp();
            long realised_bookings = searchResult.getSourceAsObject(Hotels.class).getRealised_bookings();
            long total_bookings = searchResult.getSourceAsObject(Hotels.class).getTotal_bookings();
            long total_details = searchResult.getSourceAsObject(Hotels.class).getTotal_details();
            long total_impressions = searchResult.getSourceAsObject(Hotels.class).getTotal_impressions();
            long recomm_score = searchResult.getSourceAsObject(Hotels.class).getRecomm_score();


            hotels[j] = new Hotels(hotel_idc, hotel_type, ratingCount, user_abp, realised_bookings, total_bookings, total_details, total_impressions, recomm_score);
            System.out.println(hotels[j]);

            j++;

            prev = hotel_id;
        }


    return hotels;

    }
}
