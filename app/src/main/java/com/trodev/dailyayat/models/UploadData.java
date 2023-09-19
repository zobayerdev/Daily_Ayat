package com.trodev.dailyayat.models;

public class UploadData {

    private String mName, mHead , mDate , mAuthor;
    private String mImageUrl;

    public UploadData() {

    }
    public UploadData(String name, String headline, String author, String dates , String imageUrl)
    {
        if (name.trim().equals(""))
        {
            name = "No Name";
        }
        if (author.trim().equals(""))
        {
            author = "No Author";
        }
        if(headline.trim().equals(""))
        {
            headline = "No Headline";
        }
        if(dates.trim().equals(""))
        {
            dates = "No Date";
        }

        mName = name;
        mHead = headline;
        mAuthor = author;
        mDate = dates;
        mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHead() {
        return mHead;
    }

    public void setmHead(String mHead) {
        this.mHead = mHead;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
