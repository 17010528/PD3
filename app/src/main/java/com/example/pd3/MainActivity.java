package com.example.pd3;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_1 = 1;
    private final static int REQUEST_CODE_2 = 2;
    final String[] items = {"Edit","Delete","View"};
    ListView lv;
    ArrayAdapter aa;
    ArrayList<details> details;
    ArrayList<allDetails>allDetails;
    AlertDialog levelDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("View Events");

         DBHelper db = new DBHelper(MainActivity.this);

        FloatingActionButton btnAdd = findViewById(R.id.fabAdd);
        lv = this.findViewById(R.id.lv);

        details = db.getSomeDetails();

        aa = new detailsadapter(this, R.layout.row, details);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, userinput.class);
                String[] target = {"", "", "", "", "" , "0"};
                intent.putExtra("data", target);
                startActivityForResult(intent, REQUEST_CODE_1);
            }
        });



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, final long id) {
                // TODO Auto-generated method stub



                final DBHelper db = new DBHelper(MainActivity.this);

                // Creating and Building the Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select Choice");

                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        if(item == 2) {
                            DBHelper db = new DBHelper(MainActivity.this);
                            allDetails = db.getAllDetails(pos);

                            Intent i = new Intent(MainActivity.this,
                                    userinput.class);
                            String[] target = {allDetails.get(pos).getTitle(), allDetails.get(pos).getDescription(), allDetails.get(pos).getDate(), allDetails.get(pos).getTime(), items[2] , Integer.toString(pos)};
                            i.putExtra("data", target);
                            startActivityForResult(i, REQUEST_CODE_2);

                        }else if(item == 1){

                            DBHelper db = new DBHelper(MainActivity.this);
                            allDetails = db.getAllDetails(pos);
                            int id = allDetails.get(pos).getId();


                            String msg = db.getDeleteDetails(id);
                            Toast.makeText(MainActivity.this, msg,
                                    Toast.LENGTH_SHORT).show();

                            details.clear();
                            details = db.getSomeDetails();

                            aa = new detailsadapter(MainActivity.this, R.layout.row, details);
                            lv.setAdapter(aa);

                        }else{
                            DBHelper db = new DBHelper(MainActivity.this);
                            allDetails = db.getAllDetails(pos);

                            Intent i = new Intent(MainActivity.this,
                                    userinput.class);
                            String[] target = {allDetails.get(pos).getTitle(), allDetails.get(pos).getDescription(), allDetails.get(pos).getDate(), allDetails.get(pos).getTime(), items[0], Integer.toString(pos)};
                            i.putExtra("data", target);
                            startActivityForResult(i, REQUEST_CODE_2);
                        }

                         levelDialog.dismiss();
                    }
                });

                builder.setNeutralButton("Cancel", null);



                levelDialog = builder.create();
                levelDialog.show();

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        DBHelper db = new DBHelper(MainActivity.this);

        details.clear();
        details = db.getSomeDetails();

        aa = new detailsadapter(this, R.layout.row, details);
        lv.setAdapter(aa);
    }

}
