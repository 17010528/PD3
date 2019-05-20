package com.example.pd3;

public class details {
    private String title;
    private String description;
    private String time;
    private String date;

    public details(String title, String description, String time, String date) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public String getTitle() {
        return title;
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
