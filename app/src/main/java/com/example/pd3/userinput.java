package com.example.pd3;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class userinput extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    DatePickerDialog dpd;
    int day = 0;
    int month = 0;
    int year = 0;
    int userSelectedHour = 0;
    int userSelectedMinute = 0;
    String title = "";
    String description = "";
    String date = "";
    String time = "";
    String dayOfTime = "";
    EditText ETdescription, ETtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinput);


        Button buttonDate = findViewById(R.id.buttonDate);
        Button buttonTime = findViewById(R.id.buttonTime);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

//        notificationManager = NotificationManagerCompat.from(this);


        final TextView showTime = findViewById(R.id.textViewTime);
        final TextView showDate = findViewById(R.id.textViewDate);

        ETdescription = findViewById(R.id.editTextDescription);
        ETtitle = findViewById(R.id.editTextTitle);


        Intent intent = getIntent();
        final String[] target = intent.getStringArrayExtra("data");
        title = target[0];
        description = target[1];
        date = target[3];
        time = target[2];
        if(!title.equals("")&& !date.equals("") && !time.equals("") && !description.equals("")) {

            ETtitle.setText(title);
            ETtitle.setEnabled(false);
            ETtitle.setTypeface(null , Typeface.BOLD_ITALIC);

            buttonDate.setEnabled(false);
            buttonTime.setEnabled(false);
            buttonSubmit.setVisibility(View.INVISIBLE);


            ETdescription.setText(description);
            ETdescription.setEnabled(false);
            ETdescription.setTypeface(null , Typeface.BOLD_ITALIC);

            showTime.setText("Time selected-" + time);
            showTime.setTypeface(null , Typeface.BOLD_ITALIC);

            showDate.setText("Date selected-" + date);
            showDate.setTypeface(null , Typeface.BOLD_ITALIC);

        }

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
                        month = month + 1;
                        showDate.setText("Date selected-" + dayOfMonth + "/" + month + "/" + year);
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
                        if (selectedHour > 12) {
                            selectedHour = selectedHour - 12;
                            dayOfTime = "pm";
                            if (selectedMinute < 10) {

                                showTime.setText("Time selected-" + selectedHour + ":" + "0" + selectedMinute + "pm");

                            } else {
                                showTime.setText("Time selected-" + selectedHour + ":" + selectedMinute + "pm");

                            }
                        } else {

                            dayOfTime = "am";
                            if (selectedMinute < 10) {

                                showTime.setText("Time selected-" + +selectedHour + ":0" + selectedMinute + "am");
                            } else {
                                showTime.setText("Time selected-" + selectedHour + ":" + selectedMinute + "am");

                            }
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
                title = ETtitle.getText().toString();
                description = ETdescription.getText().toString();
                date = day + "/" + month + "/" + year;
                time = "";
                if (userSelectedHour > 12) {
                    userSelectedHour = userSelectedHour - 12;
                    dayOfTime = "pm";
                    if (userSelectedMinute < 10) {

                        time = userSelectedHour + ":0" + userSelectedMinute + "pm";

                    } else {
                        time = userSelectedHour + ":" + userSelectedMinute + "pm";

                    }
                } else {

                    dayOfTime = "am";
                    if (userSelectedMinute < 10) {

                        time = "0" + userSelectedHour + ":0" + userSelectedMinute + "am";

                    } else {
                        time = "0" + userSelectedHour + ":" + userSelectedMinute + "am";

                    }
                }
//                if (!target[0].equals("")) {
//                    String userInformation[] = {title, description, date, time};
//                    Intent intent = new Intent();
//                    intent.putExtra("userInformation", userInformation);
//
//                    setResult(RESULT_CANCELED, intent);
//                    finish();

//                } if {
                    String userInformation[] = {title, description, date, time , target[4]};
                    Intent intent = new Intent();
                    intent.putExtra("userInformation", userInformation);

                    setResult(RESULT_OK, intent);
                    finish();
//                }
            }
        });
    }

//    public void sendChannel1(View v) {
//        String title = ETtitle.getText().toString();
//        String description = ETdescription.getText().toString();
//        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_one)
//                .setContentTitle(title)
//                .setContentText(description)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_ALARM)
//                .build();
//
//
//        notificationManager.notify(1, notification);
//    }

}
