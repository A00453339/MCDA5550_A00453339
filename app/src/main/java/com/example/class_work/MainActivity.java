package com.example.class_work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_search_layout);
        setContentView(R.layout.activity_main);
        //Begin the transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Replace the contents of the container with new fragment
        fragmentTransaction.replace(R.id.frame_layout, new HotelSearchFragment());
        //Complete the transaction
        fragmentTransaction.commit();
    }
}