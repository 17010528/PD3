package com.example.pd3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class delete extends AppCompatActivity {

    ArrayList<deleteDetails> deleteDetails;
    EditText etId;
    TextView tvEvents;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteDetails.add(new deleteDetails(1,"fuck u ", "motherfucker"));
        etId = findViewById(R.id.etTitle);
        tvEvents = findViewById(R.id.tvEvents);


        for (int i = 0; i < deleteDetails.size(); i++) {
            id = Integer.parseInt(etId.getText().toString());
            if (id == deleteDetails.get(i).getId()) {

                String description = deleteDetails.get(i).getDescription();
                String title = deleteDetails.get(i).getTitle();
                String id2 = Integer.toString(deleteDetails.get(i).getId());
                tvEvents.setText(id2 +description +  title);


            }

        }

    }
}
