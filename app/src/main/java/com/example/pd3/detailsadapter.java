package com.example.pd3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class detailsadapter extends ArrayAdapter<details> {
    private ArrayList<details> details;
    private Context context;
    private TextView tvTitle , tvDatenTime;



    public detailsadapter(Context context, int resource, ArrayList<details> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        details = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvTitle= rowView.findViewById(R.id.textViewTitle);
        // Get the ImageView object
        tvDatenTime = rowView.findViewById(R.id.textViewDatenTime);

        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        details currentDetails = details.get(position);
        tvTitle.setText(currentDetails.getTitle());
        tvDatenTime.setText(currentDetails.getTime()+"\n" +currentDetails.getDate());

        return rowView;
    }
}