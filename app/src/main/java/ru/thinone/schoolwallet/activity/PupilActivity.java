package ru.thinone.schoolwallet.activity;

import android.os.Bundle;

import de.greenrobot.event.EventBus;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.events.FragmentEvent;
import ru.thinone.schoolwallet.fragment.PupilListFragment;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class PupilActivity extends MyFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupil);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PupilListFragment pupilListFragment = new PupilListFragment();
        FragmentEvent event = new FragmentEvent(pupilListFragment);
        event.setAction(FragmentEvent.ADD);
        EventBus.getDefault().post(event);
    }
}
