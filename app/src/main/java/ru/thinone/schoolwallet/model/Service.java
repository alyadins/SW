package ru.thinone.schoolwallet.model;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class Service extends RealmObject{
    private int id;
    private String name;
    private RealmList<Article> articles;

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

    public RealmList<Article> getArticles() {
        return articles;
    }

    public void setArticles(RealmList<Article> articles) {
        this.articles = articles;
    }
}
