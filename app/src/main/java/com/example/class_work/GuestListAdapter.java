package com.example.class_work;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder>{

    private ArrayList<GuestListData> dataSet;
    private LayoutInflater layoutInflater;


    //Data gets passed in the constructor
    GuestListAdapter(Context context, ArrayList<GuestListData> guestListData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataSet = guestListData;
    }

    @NonNull
    @Override
    public GuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.guest_list_layout, parent, false);
        return new GuestListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapter.ViewHolder holder, int position) {
        TextView guest_text = holder.guestText;
        EditText guest_name = holder.guestName;
        RadioButton male = holder.male;
        RadioButton female = holder.female;
        guest_text.setText(dataSet.get(position).getGuest());
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView guestText;
        EditText guestName;
        RadioButton male,female;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestText = itemView.findViewById(R.id.text_view_guest);
            guestName = itemView.findViewById(R.id.editext_guest_name);
            male = itemView.findViewById(R.id.radio_male);
            female = itemView.findViewById(R.id.radio_female);
        }

    }
}