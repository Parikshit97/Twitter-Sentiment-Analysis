package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;
import sun.awt.XSettings;
import sun.tools.tree.Vset;

public class Hotels {

    @Id
    private String hotel_id;
    private long hotel_type;
    private long ratingCount;
    private long user_abp;
    private long realised_bookings;
    private long total_bookings;
    private long total_details;
    private long total_impressions;
    private long recomm_score;

    public Hotels(String hotel_id, long hotel_type, long ratingCount, long user_abp, long realised_bookings, long total_bookings, long total_details, long total_impressions, long recomm_score){
       this.hotel_id = hotel_id;
       this.hotel_type = hotel_type;
       this.ratingCount = ratingCount;
       this.user_abp = user_abp;
       this.realised_bookings = realised_bookings;
       this.total_bookings = total_bookings;
       this.total_details = total_details;
       this.total_impressions = total_impressions;
       this.recomm_score = recomm_score;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public long getUser_abp() {
        return user_abp;
    }

    public long getTotal_bookings() {
        return total_bookings;
    }

    public long getRecomm_score() {
        return recomm_score;
    }

    public long getRealised_bookings() {
        return realised_bookings;
    }

    public long getRatingCount() {
        return ratingCount;
    }

    public long getHotel_type() {
        return hotel_type;
    }

    public long getTotal_details() {
        return total_details;
    }

    public long getTotal_impressions() {
        return total_impressions;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public void setUser_abp(long user_abp) {
        this.user_abp = user_abp;
    }

    public void setTotal_bookings(long total_bookings) {
        this.total_bookings = total_bookings;
    }

    public void setRecomm_score(long recomm_score) {
        this.recomm_score = recomm_score;
    }

    public void setRealised_bookings(long realised_bookings) {
        this.realised_bookings = realised_bookings;
    }

    public void setRatingCount(long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setHotel_type(long hotel_type) {
        this.hotel_type = hotel_type;
    }

    public void setTotal_details(long total_details) {
        this.total_details = total_details;
    }

    public void setTotal_impressions(long total_impressions) {
        this.total_impressions = total_impressions;
    }

    @Override
    public String toString() {
        return "hotel_id: "+this.hotel_id+
                "hotel_type: "+this.hotel_type+
                "ratingCount: "+this.ratingCount+
                "user_abp: "+this.ratingCount+
                "realised_bookings: "+this.realised_bookings+
                "total_bookings: "+this.total_bookings+
                "total_details: "+this.total_details+
                "total_impressions: "+this.total_impressions+
                "recomm_score: "+this.recomm_score;
    }
}
