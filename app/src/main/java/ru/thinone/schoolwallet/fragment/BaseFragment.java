package ru.thinone.schoolwallet.fragment;

import android.support.v4.app.Fragment;

import de.greenrobot.event.EventBus;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.events.DialogFragmentEvent;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public abstract class BaseFragment extends Fragment{


    private ProgressDialogFragment mProgressDialogFragment;

    public void setProgress(boolean progress) {
        dismissProgressDialog();
        if (progress) {
            initProgressDialog();
        }
    }

    public void showError() {
        showError(getResources().getString(R.string.something_goes_wrong));
    }

    public void showError(String errorMessage) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        errorDialogFragment.setMessage(errorMessage);
        errorDialogFragment.setMessage(getString(R.string.error));
        errorDialogFragment.setErrorDialogFragmentListener(new ErrorDialogFragment.ErrorDialogFragmentListener() {
            @Override
            public void onDismiss(ErrorDialogFragment fragment) {
                getActivity().finish();
            }
        });
        setProgress(false);
        postDialog(errorDialogFragment);
    }

    private void initProgressDialog() {
        mProgressDialogFragment = new ProgressDialogFragment();
        postDialog(mProgressDialogFragment);
    }

    private void dismissProgressDialog() {
        try {
            mProgressDialogFragment.dismiss();
        } catch (NullPointerException e) {
            // all is ok
        }
    }

    private void postDialog(BaseDialogFragment fragment) {
        DialogFragmentEvent event = new DialogFragmentEvent();
        event.setDialogFragment(fragment);
        event.setRemovePrevious(true);
        EventBus.getDefault().post(event);
    }



    public abstract String getFragmentTag();
}
