package com.sourcey.theFixApp.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Item  {
    private String itemName,
            itemPrice,
            itemWorkPrice,
            itemDesc,
            itemType;

    private String lastIndex;

    private HashMap<Integer, Integer> lastItemCost = new HashMap<>();
    private HashMap<Integer, Integer> lastWorkCost = new HashMap<>();

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
        this.lastIndex = "0";
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
