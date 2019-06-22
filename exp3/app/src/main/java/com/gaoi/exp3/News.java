package com.gaoi.exp3;

public class News {
    private int id;
    private String title, date, source;

    public News(int id, String title, String date, String source) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return id + "\t\t" + title + "\t\t" + date + "\t\t" + source;
    }
}
