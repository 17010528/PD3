package com.example.pd3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class deleteAdapter extends ArrayAdapter<deleteDetails> {
    private ArrayList<deleteDetails> deleteDetails;
    private Context context;
    private TextView tvID , tvDateAndTime;



    public deleteAdapter(Context context, int resource, ArrayList<deleteDetails> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        deleteDetails = objects;
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
        View rowView = inflater.inflate(R.layout.rowdelete, parent, false);

        // Get the TextView object
        tvID= rowView.findViewById(R.id.tvID);
        // Get the ImageView object
        tvDateAndTime = rowView.findViewById(R.id.tvDateAndTime);

        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        deleteDetails currentDelete = deleteDetails.get(position);
        tvID.setText("ID:"+Integer.toString(currentDelete.getId()));
        tvDateAndTime.setText("Time:"+currentDelete.getTime()+"\nDate:" +currentDelete.getDate());

        return rowView;
    }
}
