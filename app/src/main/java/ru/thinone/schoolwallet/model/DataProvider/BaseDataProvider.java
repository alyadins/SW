package ru.thinone.schoolwallet.model.DataProvider;

/**
 * Created by alexandrlyadinskii on 19.05.15.
 * All rights reserved©
 */
public class BaseDataProvider {

    public static final int NETWORK = -1;
    public static final int INTERNAL = -2;

    public interface OnDataReady<T> {
        void onReady(T data);
        void onError(int reason);
    }
}
