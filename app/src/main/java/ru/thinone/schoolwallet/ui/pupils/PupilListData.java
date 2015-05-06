package ru.thinone.schoolwallet.ui.pupils;

import java.util.Objects;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilListData {
    public static final int HEADER = 0;
    public static final int ITEM = 1;

    private int mType;

    private PupilListData() {
    }

    private Object mData;



    public static PupilListData addHeaderData(String name) {

        HeaderData headerData = new HeaderData();
        headerData.setName(name);

        PupilListData data = new PupilListData();
        data.setData(headerData);
        data.setType(HEADER);

        return data;
    }

    public static PupilListData addItemData(int id, String name, String description, int price) {

        ItemData itemData = new ItemData();
        itemData.setId(id);
        itemData.setName(name);
        itemData.setDescription(description);
        itemData.setPrice(price);

        PupilListData data = new PupilListData();
        data.setData(itemData);
        data.setType(ITEM);

        return data;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
        mData = data;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public static class HeaderData {
        private String mName;

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }
    }

    public static class ItemData {
        private int mId;
        private String mName;
        private String mDescription;
        private int mPrice;
        private boolean mCheck = false;

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

        public boolean isCheck() {
            return mCheck;
        }

        public void setCheck(boolean check) {
            mCheck = check;
        }
    }
}
