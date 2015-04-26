package ru.oblako10.schoolwallet.network;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.oblako10.schoolwallet.model.PupilInfo;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public interface NetworkService  {
    String ENDPOINT_URL = "http://school-lab.oblako10.ru/app";

    @GET("/!onepages.html?page=9004")
    void register(@Query("code") String code, Callback<PupilInfo> callback);

    @GET("/!onepages.html?page=9003")
    void getList(@Query("code") String code);
}
