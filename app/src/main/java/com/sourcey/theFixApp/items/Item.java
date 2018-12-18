package com.sourcey.theFixApp.items;

public class Item {
    private String itemName,
            itemPrice,
            itemWorkPrice,
            itemDesc,
            itemType;

    public Item(){}

    public Item(String itemName, String itemPrice, String itemWorkPrice, String itemDesc, String itemType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWorkPrice = itemWorkPrice;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemWorkPrice() {
        return itemWorkPrice;
    }

    public void setItemWorkPrice(String itemWorkPrice) {
        this.itemWorkPrice = itemWorkPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
