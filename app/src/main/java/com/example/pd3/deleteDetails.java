package com.example.pd3;

import java.io.Serializable;

public class deleteDetails implements Serializable {
    private int id;
    private String title;
    private String description;

    public deleteDetails(int id ,String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId(){
        return id;
    }

}
