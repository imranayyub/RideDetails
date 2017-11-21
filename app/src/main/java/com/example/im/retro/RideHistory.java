package com.example.im.retro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Im on 21-11-2017.
 */

public class RideHistory {

    @SerializedName("booking_id")
    private String booking_id;
    @SerializedName("image")
    private String image;
    @SerializedName("driver_name")
    private String driver_name;
//    @SerializedName("ride_times")
//    private String ride_times;

    public RideHistory(String booking_id, String image, String driver_name)
    {
        this.booking_id=booking_id;
        this.image=image;
        this.driver_name=driver_name;
    }
    public String getBooking_id() {
        return booking_id;
    }
    public String getImage() {
        return image;
    }
    public String getDriver_name() {
        return driver_name;
    }
    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

}