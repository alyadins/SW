package ru.thinone.schoolwallet.ui.pupils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.thinone.schoolwallet.R;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilServicesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface PupilServicesListAdapterListener {
        void onReady(PupilServicesListAdapter listAdapter);
    }

    private Context mContext;
    private List<PupilListData> mListData;
    private PupilServicesListAdapterListener mPupilServicesListAdapterListener;

    public PupilServicesListAdapter(Context context, List<PupilListData> listData, PupilServicesListAdapterListener listAdapterListener) {
        mListData = listData;
        mContext = context;
        mPupilServicesListAdapterListener = listAdapterListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case PupilListData.HEADER:
                v = LayoutInflater.from(mContext).inflate(R.layout.pupil_services_list_header, parent, false);
                return new HeaderViewHolder(v);
            case PupilListData.ITEM:
                v = LayoutInflater.from(mContext).inflate(R.layout.pupil_services_list_item, parent, false);
                return new ItemViewHolder(v);
            case PupilListData.BUTTON:
                v = LayoutInflater.from(mContext).inflate(R.layout.pupil_services_list_button, parent, false);
                return new ButtonViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case PupilListData.HEADER:
                PupilListData.HeaderData headerData = (PupilListData.HeaderData) mListData.get(position).getData();
                ((HeaderViewHolder) holder).setHeaderText(headerData.getName());
                break;
            case PupilListData.ITEM:
                PupilListData.ItemData itemData = (PupilListData.ItemData) mListData.get(position).getData();
                ((ItemViewHolder) holder).update(itemData);
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.header_text)
        TextView mHeaderText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            R.layout.pupil_services_list_header
        }

        public void setHeaderText(String text) {
            mHeaderText.setText(text);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.name)
        TextView mName;
        @InjectView(R.id.description)
        TextView mDescription;
        @InjectView(R.id.price)
        TextView mPrice;
        @InjectView(R.id.checkbox)
        CheckBox mCheckbox;
        @InjectView(R.id.card_view)
        CardView mCardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            R.layout.pupil_services_list_item
            mCardView.setOnClickListener(this);
        }

        public void update(PupilListData.ItemData itemData) {
            mName.setText(itemData.getName());
            mDescription.setText(itemData.getDescription());
            mPrice.setText(itemData.getPrice() + " руб.");
            mCheckbox.setChecked(itemData.isCheck());
        }

        @Override
        public void onClick(View v) {
            PupilListData.ItemData itemData = (PupilListData.ItemData) mListData.get(getPosition()).getData();
            itemData.setCheck(!itemData.isCheck());
            notifyItemChanged(getPosition());
        }
    }

    public class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.ready_button)
        Button mReadyButton;

        public ButtonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            R.layout.pupil_services_list_button
            mReadyButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPupilServicesListAdapterListener.onReady(PupilServicesListAdapter.this);
        }
    }
}
