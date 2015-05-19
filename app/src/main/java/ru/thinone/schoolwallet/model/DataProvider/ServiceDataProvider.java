package ru.thinone.schoolwallet.model.DataProvider;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.thinone.schoolwallet.WalletContext;
import ru.thinone.schoolwallet.model.Article;
import ru.thinone.schoolwallet.model.Service;
import ru.thinone.schoolwallet.network.NetworkService;

import static ru.thinone.schoolwallet.model.DataProvider.TimeSettings.*;

/**
 * Created by alexandrlyadinskii on 19.05.15.
 * All rights reserved©
 */
public class ServiceDataProvider extends BaseDataProvider{

    public static void getServiceList(final Context context, String code, final BaseDataProvider.OnDataReady<List<Service>> onDataReady) {
        final List<Service> serviceList = new ArrayList<>();
        final Realm realm = Realm.getInstance(context);
        final RealmResults<Service> realmServices = realm.where(Service.class).findAll();
        if (isActual(context, SERVICES, SERVICES_TTL) && realmServices.size() > 0) {
            serviceList.addAll(realmServices);
            onDataReady.onReady(serviceList);
            return;
        }

        WalletContext.getNetworkService().getList(code, new Callback<List<Service>>() {
            @Override
            public void success(List<Service> services, Response response) {

                updateTime(context, SERVICES);
                try {
                    realm.beginTransaction();
                    realm.clear(Service.class);
                    realm.clear(Article.class);
                    realm.copyToRealm(services);
                    realm.commitTransaction();

                    serviceList.addAll(services);
                    onDataReady.onReady(serviceList);
                } catch (RealmException e) {
                    onDataReady.onError(INTERNAL);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                    serviceList.addAll(realmServices);
                    onDataReady.onReady(serviceList);
            }
        });
    }
}
