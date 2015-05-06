package ru.thinone.schoolwallet.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import de.greenrobot.event.EventBus;
import ru.thinone.schoolwallet.R;
import ru.thinone.schoolwallet.events.DialogFragmentEvent;
import ru.thinone.schoolwallet.events.FragmentEvent;
import ru.thinone.schoolwallet.fragment.BaseDialogFragment;
import ru.thinone.schoolwallet.fragment.BaseFragment;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class MyFragmentActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MFATEST", "on create");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    public void onEvent(FragmentEvent event) {

        Log.d("MFATEST", "on fragment event " + event.getFragment().getFragmentTag() + " action =  " + event.getAction());
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(event.getFragment().getFragmentTag());


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
        }
        switch (event.getAction()) {
            case FragmentEvent.ADD:
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.add(R.id.content, event.getFragment(), event.getFragment().getFragmentTag());
                break;
            case FragmentEvent.REPLACE:
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.content, event.getFragment(), event.getFragment().getFragmentTag());
                break;
            case FragmentEvent.REMOVE:
                transaction.remove(event.getFragment());
                break;
        }
        if (event.isNeedBackstack()) {
            transaction.addToBackStack(event.getFragment().getFragmentTag());
        }
        transaction.commit();
    }

    public void onEvent(DialogFragmentEvent event) {
        Log.d("MFATEST", "on dialog event");
        FragmentManager manager = getSupportFragmentManager();
        BaseDialogFragment fragment = event.getDialogFragment();
        if (event.isRemovePrevious()) {
            DialogFragment f = (DialogFragment) manager.findFragmentByTag(fragment.getFragmentTag());
            if (f != null) {
                f.dismiss();
            }
        }
        fragment.show(manager, fragment.getFragmentTag());
    }
}
