package com.example.class_work;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HotelSearchFragment extends Fragment {

    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView, searchTextConfirmationTextView;
    DatePicker checkInDatePicker,checkOutDatePicker;
    Button confirmSearch, search;
    EditText guestName,guestNum;
    String checkInDate, checkOutDate, numberOfGuests, guestNm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout,container,  false);
        return view;
            }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchTextConfirmationTextView = view.findViewById(R.id.confirm_search_text_view);
        titleTextView = view.findViewById(R.id.title_text_view);
        checkInDatePicker = view.findViewById(R.id.date_picker_check_in);
        checkOutDatePicker = view.findViewById(R.id.date_picker_check_out);
        confirmSearch = view.findViewById(R.id.button_submit);
        search = view.findViewById(R.id.button_search);
        guestName = view.findViewById(R.id.editext_guest_name);
        guestNum = view.findViewById(R.id.editext_guest_num);
        mainLayout=view.findViewById(R.id.main_constraint_layout);

        titleTextView.setText(R.string.welcome_text);

        confirmSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(checkInDatePicker);
                checkOutDate = getDateFromCalendar(checkOutDatePicker);
                //Get input of guests count
                numberOfGuests = guestNum.getText().toString();
                guestNm = guestName.getText().toString();
                searchTextConfirmationTextView.setText("Dear "+guestNm+", Your check in date is " + checkInDate + ", " +
                        "your checkout date is " + checkOutDate + ".The number of guests are " + numberOfGuests);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInDate=getDateFromCalendar(checkInDatePicker);
                checkOutDate=getDateFromCalendar(checkOutDatePicker);
                numberOfGuests=guestNum.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);
                bundle.putString("name of guest", guestNm);

                HotelListFragment hotelsListFragment = new HotelListFragment();
                hotelsListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private String getDateFromCalendar(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }


}
