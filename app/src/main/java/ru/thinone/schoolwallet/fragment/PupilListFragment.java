package ru.thinone.schoolwallet.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.WalletContext;
import ru.thinone.schoolwallet.events.FragmentEvent;
import ru.thinone.schoolwallet.model.Service;
import ru.thinone.schoolwallet.ui.pupils.PupilListData;
import ru.thinone.schoolwallet.ui.pupils.PupilServicesListAdapter;
import ru.thinone.schoolwallet.util.SettingsHelper;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, PupilServicesListAdapter.PupilServicesListAdapterListener {

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String mUserId;

    private List<PupilListData> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pupil_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserId = SettingsHelper.getUserId(getActivity());
        loadList(false);
    }

    private void loadList(final boolean fromRefresher) {
        if (!fromRefresher) setProgress(true);
        WalletContext.getNetworkService().getList(mUserId, new Callback<List<Service>>() {
            @Override
            public void success(List<Service> services, Response response) {
                if (!fromRefresher) setProgress(false);
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                initListData(services);
            }

            @Override
            public void failure(RetrofitError error) {
                showError();
            }
        });
    }

    private void initListData(List<Service> services) {
        if (mData == null) {
            mData = new ArrayList<>();
        }

        mData.clear();
        for (Service service : services) {
            mData.add(PupilListData.addHeaderData(service.getGroupName()));
            for (Service.ServiceItem serviceItem : service.getServiceItemsList().getItems()) {
                PupilListData data = PupilListData.addItemData(serviceItem.getId(),
                        serviceItem.getName(),
                        serviceItem.getDescription(),
                        serviceItem.getPrice());
                mData.add(data);
            }
        }

        mData.add(PupilListData.addButton());

        initList();
    }

    private void initList() {
        if (mAdapter == null) {
            mAdapter = new PupilServicesListAdapter(getActivity(), mData, this);
            if (mRecyclerView != null) {
                mRecyclerView.setAdapter(mAdapter);
            }
        } else {
            mAdapter.notifyDataSetChanged();
        }
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

    @Override
    public void onRefresh() {
        loadList(true);
    }

    @Override
    public void onReady(PupilServicesListAdapter listAdapter) {
        generateQrCode();

    }

    private void generateQrCode() {
        List<Integer> checkingItemIds = new ArrayList<>();
        for (PupilListData data : mData) {
            if (data.getType() == PupilListData.ITEM) {
                PupilListData.ItemData itemData = (PupilListData.ItemData) data.getData();
                if (itemData.isCheck()) {
                    checkingItemIds.add(itemData.getId());
                }
            }
        }

        QrCodeFragment fragment = new QrCodeFragment();
        fragment.setIds(checkingItemIds);

        FragmentEvent event = new FragmentEvent(fragment);
        event.setAction(FragmentEvent.ADD);
        event.setNeedBackstack(true);

        EventBus.getDefault().post(event);
    }
}
