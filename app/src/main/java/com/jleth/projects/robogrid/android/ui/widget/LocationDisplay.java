package com.jleth.projects.robogrid.android.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.data.util.LocalizationUtil;
import com.jleth.projects.robogrid.android.model.Location;

/**
 * Created by JEP on 11/21/2015.
 */
public class LocationDisplay extends AppCompatTextView {


    public LocationDisplay(Context context) {
        super(context);
        init();
    }

    public LocationDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocationDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        if (isInEditMode()) {
            setText(getResources().getString(R.string.coordinate, 10, 5, "North"));
        }
    }

    public void setLocation(Location location) {
        String dir = LocalizationUtil.convertDirection(getContext(), location.getDirection());
        setText(getResources().getString(R.string.coordinate, location.getX(), location.getY(), dir));
    }
}
