package ru.thinone.schoolwallet.network;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.thinone.schoolwallet.model.PupilInfo;
import ru.thinone.schoolwallet.model.Service;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public interface NetworkService  {
    String ENDPOINT_URL = "http://school-lab.oblako10.ru/app";

    @GET("/!onepages.html?page=9004")
    void register(@Query("code") String code, Callback<PupilInfo> callback);

    @GET("/!onepages.html?page=9003")
    void getList(@Query("code") String code, Callback<List<Service>> callback);
}
