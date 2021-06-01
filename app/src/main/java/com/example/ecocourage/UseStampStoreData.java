package com.example.ecocourage;

public class UseStampStoreData {
    private String storeName;
    private String storeAddress;
    private int myStampNumber;


    public UseStampStoreData(String storeName, String storeAddress, int myStampNumber) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.myStampNumber = myStampNumber;

    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getMyStampNumber() {
        return myStampNumber;
    }

    public void setMyStampNumber(int myStampNumber) {
        this.myStampNumber = myStampNumber;
    }

}
