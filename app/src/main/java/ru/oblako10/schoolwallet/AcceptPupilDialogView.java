package ru.oblako10.schoolwallet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.oblako10.schoolwallet.model.PupilInfo;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class AcceptPupilDialogView extends FrameLayout {
    @InjectView(R.id.name)
    TextView mName;
    @InjectView(R.id.group)
    TextView mGroup;
    @InjectView(R.id.school)
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
        View v = LayoutInflater.from(getContext()).inflate(R.layout.accept_pupil_dialog, this);
        ButterKnife.inject(this, v);
    }

    public void update(PupilInfo info) {
        mName.setText(info.getFio());
        mGroup.setText(info.getGroup());
        mSchool.setText(info.getSchool());
    }
}
