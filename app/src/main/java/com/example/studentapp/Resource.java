package com.example.studentapp;

public class Resource {
    private String title;
    private String link;

    public Resource(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
