package com.example.pd3;

import android.app.Activity;
import android.app.AlarmManager;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class userinput extends AppCompatActivity {


    DatePickerDialog dpd;
    int day = 0;
    int month = 0;
    int year = 0;
    int hour = 0;
    int minute = 0;

    int userSelectedHour = 0;
    int userSelectedMinute = 0;
    int userSelectedDay = 0;
    int userSelectedMonth = 0;
    int userSelectedYear = 0;

    int reqCode = 12345;


    String title = "";
    String description = "";
    String date = "";
    String time = "";
    EditText ETdescription, ETtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinput);

        setTitle("Add Events");

        Button buttonDate = findViewById(R.id.buttonDate);
        Button buttonTime = findViewById(R.id.buttonTime);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);


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
                        userSelectedDay = dayOfMonth;
                        userSelectedHour = month;
                        userSelectedYear = year;
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
                 hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                 minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(userinput.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        userSelectedHour = selectedHour;
                        userSelectedMinute = selectedMinute;

                        if(selectedHour<10){
                            if(selectedMinute<10){
                                showTime.setText("Time selected-0"+selectedHour + ":0" + selectedMinute);
                                time = "0"+selectedHour + ":0" + selectedMinute;
                            }else{
                                showTime.setText("Time selected-0"+selectedHour + ":"+selectedMinute);
                                time = "0"+selectedHour + ":" + selectedMinute;


                            }
                        }else{
                            if(selectedMinute<10){
                                showTime.setText("Time selected-"+selectedHour + ":0" + selectedMinute);
                                time = selectedHour + ":0" + selectedMinute;

                            }else{
                                showTime.setText("Time selected-"+selectedHour +":"+ selectedMinute);
                                time = selectedHour + ":" + selectedMinute;


                            }
                        }

                    }
                }, hour, minute, true);//Yes 24 hour time
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
                DBHelper dbh = new DBHelper(userinput.this);
                Log.e("Zeno :" , title + description + date+ time);
                dbh.insertDetails(title, description, date, time);
                dbh.close();



                hour = hour*60 ;
                day = userSelectedDay - day ;

                int reminderTime = (userSelectedHour*60 + userSelectedMinute) - (hour + minute);
                int reminderDate = day * 1440;
                int reminder = reminderTime + reminderDate;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE,reminder);

                Intent intent = new Intent(userinput.this, NotificationReceiver.class);
                String[] name = {title , description};
                intent.putExtra("name", name);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(userinput.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                if (day<1) {
                    Toast.makeText(userinput.this, "Event in " + reminderTime + "minute(s)",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(userinput.this, "Event in " + day + "day(s)"+reminderTime + " minute(s)",
                            Toast.LENGTH_SHORT).show();
                }

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
