package ru.thinone.schoolwallet.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.thinone.schoolwallet.model.PupilInfo;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class AcceptPupilDialogView extends FrameLayout {
    @InjectView(ru.thinone.schoolwallet.R.id.name)
    TextView mName;
    @InjectView(ru.thinone.schoolwallet.R.id.group)
    TextView mGroup;
    @InjectView(ru.thinone.schoolwallet.R.id.school)
    TextView mSchool;

    public AcceptPupilDialogView(Context context) {
        super(context);
        init();
    }

    public AcceptPupilDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AcceptPupilDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(ru.thinone.schoolwallet.R.layout.accept_pupil_dialog, this);
        ButterKnife.inject(this, v);
    }

    public void update(PupilInfo info) {
        mName.setText(info.getFio());
        mGroup.setText(info.getGroup());
        mSchool.setText(info.getSchool());
    }
}
