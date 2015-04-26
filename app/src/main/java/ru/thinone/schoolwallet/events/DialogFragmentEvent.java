package ru.thinone.schoolwallet.events;

import ru.thinone.schoolwallet.fragment.BaseDialogFragment;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class DialogFragmentEvent {

    private BaseDialogFragment mDialogFragment;
    private boolean mRemovePrevious;


    public BaseDialogFragment getDialogFragment() {
        return mDialogFragment;
    }

    public void setDialogFragment(BaseDialogFragment dialogFragment) {
        mDialogFragment = dialogFragment;
    }

    public boolean isRemovePrevious() {
        return mRemovePrevious;
    }

    public void setRemovePrevious(boolean removePrevious) {
        mRemovePrevious = removePrevious;
    }
}
