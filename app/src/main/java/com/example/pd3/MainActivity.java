package com.example.pd3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_1 = 1;
    private final static int REQUEST_CODE_2 = 2;
    private Intent dataIntent;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<details> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAdd = findViewById(R.id.fabAdd);
        lv = this.findViewById(R.id.lv);


        details = new ArrayList<details>();

        aa = new detailsadapter(this, R.layout.row, details);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, userinput.class);
                String[] target = {"", "", "", "", ""};
                intent.putExtra("data", target);
                startActivityForResult(intent, REQUEST_CODE_1);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                details data = details.get(position);

                Intent i = new Intent(MainActivity.this,
                        userinput.class);

                String[] target = {data.getTitle(), data.getDescription(), data.getDate(), data.getTime(), Integer.toString(position)};
                i.putExtra("data", target);
                startActivityForResult(i, REQUEST_CODE_2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        // The returned result data is identified by requestCode.
        // The request code is specified in startActivityForResult(intent, REQUEST_CODE_1); method.
        switch (requestCode) {
            // This request code is set by startActivityForResult(intent, REQUEST_CODE_1) method.
            case REQUEST_CODE_1:
                if (resultCode == RESULT_OK) {
                    String information[] = dataIntent.getStringArrayExtra("userInformation");
//                    tvTest.setText(information[0] + information[1] + information[2] + information[3]);

                    details.add(new details(information[0], information[1], information[2], information[3]));
                    aa.notifyDataSetChanged();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.delete){
            Intent i = new Intent(MainActivity.this,
                    delete.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
