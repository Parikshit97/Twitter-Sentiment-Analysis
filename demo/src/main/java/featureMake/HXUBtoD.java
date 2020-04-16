package featureMake;

import com.example.demo.ElasticConfig;
import com.example.demo.Hotels;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HXUBtoD implements Feature {
    @Override
    public HashMap<String, HashMap<String, Double>> calculate(ArrayList<Hotels> hotels, String userprofileid) throws IOException {
        HashMap<String, HashMap<String, Double>> outer = new HashMap<>();

        ElasticConfig elasticConfig = new ElasticConfig();
        JestClient jestClient = elasticConfig.jestClient();

        try {

            JestResult jestResult = jestClient.execute(new Get.Builder("db_ranking", "AmIwfnEBmqnhJvawM6FZ").build());
            HXU hxu = jestResult.getSourceAsObject(HXU.class);

            for(Hotels hotel : hotels) {
                HashMap<String, Double> inner = new HashMap<>();

                if(hxu.getHxu_map()==null || hxu.getHxu_map().get(hotel.getHotel_id())==null || hxu.getHxu_map().get(hotel.getHotel_id())==null || hxu.getHxu_map().get(hotel.getHotel_id()).get(userprofileid)==null){
                    continue;
                }

                long details_bookings = hxu.getHxu_map().get(hotel.getHotel_id()).get(userprofileid).getBookings();
                long details_details = hxu.getHxu_map().get(hotel.getHotel_id()).get(userprofileid).getDetails();
                if(details_details == 0){
                    continue;
                }
                double hotel_user_btod = details_bookings / (1.0 * details_details);

//                System.out.println(hotel_user_btod);

                inner.put("hotel_user_btod", hotel_user_btod);
                outer.put(hotel.getHotel_id(), inner);
            }

            ;
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }

        return outer;
    }

    @Override
    public String toString() {
        return "hotel_user_btod";
    }
}
