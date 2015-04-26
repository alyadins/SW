package ru.oblako10.schoolwallet.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import ru.oblako10.schoolwallet.util.SettingsHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkRegistration();
    }

    private void checkRegistration() {
        String userId = SettingsHelper.getUserId(this);

        //TODO add check by regex
        if (userId.isEmpty()) {
            startRegistration();
        } else {
            //TODO add switch as parent or teacher
            startAsPupil();
        }
    }

    private void startRegistration() {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    private void startAsPupil() {
        Intent intent = new Intent(MainActivity.this, PupilActivity.class);
        startActivity(intent);
        finish();
    }
}
