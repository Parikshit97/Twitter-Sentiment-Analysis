package com.example.demo;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Component
public class RedisCronJob {

    @Scheduled(fixedRate = 50000)
    public void fixedRateSch() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);

        ElasticConfig elasticConfig = new ElasticConfig();
        JestClient jestClient = elasticConfig.jestClient();

        ArrayList<String>document_ids= new ArrayList<>();
        Collections.addAll(document_ids, new String[]{"p_MHVXEBAc85LkT9nyJJ", "qPMIVXEBAc85LkT9VSKr", "qfMJVXEBAc85LkT9VCLk"});

        for(String document_id : document_ids) {
            JestResult jestResult = jestClient.execute(new Get.Builder("db_ranking", document_id).build());

            System.out.println(jestResult.getSourceAsObject(Hotels.class));
        }

        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server successful");
        System.out.println("Server is running: "+jedis.ping());


        Hotels[] hotels = new Hotels[document_ids.size()];
        int j=0;

        HashMap<String, HashMap<String, Object>> jmap = new HashMap<>();

        for(String document_id : document_ids) {

          JestResult jestResult = jestClient.execute(new Get.Builder("db_ranking", document_id).build());


            String hotel_idc = jestResult.getSourceAsObject(Hotels.class).getHotel_id();
            long hotel_type = jestResult.getSourceAsObject(Hotels.class).getHotel_type();
            long ratingCount = jestResult.getSourceAsObject(Hotels.class).getRatingCount();
            long user_abp = jestResult.getSourceAsObject(Hotels.class).getUser_abp();
            long realised_bookings = jestResult.getSourceAsObject(Hotels.class).getRealised_bookings();
            long total_bookings = jestResult.getSourceAsObject(Hotels.class).getTotal_bookings();
            long total_details = jestResult.getSourceAsObject(Hotels.class).getTotal_details();
            long total_impressions = jestResult.getSourceAsObject(Hotels.class).getTotal_impressions();
            long recomm_score = jestResult.getSourceAsObject(Hotels.class).getRecomm_score();

            HashMap<String, Object> inner = new HashMap<>();
            inner.put("hotel_id", (String)hotel_idc);
            inner.put("hotel_type", (long)hotel_type);
            inner.put("ratingCount", (long)ratingCount);
            inner.put("user_abp", (long)user_abp);
            inner.put("realised_bookings", (long)realised_bookings);
            inner.put("total_bookings", (long)total_bookings);
            inner.put("total_details", (long)total_details);
            inner.put("total_impressions", (long)total_impressions);
            inner.put("recomm_score", (long)recomm_score);
            jmap.put(document_id, inner);

            jedis.hset(document_id, "hotel_id", hotel_idc);
            jedis.hset(document_id, "hotel_type", String.valueOf(hotel_type));
            jedis.hset(document_id, "ratingCount", String.valueOf(ratingCount));
            jedis.hset(document_id, "user_abp", String.valueOf(user_abp));
            jedis.hset(document_id, "realised_bookings", String.valueOf(realised_bookings));
            jedis.hset(document_id, "total_bookings", String.valueOf(total_bookings));
            jedis.hset(document_id, "total_details", String.valueOf(total_details));
            jedis.hset(document_id, "total_impressions", String.valueOf(total_impressions));
            jedis.hset(document_id, "recomm_score", String.valueOf(recomm_score));

            hotels[j] = new Hotels(hotel_idc, hotel_type, ratingCount, user_abp, realised_bookings, total_bookings, total_details, total_impressions, recomm_score);

            j++;
        }


        for(String document_id : document_ids){
            HashMap<String, String> hget = (HashMap<String, String>) jedis.hgetAll(document_id);
            System.out.println(hget);

        }


    }
}
