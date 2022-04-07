package com.example.class_work;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.widget.Toast;

public class HotelGuestDetailsFragment extends Fragment {

    View view;
    private static ArrayList<GuestListData> data;
    GuestListData guestlistdata;
    String hotelName,checkIn,checkOut,guestName;
    Integer roomNumber,guestNumber;
    BookingData bookingData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_guest_detail_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
        Button submit = view.findViewById(R.id.button_submit_guest);

        hotelName = getArguments().getString("hotel name");
        Float hotelPrice = getArguments().getFloat("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        guestNumber = getArguments().getInt("number of guests");
        checkIn = getArguments().getString("check-in date");
        checkOut = getArguments().getString("check-out date");
        guestName = getArguments().getString("guest name");

        roomNumber = Math.round(guestNumber/2);

        hotelRecapTextView.setText(guestName+",You have selected " + hotelName + ". The cost will be $ " + roomNumber * hotelPrice + " and availability is " + hotelAvailability);
        hotelRecapTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        hotelRecapTextView.setBackgroundColor(Color.CYAN);

        data = new ArrayList<>();

        for (int i = 1; i <= guestNumber; i++) {
            guestlistdata = new GuestListData("Guest" + i);
            data.add(guestlistdata);
        }
        setupRecyclerView ();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking();
            }
        });
    }

    private void setupRecyclerView () {
        RecyclerView recyclerView = view.findViewById(R.id.guest_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(), data);
        recyclerView.setAdapter(guestListAdapter);
    }

    private void booking() {
        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        Api.getClient().makeReservations(hotelName,checkIn,checkOut,roomNumber,guestNumber,guestName, new Callback<BookingData>()
        {
            @Override
            public void success(BookingData bookingdata, Response response)
            {
                // in this method we will get the response from API
                bookingData = bookingdata;
                //bookingData.get(0);


                //obj.add();
                //System.out.println("bookingdata is"+bookingData);
                // display the message getting from web api
                Toast.makeText(getActivity(), bookingData.getMessage(), Toast.LENGTH_LONG).show();

                //Toast.makeText(getActivity(), (CharSequence) bookingData.get(0),Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
