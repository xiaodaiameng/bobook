package com.sp001.pojo;

import java.sql.Timestamp;

public class Books {
    private int id;
    private String bookname;
    private int sellerId;
    private int buyerId;
    private int price;
    private Timestamp created_time;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public String getBookname() {
        return bookname;
    }

    public int getSellerId() {
        return sellerId;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public int getPrice() {
        return price;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public Books() {
    }

    public Books(int id, String bookname, int sellerId, int buyerId, int price, Timestamp created_time) {
        this.id = id;
        this.bookname = bookname;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.price = price;
        this.created_time = created_time;
    }
}
