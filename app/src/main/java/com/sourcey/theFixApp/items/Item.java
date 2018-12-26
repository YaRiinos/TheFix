package com.sourcey.theFixApp.items;

public class Item  {
    private String itemName,
            itemPrice,
            itemWorkPrice,
            itemDesc,
            itemType;
    private int [] lastItemCost, lastWorkCost;

    public Item(){}

    public Item(String itemType) {
        this.itemType = itemType;
    }

    public Item(String itemName, String itemPrice, String itemWorkPrice, String itemDesc, String itemType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWorkPrice = itemWorkPrice;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.lastItemCost = new int [10];
        this.lastItemCost[0] = Integer.parseInt(itemPrice);
        this.lastWorkCost = new int [10];
        this.lastWorkCost[0] = Integer.parseInt(itemWorkPrice);
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemWorkPrice='" + itemWorkPrice + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
