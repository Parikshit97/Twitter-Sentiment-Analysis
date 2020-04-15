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

@RestController
public class Controller {

    @Autowired
    Handler handler;


    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public void allHotels(@RequestBody RankingRequest req) throws IOException {


        Hotels[] hotels = handler.receiverequest(req);

        Model model = new Model();
        model.predictions(hotels);
    }

}
