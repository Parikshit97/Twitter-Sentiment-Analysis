package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

public interface Feature {
    public HashMap<String, HashMap<String,Double>> calculate(ArrayList<Hotels> hotels);
}
