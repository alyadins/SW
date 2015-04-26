package ru.thinone.schoolwallet;

import android.app.Application;

import retrofit.RestAdapter;
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
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(NetworkService.ENDPOINT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mNetworkService = adapter.create(NetworkService.class);
    }

    public static NetworkService getNetworkService() {
        return mNetworkService;
    }
}
