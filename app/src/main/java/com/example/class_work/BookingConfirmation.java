package com.example.class_work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookingConfirmation extends Fragment {
    View view;
    TextView confirmation;
    String guestNm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.confirmation,container,  false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmation = view.findViewById(R.id.confirmation_text_view);
        guestNm = getArguments().getString("name of guest");
        confirmation.setText("Thank You "+guestNm+ ".Your booking is confirmed");

    }



}
