package com.example.demo;

import netscape.security.UserTarget;
import sun.management.HotspotRuntimeMBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankingRequest {

    public static class hotel{

        double fprice;
        double sprice;
        double distance;

        public double getDistance() {
            return distance;
        }

        public double getFprice() {
            return fprice;
        }

        public double getSprice() {
            return sprice;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public void setFprice(double fprice) {
            this.fprice = fprice;
        }

        public void setSprice(double sprice) {
            this.sprice = sprice;
        }
    }

    Map<String, hotel> hotelmap;
    String userprofileid;
    String cityid;
    String sortby;


    public String getCityid() {
        return cityid;
    }

    public String getSortby() {
        return sortby;
    }

    public String getUserprofileid() {
        return userprofileid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public void setUserprofileid(String userprofileid) {
        this.userprofileid = userprofileid;
    }

    public Map<String, hotel> getHotelmap() {
        return hotelmap;
    }

    public void setHotelmap(Map<String, hotel> hotelmap) {
        this.hotelmap = hotelmap;
    }
}
