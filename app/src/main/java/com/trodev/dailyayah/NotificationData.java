package com.trodev.dailyayah;

public class NotificationData {


    private String title, body, date, key;


    public NotificationData() {
    }

    public NotificationData(String title, String body, String date, String key) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.key = key;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
