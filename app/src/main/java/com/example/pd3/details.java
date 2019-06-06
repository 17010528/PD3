package com.example.pd3;

import java.io.Serializable;

public class details implements Serializable {
    private String title;
    private String time;
    private String date;



    public details(String title, String date, String time) {

        this.title = title;
        this.time = time;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }


    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }



    @Override
    public String toString(){
        return title + " " + time + " " + date + " ";
    }

}
