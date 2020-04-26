package com.example.demo;

public class Predictions implements Comparable<Predictions> {

    String hotelid;
    double predval;

    public Predictions(String hotelid, double predval) {
        this.hotelid = hotelid;
        this.predval = predval;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public double getPredval() {
        return predval;
    }

    public void setPredval(double predval) {
        this.predval = predval;
    }

    @Override
    public int compareTo(Predictions o) {
        if(o.predval - this.predval > 0){
            return 1;
        }else if(this.predval - o.predval >0){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Predictions{" +
                "hotelid='" + hotelid + '\'' +
                ", predval=" + predval +
                '}';
    }
}
