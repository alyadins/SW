package ru.thinone.schoolwallet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class Service {

    @SerializedName("tradegroup-name")
    String mGroupName;
    @SerializedName("tradegroup-id")
    int mGroupId;
    @SerializedName("articles")
    ServiceItemsList mServiceItemsList;

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }


    public ServiceItemsList getServiceItemsList() {
        return mServiceItemsList;
    }

    public void setServiceItemsList(ServiceItemsList serviceItemsList) {
        mServiceItemsList = serviceItemsList;
    }

    public class ServiceItemsList {
        @SerializedName("tradegroupID7")
        String mServicesId;

        @SerializedName("items")
        List<ServiceItem> mItems;

        public String getServicesId() {
            return mServicesId;
        }

        public void setServicesId(String servicesId) {
            mServicesId = servicesId;
        }

        public List<ServiceItem> getItems() {
            return mItems;
        }

        public void setItems(List<ServiceItem> items) {
            mItems = items;
        }
    }

    public class ServiceItem {
        @SerializedName("id")
        int mId;

        @SerializedName("name")
        String mName;

        @SerializedName("description")
        String mDescription;

        @SerializedName("price")
        int mPrice;

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public int getPrice() {
            return mPrice;
        }

        public void setPrice(int price) {
            mPrice = price;
        }
    }
}
