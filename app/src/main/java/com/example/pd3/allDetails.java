package com.example.pd3;

import java.io.Serializable;

public class allDetails implements Serializable {
    private String title;
    private String time;
    private String date;
    private int id;
    private String description;



    public allDetails(int id, String title, String description, String date , String time) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    public void setAllDetails(String title, String description , String date , String time){
        this.title = title;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }




}
