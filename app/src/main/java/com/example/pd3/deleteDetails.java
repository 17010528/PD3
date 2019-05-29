package com.example.pd3;

import java.io.Serializable;

public class deleteDetails implements Serializable {
    private int id;
    private String date;
    private String time;

    public deleteDetails(int id , String date , String time) {
        this.id = id;
        this.date = date;
        this.time = time;

    }


    public String getDate() {
        return date;
    }

    public String getTime(){
        return time;
    }

    public int getId(){
        return id;
    }

}
