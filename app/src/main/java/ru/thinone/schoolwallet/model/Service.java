package ru.thinone.schoolwallet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class Service {
    private int id;
    private String name;
    private List<Article> articles;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class Article {
        private int id;
        private String name;
        private String description;
        private int price;
        @SerializedName("tradegroup_id")
        private int tradegroupId;

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

        public int getTradegroupId() {
            return tradegroupId;
        }

        public void setTradegroupId(int tradegroupId) {
            this.tradegroupId = tradegroupId;
        }
    }
}
