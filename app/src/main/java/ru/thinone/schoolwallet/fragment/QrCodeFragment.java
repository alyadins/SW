package ru.thinone.schoolwallet.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.ui.ResizableImageView;
import ru.thinone.schoolwallet.util.QrImageGenerator;
import ru.thinone.schoolwallet.util.SettingsHelper;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class QrCodeFragment extends BaseFragment {

    @InjectView(R.id.qr_image)
    ImageView mQrImage;
    private List<Integer> mIds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_qr, container, false);
        ButterKnife.inject(this, v);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.qr_code_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateQrImage();
    }

    private void updateQrImage() {
        String id = SettingsHelper.getUserId(getActivity());
        StringBuilder builder = new StringBuilder(id);
        if (mIds.size() > 0) {
            builder.append(";");
            for (int i = 0; i < mIds.size() - 1; i++) {
                builder.append(mIds.get(i));
                builder.append(";");
            }

            builder.append(mIds.get(mIds.size() - 1));
        }
        Bitmap bitmap = QrImageGenerator.getQrImage(builder.toString());
        mQrImage.setImageBitmap(bitmap);
    }

    public void setIds(List<Integer> ids) {
        mIds = ids;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
