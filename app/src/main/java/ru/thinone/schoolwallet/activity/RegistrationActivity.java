package ru.thinone.schoolwallet.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.thinone.schoolwallet.ui.AcceptPupilDialogView;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.WalletContext;
import ru.thinone.schoolwallet.model.PupilInfo;
import ru.thinone.schoolwallet.util.SettingsHelper;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class RegistrationActivity extends Activity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startScan();
    }

    private void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        integrator.autoWide();
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, R.string.scan_error, Toast.LENGTH_LONG).show();
            } else {
                setProgress(true);
                sendInfo(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendInfo(final String id) {
        WalletContext.getNetworkService().register(id, new Callback<PupilInfo>() {
            @Override
            public void success(PupilInfo pupilIInfo, Response response) {
                setProgress(false);
                showAcceptDialog(pupilIInfo, id);
            }

            @Override
            public void failure(RetrofitError error) {
                setProgress(false);
                showErrorDialog();
            }
        });
    }

    private void showAcceptDialog(PupilInfo pupilInfo, final String id) {
        AcceptPupilDialogView dialogView = new AcceptPupilDialogView(this);
        dialogView.update(pupilInfo);
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.data_valid))
                .setView(dialogView)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveData(id);
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startScan();
                    }
                }).show();
    }

    private void saveData(String id) {
        SettingsHelper.saveUserId(this, id);
        startAsPupil();
    }

    private void startAsPupil() {
        Intent intent = new Intent(RegistrationActivity.this, PupilActivity.class);
        startActivity(intent);
        finish();
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.error))
                .setMessage(getString(R.string.something_goes_wrong))
                .setPositiveButton(getString(R.string.okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    private void setProgress(boolean progress) {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (progress) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.please_wait));
            mProgressDialog.show();
        }
    }
}
