package com.sp001.pojo;

public class Students {
    private int id;
    private String nickname;
    private String pwd;
    private int purchase_times;
    private int sales_times;

    public int getId() {
        return id;
    }
    public String getNickname() {
        return nickname;
    }
    public int getPurchase_times(){
        return purchase_times;
    }
    public int getSales_times(){
        return sales_times;
    }
    public String getPwd() {
        return pwd;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public void setPurchase_times(int purchase_times) {
        this.purchase_times = purchase_times;
    }
    public void updatePurchase_times(int purchase_times) {
        this.purchase_times = purchase_times + 1;
    }

    public void setSales_times(int sales_times) {
        this.sales_times = sales_times;
    }

    public void updateSales_times(int sales_times) {
        this.sales_times = sales_times + 1;
    }

    public Students() {
    }

    public Students(int id, String nickname, String pwd, int purchase_times, int sales_times) {
        this.id = id;
        this.nickname = nickname;
        this.pwd = pwd;
        this.purchase_times = purchase_times;
        this.sales_times = sales_times;
    }
}

