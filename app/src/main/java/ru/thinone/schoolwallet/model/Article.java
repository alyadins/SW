package ru.thinone.schoolwallet.model;

import io.realm.RealmObject;

public class Article extends RealmObject{
    private int id;
    private String name;
    private String description;
    private int price;
    private int tradegroup_id;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTradegroup_id() {
        return tradegroup_id;
    }

    public void setTradegroup_id(int tradegroup_id) {
        this.tradegroup_id = tradegroup_id;
    }
}