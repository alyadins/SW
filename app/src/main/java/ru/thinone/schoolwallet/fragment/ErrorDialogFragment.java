package ru.thinone.schoolwallet.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.thinone.schoolwallet.R;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class ErrorDialogFragment extends BaseDialogFragment{

    public interface ErrorDialogFragmentListener {
        void onDismiss(ErrorDialogFragment fragment);
    }

    private String mTitle;
    private String mMessage;
    private ErrorDialogFragmentListener mErrorDialogFragmentListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(mMessage)
                .setTitle(mTitle)
                .setNegativeButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mErrorDialogFragmentListener != null) mErrorDialogFragmentListener.onDismiss(ErrorDialogFragment.this);
                        dialog.dismiss();
                    }
                }).create();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setErrorDialogFragmentListener(ErrorDialogFragmentListener errorDialogFragmentListener) {
        mErrorDialogFragmentListener = errorDialogFragmentListener;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }
}
