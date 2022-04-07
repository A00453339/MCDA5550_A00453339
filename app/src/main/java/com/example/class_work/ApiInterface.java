package com.example.class_work;

import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/bookings/")
    public void makeReservations(
            @Field("hotel_name") String hotel_name,
            @Field("check_in_date") String check_in_date,
            @Field("check_out_date") String check_out_date,
            @Field("num_of_rooms") Integer num_of_rooms,
            @Field("num_of_guests") Integer num_of_guests,
            @Field("guest_name") String guest_name, Callback<BookingData> callback);

    // API's endpoints
    @GET("/hotel_list")
    public void getHotelsLists(Callback<List<HotelListData>> callback);

}
