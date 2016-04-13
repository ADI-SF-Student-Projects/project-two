package com.example.android.oaklandupdown;

import java.io.Serializable;

public class Place {

    /**
     * Created by stewartmcmillan on 3/25/16.
     */

    private int id;
    private String name;
    private String type;
    private String subType;
    private String address;
    private String phone;
    private String price;
    public int isFavorite;
    private String description;
    private int photo;

    public Place(int id, String name, String type, String subType, String address, String phone, String price, int isFavorite, String description, int photo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.isFavorite = isFavorite;
        this.description = description;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
