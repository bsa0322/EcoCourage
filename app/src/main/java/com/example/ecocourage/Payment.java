package com.example.ecocourage;

import java.io.Serializable;

public class Payment implements Serializable{
    int idx;
    int storeIdx; //애용가게 데이터베이스 인덱스
    String name; //결제 가게 이름;
    double sale; //가게 할인률;
    int stamp;   //스탬프 변화량
    int courage; //용기점수 변화량

    public Payment(){};

    public Payment(String name){
        this.name=name;
        this.sale=0;
        this.stamp=0;
        this.courage=0;
    }

    public Payment(int idx, String name){
        this.idx=idx;
        this.sale=0;
        this.name=name;
        this.stamp=0;
        this.courage=0;
    }

    public Payment(int storeIdx, String name,double sale,int stamp, int courage){
        this.storeIdx=storeIdx;
        this.name=name;
        this.sale=sale;
        this.stamp=stamp;
        this.courage=courage;
    }

    public Payment(int idx,int storeIdx, String name,double sale,int stamp,int courage){
        this.storeIdx=storeIdx;
        this.idx=idx;
        this.name=name;
        this.sale=sale;
        this.stamp=stamp;
        this.courage=courage;
    }

    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStamp() {
        return stamp;
    }
    public void setStamp(int stamp) {
        this.stamp = stamp;
    }
    public int getCourage() {
        return courage;
    }
    public void setCourage(int courage) {
        this.courage = courage;
    }
    public double getSale() {
        return sale;
    }
    public void setSale(double sale) {
        this.sale = sale;
    }
    public int getStoreIdx() {
        return storeIdx;
    }
    public void setStoreIdx(int storeIdx) {
        this.storeIdx = storeIdx;
    }
}
