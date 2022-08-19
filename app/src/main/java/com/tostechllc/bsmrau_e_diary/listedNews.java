package com.tostechllc.bsmrau_e_diary;

public class listedNews {
    int id,status;
    String newsimage,newsheading,newscontent,created_on;

    public listedNews(int id, int status, String newsimage, String newsheading, String newscontent, String created_on) {
        this.id = id;
        this.status = status;
        this.newsimage = newsimage;
        this.newsheading = newsheading;
        this.newscontent = newscontent;
        this.created_on = created_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNewsimage() {
        return newsimage;
    }

    public void setNewsimage(String newsimage) {
        this.newsimage = newsimage;
    }

    public String getNewsheading() {
        return newsheading;
    }

    public void setNewsheading(String newsheading) {
        this.newsheading = newsheading;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }
}
