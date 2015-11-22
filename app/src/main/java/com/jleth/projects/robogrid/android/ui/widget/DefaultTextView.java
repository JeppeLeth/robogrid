package com.jleth.projects.robogrid.android.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class DefaultTextView extends AppCompatTextView {

    public DefaultTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public DefaultTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
    }
}
