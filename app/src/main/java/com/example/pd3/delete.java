package com.example.pd3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class delete extends AppCompatActivity {

    ArrayList<deleteDetails> deleteDetails;
    EditText etId;
    TextView tvEvents;
    Button btnCheck ,btnDelete;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etId = findViewById(R.id.etTitle);
        tvEvents = findViewById(R.id.tvEvents);
        btnCheck = findViewById(R.id.btnCheck);
        btnDelete = findViewById(R.id.btnDelete);


        final DBHelper db = new DBHelper(delete.this);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < deleteDetails.size(); i++) {


                        String description = deleteDetails.get(i).getDescription();
                        String title = deleteDetails.get(i).getTitle();
                        String id2 = Integer.toString(deleteDetails.get(i).getId());
                        tvEvents.setText("ID:"+id2+"\nTitle:"+title+"\nDescription"+description+"\n");


                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title =etId.getText().toString();
                String msg = db.getDeleteDetails(title);
                Toast.makeText(delete.this, msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
