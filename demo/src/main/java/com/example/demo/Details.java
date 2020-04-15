package com.example.demo;

public class Details {

    long impressions;
    long details;
    long bookings;

    public Details(long impressions, long details, long bookings) {
        this.impressions = impressions;
        this.details = details;
        this.bookings = bookings;
    }

    public long getBookings() {
        return bookings;
    }

    public long getDetails() {
        return details;
    }

    public long getImpressions() {
        return impressions;
    }

    public void setBookings(long bookings) {
        this.bookings = bookings;
    }

    public void setDetails(long details) {
        this.details = details;
    }

    public void setImpressions(long impressions) {
        this.impressions = impressions;
    }

    @Override
    public String toString() {
        return "Details{" +
                "impressions=" + impressions +
                ", details=" + details +
                ", bookings=" + bookings +
                '}';
    }
}
