package ru.thinone.schoolwallet.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.WalletContext;
import ru.thinone.schoolwallet.model.Service;
import ru.thinone.schoolwallet.util.SettingsHelper;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilListFragment extends BaseFragment {

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private String mUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pupil_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserId = SettingsHelper.getUserId(getActivity());
        loadList();
    }

    private void loadList() {
        setProgress(true);
        WalletContext.getNetworkService().getList(mUserId, new Callback<List<Service>>() {
            @Override
            public void success(List<Service> services, Response response) {
                initList();
            }

            @Override
            public void failure(RetrofitError error) {
                showError();
            }
        });
    }

    private void initList() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }
}
