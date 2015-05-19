package ru.thinone.schoolwallet;

import android.app.Application;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import ru.thinone.schoolwallet.network.NetworkService;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class WalletContext extends Application {

    private static NetworkService mNetworkService;

    @Override
    public void onCreate() {
        super.onCreate();
        initNetworkService();
    }

    private void initNetworkService() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
        OkHttpClient client = new OkHttpClient();



        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(NetworkService.ENDPOINT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .setConverter(new GsonConverter(gson))
                .build();

        mNetworkService = adapter.create(NetworkService.class);
    }

    public static NetworkService getNetworkService() {
        return mNetworkService;
    }
}
