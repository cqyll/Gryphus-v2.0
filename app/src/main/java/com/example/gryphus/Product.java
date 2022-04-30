package com.example.gryphus;

import android.graphics.drawable.Drawable;

public class Product {

    private int itemID;
    private String name;
    private int quantity;
    private double price;
    private boolean inStock;
    private boolean favourite;
    private Drawable icon;


    public boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Product(String name, Drawable icon, int id, boolean inStock) {
        this.name = name;
        this.icon = icon;
        this.itemID = id;
        this.inStock = inStock;
    }

    public Product(String name, int itemID, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.itemID = itemID;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }



    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public void setName(String name) {
        this.name = name;
    }
}
