package ru.thinone.schoolwallet.events;

import ru.thinone.schoolwallet.fragment.BaseFragment;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class FragmentEvent {
    public static final int ADD = 0;
    public static final int REPLACE = 1;
    public static final int REMOVE = 2;


    private BaseFragment mFragment;
    private boolean mNeedBackstack;
    private int mAction = REPLACE;

    public void setFragment(BaseFragment fragment) {
        mFragment = fragment;
    }

    public FragmentEvent(BaseFragment fragment) {
        mFragment = fragment;
    }

    public boolean isNeedBackstack() {
        return mNeedBackstack;
    }

    public BaseFragment setNeedBackstack(boolean needBackstack) {
        mNeedBackstack = needBackstack;
        return mFragment;
    }

    public BaseFragment getFragment() {
        return mFragment;
    }

    public void setAction(int action) {
        mAction = action;
    }

    public int getAction() {
        return mAction;
    }
}
