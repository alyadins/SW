package ru.oblako10.schoolwallet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.oblako10.schoolwallet.R;
import ru.oblako10.schoolwallet.util.SettingsHelper;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilActivity extends Activity {

    @InjectView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mText.setText(SettingsHelper.getUserId(this));
    }
}
