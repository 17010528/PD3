package com.example.pd3;

import android.content.Intent;
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
    ListView lv;
    ArrayAdapter aa1;
    Button btnDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        setTitle("Delete Events");

        etId = findViewById(R.id.etTitle);

        btnDelete = findViewById(R.id.btnDelete);

        lv = this.findViewById(R.id.lvDelete);

        final DBHelper db = new DBHelper(delete.this);
        deleteDetails = db.getDelete();
        aa1 = new deleteAdapter(this, R.layout.rowdelete, deleteDetails);
        lv.setAdapter(aa1);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                String msg = db.getDeleteDetails(id);
                Toast.makeText(delete.this, msg,
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(delete.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
