package ru.thinone.schoolwallet.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @InjectView(R.id.summ_text)
    TextView mSummText;

    private String mUserId;

    private List<PupilListData> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pupil_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSummText.setText("0 руб.");
        mUserId = SettingsHelper.getUserId(getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ready) {
            generateQrCode();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadList();
        updateSum();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.pupil_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void loadList() {
        if (mData == null) {
            WalletContext.getNetworkService().getList(mUserId, new Callback<List<Service>>() {
                @Override
                public void success(List<Service> services, Response response) {
                    initListData(services);
                }

                @Override
                public void failure(RetrofitError error) {
                    showError();
                }
            });
        } else {
            initList();
        }
    }

    private void initListData(List<Service> services) {
        if (mData == null) {
            mData = new ArrayList<>();
        }

        mData.clear();
        for (Service service : services) {
            mData.add(PupilListData.addHeaderData(service.getName()));
            for (Service.Article article : service.getArticles()) {
                PupilListData data = PupilListData.addItemData(article.getId(),
                        article.getName(),
                        article.getDescription(),
                        article.getPrice());
                mData.add(data);
            }
        }

        initList();
    }

    private void initList() {
        if (mAdapter == null) {
            mAdapter = new PupilServicesListAdapter(getActivity(), mData, this);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mAdapter);
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
        loadList();
    }

    @Override
    public void onReady(PupilServicesListAdapter listAdapter) {
        generateQrCode();

    }

    @Override
    public void onChange(PupilServicesListAdapter listAdapter) {
        updateSum();
    }

    private void updateSum() {
        if (mData != null) {
            int sum = 0;
            for (PupilListData data : mData) {
                if (data.getType() == PupilListData.ITEM) {
                    PupilListData.ItemData itemData = ((PupilListData.ItemData) data.getData());
                    if (itemData.isCheck()) {
                        sum += itemData.getPrice();
                    }
                }
            }

            mSummText.setText(sum + " руб.");
        }
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
        event.setAction(FragmentEvent.REPLACE);
        event.setNeedBackstack(true);

        EventBus.getDefault().post(event);
    }
}
