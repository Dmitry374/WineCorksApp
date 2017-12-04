package com.example.dima.winecorksapp;

/**
 * Created by Dima on 25.11.2017.
 */

public class Cork {
    int id;
    int name;
    int image;
    int link;
    int price;
    int brand;

    public Cork(int id, int name, int image, int link, int price, int brand) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.link = link;
        this.price = price;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }

    public int getLink() {
        return link;
    }

    public int getPrice() {
        return price;
    }

    public int getBrand() {
        return brand;
    }
}
