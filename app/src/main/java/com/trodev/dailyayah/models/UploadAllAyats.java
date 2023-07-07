package com.trodev.dailyayah.models;

public class UploadAllAyats {

    private String headline , ayat, author, date , key;

    public UploadAllAyats() {
    }

    public UploadAllAyats(String headline, String ayat, String author, String date, String key) {
        this.headline = headline;
        this.ayat = ayat;
        this.author = author;
        this.date = date;
        this.key = key;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
