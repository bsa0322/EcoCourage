package com.example.ecocourage;

import java.io.Serializable;

//정보 전달 목적
public class EcoStore implements Serializable{
    int idx;
    String name; //가게이름
    String address; //가게주소
    int storeCourage; //가게의 용기점수
    int customerCourage; //소비자의 용기점수
    int customerStamp; //소비자의 스탬프수
    int ranking; //가게 순위
    double storePlaceX; //가게 x좌표값
    double storePlaceY; //가게 y좌표값
    int storeStamp; //가게 할인률당 스탬프량
    double storeSale; //가게 할인률

    public EcoStore(String name, String tel, int idx){
        this.customerCourage=0;
        this.customerStamp=0;
    }

    public EcoStore(String name, String address){
        this.name = name;
        this.address = address;
        this.customerCourage=0;
        this.customerStamp=0;
    }

    public EcoStore(int idx, String name, String address){
        this.idx=idx;
        this.name=name;
        this.address=address;
        this.customerCourage=0;
        this.customerStamp=0;
    }

    public EcoStore(String name, String address, int ranking, int storeStamp, double storeSale){
        this.name=name;
        this.address=address;
        this.ranking=ranking;
        this.storeStamp=storeStamp;
        this.storeSale=storeSale;
        this.customerCourage=0;
        this.customerStamp=0;
    }

    public EcoStore(int idx, String name, String address, int customerCourage, int customerStamp, int ranking, int storeStamp, double storeSale){
        this.idx=idx;
        this.name=name;
        this.address=address;
        this.ranking=ranking;
        this.storeStamp=storeStamp;
        this.storeSale=storeSale;
        this.customerCourage=customerCourage;
        this.customerStamp=customerStamp;
    }

    public int getIdx(){
        return idx;
    }
    public void setIdx(int idx){
        this.idx=idx;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getStoreCourage() {
        return storeCourage;
    }
    public void setStoreCourage(int storeCourage) {
        this.storeCourage = storeCourage;
    }
    public int getCustomerCourage() {
        return customerCourage;
    }
    public void setCustomerCourage(int customerCourage) {
        this.customerCourage = customerCourage;
    }
    public int getCustomerStamp() {
        return customerStamp;
    }
    public void setCustomerStamp(int customerStamp) {
        this.customerStamp = customerStamp;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public double getStorePlaceX() {
        return storePlaceX;
    }
    public void setStorePlaceX(double storePlaceX) {
        this.storePlaceX = storePlaceX;
    }
    public double getStorePlaceY() {
        return storePlaceY;
    }
    public void setStorePlaceY(double storePlaceY) {
        this.storePlaceY = storePlaceY;
    }
    public int getStoreStamp() {
        return storeStamp;
    }
    public void setStoreStamp(int storeStamp) {
        this.storeStamp = storeStamp;
    }
    public double getStoreSale() {
        return storeSale;
    }
    public void setStoreSale(double storeSale) {
        this.storeSale = storeSale;
    }
}
