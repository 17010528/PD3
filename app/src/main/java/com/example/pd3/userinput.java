package com.example.pd3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class userinput extends AppCompatActivity {


    DatePickerDialog dpd;
    int day = 0;
    int month = 0;
    int year = 0;
    int userSelectedHour = 0;
    int userSelectedMinute = 0;
    String dayOfTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinput);


        Button buttonDate = findViewById(R.id.buttonDate);
        Button buttonTime = findViewById(R.id.buttonTime);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        final TextView showTime = findViewById(R.id.textViewTime);
        final TextView showDate = findViewById(R.id.textViewDate);

        final EditText ETdescription = findViewById(R.id.editTextDescription);
        final EditText ETtitle = findViewById(R.id.editTextTitle);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                 day = c.get(Calendar.DAY_OF_MONTH);
                 month = c.get(Calendar.MONTH);
                 year = c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(userinput.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        showDate.setText("Date selected - " +dayOfMonth + "/" + month + "/" + year);
                    }
                }, day, month, year);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        });

        buttonTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(userinput.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        userSelectedHour = selectedHour;
                        userSelectedMinute = selectedMinute;
                        if(selectedHour > 12){
                            selectedHour = selectedHour - 12;
                            dayOfTime = "pm";
                            showTime.setText( "Time selected - " + selectedHour + ":" + selectedMinute + "pm");
                        }else {
                            dayOfTime = "am";
                            showTime.setText("Time selected : " + selectedHour + ":" + selectedMinute + "am");
                        }
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               String title = ETtitle.getText().toString();
               String description = ETdescription.getText().toString();
               String date = day + "/" + month + "/" + year;
               String time = userSelectedHour + " : "+ userSelectedMinute + dayOfTime;
               String userInformation [] = {title, description , date, time};
                Intent intent = new Intent();
                intent.putExtra("userInformation", userInformation);
                setResult(RESULT_OK , intent);
                finish();
            }
        });
    }
}
