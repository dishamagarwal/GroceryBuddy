package com.example.grocerybuddy;

import java.util.Date;
enum ItemType {
    VEGGIES,
    PANTRY,
    DAIRY,
    MEAT,
    OTHER;
}

public class Item {
    private String itemName;
    private Date expiry;
    private Date boughtOn;
    private ItemType itemType;

    public Item() {
    }

    public Item(String itemName, Date expiry, Date boughtOn, ItemType itemType) {
        this.itemName = itemName;
        this.expiry = expiry;
        this.boughtOn = boughtOn;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public Enum getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", expiry=" + expiry +
                ", boughtOn=" + boughtOn +
                ", itemType=" + itemType +
                '}';
    }
}
