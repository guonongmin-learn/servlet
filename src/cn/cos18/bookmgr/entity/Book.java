package cn.cos18.bookmgr.entity;

import java.util.Date;

public class Book {

    private int id;
    private String name;
    private String auther;
    private float price;
    private Date pubDate;


    public Book(int id, String name, String auther, float price, Date pubDate) {
        this.id = id;
        this.name = name;
        this.auther = auther;
        this.price = price;
        this.pubDate = pubDate;
    }

    public Book( String name, String auther, float price, Date pubDate) {
        this.name = name;
        this.auther = auther;
        this.price = price;
        this.pubDate = pubDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}
