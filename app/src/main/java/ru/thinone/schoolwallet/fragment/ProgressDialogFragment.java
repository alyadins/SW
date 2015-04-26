package ru.thinone.schoolwallet.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import ru.thinone.schoolwallet.R;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class ProgressDialogFragment extends BaseDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.please_wait));
        setCancelable(false);
        return progressDialog;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }
}
