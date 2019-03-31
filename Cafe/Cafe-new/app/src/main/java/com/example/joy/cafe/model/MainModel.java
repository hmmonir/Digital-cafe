package com.example.joy.cafe.model;

public class MainModel {

    String name,price,image,id,detsils;

    public String getDetsils() {
        return detsils;
    }

    public void setDetsils(String detsils) {
        this.detsils = detsils;
    }

    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
