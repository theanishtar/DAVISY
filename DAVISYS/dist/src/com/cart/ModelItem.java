package com.cart;

import javax.swing.Icon;

public class ModelItem {

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ModelItem(int itemID, String itemName, String description,String end, double price, String brandName, Icon image) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
        this.end =end;
        this.price = price;
        this.brandName = brandName;
        this.image = image;
    }

    public ModelItem() {
    }

    private int itemID;
    private String itemName;
    private String description;
    private String end;
    private double price;
    private String brandName;
    private Icon image;
}
